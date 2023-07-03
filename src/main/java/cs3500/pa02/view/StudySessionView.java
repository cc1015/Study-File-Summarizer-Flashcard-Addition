package cs3500.pa02.view;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents the view in a study session program.
 */
public class StudySessionView {
  private Appendable output;

  public StudySessionView(Appendable output) {
    this.output = Objects.requireNonNull(output);
  }

  /**
   * Appends the welcome message to the output.
   */
  public void displayWelcome() {
    displayMessage("Enter the flashcard bank .sr file path:");
  }

  /**
   * Appends the choose question prompt to the output.
   */
  public void displayChooseQuestion() {
    displayMessage("How many flashcards would you like to study? (enter a number)");
  }

  /**
   * Appends the question to the output.
   */
  public void displayQuestion(String question) {
    displayMessage(question);
  }

  /**
   * Appends the answer to the output.
   */
  public void displayAnswer(String answer) {
    displayMessage(answer);
  }

  /**
   * Appends the options to the output.
   */
  public void displayOptions() {
    displayMessage("1. mark easy  2. mark hard  3. show answer  4. exit");
  }

  /**
   * Appends the incorrect option error to the output.
   */
  public void displayIncorrectOption() {
    displayMessage("Please only select from one of the options listed in the question.");
  }

  /**
   * Appends the non-existent file error message to the output
   */
  public void displayNoFileError() {
    displayMessage(
        "The given file is not a .sr file or does not exist. Would you like to enter another .sr "
            + "file?\n1.YES 2. NO");
  }

  /**
   * Appends the session stats to the output.
   *
   * @param questionsAnswered The total number of questions answered.
   *
   * @param easyToHard The number of questions changed from easy to hard.
   *
   * @param hardToEasy The number of questions changed from hard to easy.
   *
   * @param easyNum The number of easy questions in the bank.
   *
   * @param hardNum The number of hard questions in the bank.
   */
  public void displayStats(int questionsAnswered, int easyToHard, int hardToEasy, int easyNum,
                           int hardNum) {
    String totalStat = "Total number of questions answered: " + questionsAnswered + "\n";
    String easyToHardStat =
        "Total number of questions changed from easy to hard: " + easyToHard + "\n";
    String hardToEasyStat =
        "Total number of questions changed from hard to easy: " + hardToEasy + "\n";
    String totalEasy = "Total number of easy questions: " + easyNum + "\n";
    String totalHard = "Total number of hard questions: " + hardNum;

    String stats = totalStat + easyToHardStat + hardToEasyStat + totalEasy + totalHard;

    try {
      this.output.append("Study session stats:\n" + stats);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Appends the message to the output.
   */
  private void displayMessage(String message) {
    try {
      this.output.append(message + "\n");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
