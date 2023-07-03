package cs3500.pa02.controller;

import cs3500.pa02.model.StudyCollection;
import cs3500.pa02.model.ssmodel.StudySessionData;
import cs3500.pa02.model.ssmodel.Difficulty;
import cs3500.pa02.model.ssmodel.Flashcard;
import cs3500.pa02.model.ssmodel.FlashcardBank;
import cs3500.pa02.parser.SrParser;
import cs3500.pa02.reader.FileReader;
import cs3500.pa02.view.StudySessionView;
import cs3500.pa02.writer.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Represents a controller that executes a study session.
 */
public class StudySessionController implements Controller {
  private Path srPath;
  private StudySessionData studySessionData;
  private StudySessionView studySessionView;
  private FlashcardBank bank;
  private Random rand;
  private Scanner scanner;

  /**
   * Initializes the studySessionData field. Initializes studySessionView field with the given
   * Appendable input. Initializes the scanner field with the given Readable input. Initializes
   * rand with the given Random.
   */
  public StudySessionController(Readable input, Appendable output, Random rand) {
    this.srPath = null;
    this.studySessionData = new StudySessionData();
    this.studySessionView = new StudySessionView(output);
    this.bank = null;
    this.rand = rand;
    this.scanner = new Scanner(input);
  }

  /**
   * Executes the study session by facilitating the interactions of the study session view and
   * study session model. Displays welcome dialogue and checks if the user given path is valid,
   * if so continue with session, otherwise ask again.
   */
  @Override
  public void executeProgram() {
    studySessionView.displayWelcome();
    if (setSrPathIfExists()) {
      this.bank = createStudyBank();
      ArrayList<Flashcard> questions = generateQuestions();
      runSession(questions);
      showFinalStats();
      writeFile(bank, srPath.toString());
    } else {
      enterNewFile();
    }
  }

  /**
   * Writes the given content at the given destination path. Displays error message if file cannot
   * be written.
   *
   * @param content  The updated flashcards to be written.
   * @param destPath The destination path the content is written at.
   */
  @Override
  public void writeFile(StudyCollection content, String destPath) {
    FileWriter writer = new FileWriter();
    try {
      writer.write(destPath, content);
    } catch (IOException e) {
      throw new RuntimeException("There was an error writing the file.");
    }
  }

  /**
   * Called if the given .sr file is non-existent. Gives the user the option to re-enter a new file.
   */
  private void enterNewFile() {
    boolean invalidInput = true;
    while (invalidInput) {
      studySessionView.displayNoFileError();

      enum Input {
        YES,
        NO
      }

      try {
        int userChoice = Integer.parseInt(readUserInput()); // checks if user input a random letter
        try {
          Input input = Input.values()[userChoice - 1]; // checks if user input random number
          switch (input) {
            case YES:
              invalidInput = false;
              executeProgram();
              break;
            case NO:
              return;
            default:
              studySessionView.displayIncorrectOption();
              break;
          }
        } catch (ArrayIndexOutOfBoundsException e) {
          studySessionView.displayIncorrectOption();
        }
      } catch (NumberFormatException e) {
        studySessionView.displayIncorrectOption();
      }
    }
  }

  /**
   * Reads user input by calling readUserInput() and checks if the given file exists and is a .sr
   * file. If so, set the srPath field to the given file's path and return true, otherwise return
   * false.
   *
   * @return The user-inputted sr file path.
   */
  private boolean setSrPathIfExists() {
    String pathString = readUserInput();
    File srFile = new File(pathString);
    if (srFile.exists() && pathString.endsWith(".sr")) {
      this.srPath = Path.of(srFile.getPath());
      return true;
    } else {
      return false;
    }
  }

  /**
   * Creates the bank of flashcards to choose questions from by calling readInput to read the sr
   * file and parser to parse the read content.
   *
   * @return The FlashcardBank of possible questions.
   */
  private FlashcardBank createStudyBank() {
    ArrayList<StringBuilder> content = readInput();
    SrParser parser = new SrParser();
    FlashcardBank bank = new FlashcardBank(parser.parse(content));

    return bank;
  }

  /**
   * Reads the files at this Controller's sr path.
   *
   * @return The content of the file as a list of StringBuilders.
   */
  private ArrayList<StringBuilder> readInput() {
    FileReader reader = new FileReader(srPath);
    return reader.read();
  }

  /**
   * Uses studySessionView to take in user's desired number of questions. Calls bank's
   * generateRandomCards method with the desired number of questions.
   *
   * @return The list of random questions.
   */
  private ArrayList<Flashcard> generateQuestions() {
    boolean invalidInput = true;
    int questionNum = 0;

    while (invalidInput) {
      studySessionView.displayChooseQuestion();
      String input = readUserInput();
      try {
        questionNum = Integer.parseInt(input);
        if (questionNum >= 0) {
          invalidInput = false;
        } else {
          studySessionView.displayIncorrectOption();
        }
      } catch (NumberFormatException e) {
        studySessionView.displayIncorrectOption();
      }
    }

    ArrayList<Flashcard> questions = this.bank.generateRandomCards(questionNum, rand);
    return questions;
  }

  /**
   * Uses a Scanner to take in the user's input from the command line.
   *
   * @return The user's input.
   */
  private String readUserInput() {
    StringBuilder line = new StringBuilder();
    if (scanner.hasNextLine()) {
      line.append(scanner.nextLine());
    }
    return line.toString();
  }

  /**
   *
   *
   * @param questions The questions to be displayed.
   */
  private void runSession(ArrayList<Flashcard> questions) {
    boolean selectedExit = false;

    // for every flashcard and while the user has not selected "exit"
    for (int i = 0; i < questions.size() && !selectedExit; i += 1) {
      Flashcard currentFlashcard = questions.get(i);
      boolean inputInvalidOption = true;

      // while the user has input an invalid option
      while (inputInvalidOption) {
        studySessionView.displayQuestion(currentFlashcard.questionToString());
        studySessionView.displayOptions();
        try {
          int userChoice = Integer.parseInt(readUserInput()); // check if user input is a number
          try {
            Option option = Option.values()[userChoice - 1]; // check if user input is in range
            switch (option) {
              case EXIT:
                selectedExit = true;
                break;
              default:
                updateFlashcard(option, currentFlashcard);
                break;
            }
            inputInvalidOption = false;
          } catch (ArrayIndexOutOfBoundsException e) {
            studySessionView.displayIncorrectOption();
          }
        } catch (NumberFormatException e) {
          studySessionView.displayIncorrectOption();
        }
      }
      runBuffer();
    }
  }

  /**
   * Takes in the users input and updates the given current flashcard's metadata accordingly.
   *
   * @param input            The user's input.
   * @param currentFlashcard The current flashcard that needs to be updated.
   * @return Whether the user input a valid input.
   */
  private void updateFlashcard(Option input, Flashcard currentFlashcard) {
    switch (input) {
      case MARK_EASY:
        if (this.bank.updateDifficulty(currentFlashcard, Difficulty.EASY)) {
          studySessionData.updateHardToEasyCount();
        }
        studySessionData.updateQuestionsAnswered();
        break;
      case MARK_HARD:
        if (this.bank.updateDifficulty(currentFlashcard, Difficulty.HARD)) {
          studySessionData.updateEasyToHardCount();
        }
        studySessionData.updateQuestionsAnswered();
        break;
      case SHOW_ANSWER:
        String answer = currentFlashcard.answerToString();
        studySessionView.displayAnswer(answer);
        studySessionData.updateQuestionsAnswered();
        break;
      default:
        break;
    }
  }

  /**
   * Runs Thread.sleep as a time buffer between new questions.
   */
  private void runBuffer() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Gets the final stats of the session and delegates to the studySessionView to display them.
   */
  private void showFinalStats() {
    int questionsAnswered = studySessionData.getQuestionsAnswered();
    int easyToHardCount = studySessionData.getEasyToHardCount();
    int hardToEasyCount = studySessionData.getHardToEasyCount();
    int totalEasy = this.bank.getEasyCount();
    int totalHard = this.bank.getHardCount();

    studySessionView.displayStats(questionsAnswered, easyToHardCount, hardToEasyCount, totalEasy,
        totalHard);
  }
}
