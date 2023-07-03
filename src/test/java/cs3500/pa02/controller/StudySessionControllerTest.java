package cs3500.pa02.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa02.model.ssmodel.Difficulty;
import cs3500.pa02.model.ssmodel.Flashcard;
import cs3500.pa02.model.ssmodel.FlashcardBank;
import cs3500.pa02.reader.FileReader;
import cs3500.pa02.writer.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for StudySessionController class.
 */
class StudySessionControllerTest {
  Flashcard chemCard;
  Flashcard lawCard;
  Flashcard sharksCard;
  FlashcardBank hardBank;

  Flashcard waterCard;
  Flashcard monthCard;
  Flashcard dozenCard;
  FlashcardBank easyBank;

  Appendable output;
  Appendable expectedOutput;
  String welcome = "Enter the flashcard bank .sr file path:\n";
  String flashcardNum = "How many flashcards would you like to study? (enter a number)\n";
  String options = "1. mark easy  2. mark hard  3. show answer  4. exit\n";
  String studyStats = "Study session stats:\n";
  String total = "Total number of questions answered: ";
  String easyToHard = "Total number of questions changed from easy to hard: ";
  String hardToEasy = "Total number of questions changed from hard to easy: ";
  String totalEasy = "Total number of easy questions: ";
  String totalHard = "Total number of hard questions: ";
  String invalidOption = "Please only select from one of the options listed in the question.\n";
  String invalidFile = "The given file is not a .sr file or does not exist. Would you like to "
      + "enter another .sr file?\n1.YES 2. NO\n";

  FileWriter writer;
  FileReader reader;

  ArrayList<String> expectedSrStrings;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    chemCard = new Flashcard("what are some halogens?", "argon and xenon.", Difficulty.HARD);
    lawCard = new Flashcard("how many amendments are there?", "27.", Difficulty.HARD);
    sharksCard = new Flashcard("what are the different types of sharks?",
        "bull, hammerhead, and great white.", Difficulty.HARD);

    ArrayList<Flashcard> cards = new ArrayList<>();
    cards.add(chemCard);
    cards.add(lawCard);
    cards.add(sharksCard);

    hardBank = new FlashcardBank(cards);

    waterCard = new Flashcard("what is water?", "H2O.", Difficulty.EASY);
    monthCard = new Flashcard("what is the month?", "may.", Difficulty.EASY);
    dozenCard = new Flashcard("how many is in a dozen?", "12.", Difficulty.EASY);

    ArrayList<Flashcard> cards2 = new ArrayList<>();
    cards2.add(waterCard);
    cards2.add(monthCard);
    cards2.add(dozenCard);

    easyBank = new FlashcardBank(cards2);

    output = new StringBuilder();
    writer = new FileWriter();

    try {
      writer.write("src/test/resources/allHard.sr", hardBank);
    } catch (IOException e) {
      fail();
    }

    try {
      writer.write("src/test/resources/allEasy.sr", easyBank);
    } catch (IOException e) {
      fail();
    }

    expectedSrStrings = new ArrayList<>();
  }

  /**
   * Simulates a hard question being marked easy. Checks if the stats show 1 question answered,
   * 1 question marked easy, 1 total easy questions in the file, and 2 total hard question in
   * and that the question's difficulty is updated after the session in the sr file.
   */
  @Test
  public void testMarkEasyPlay() {
    String sequence = "src/test/resources/allHard.sr\n1\n1";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(
        welcome + flashcardNum + lawCard.questionToString() + "\n" + options + studyStats
            + total + "1\n" + easyToHard + "0\n" + hardToEasy + "1\n" + totalEasy + "1\n"
            + totalHard + "2");

    assertEquals(output.toString(), expectedOutput.toString());

    reader = new FileReader(Path.of("src/test/resources/allHard.sr"));
    ArrayList<StringBuilder> updatedSr = reader.read();
    ArrayList<String> updatedSrStrings = new ArrayList<>();

    for (StringBuilder s : updatedSr) {
      updatedSrStrings.add(s.toString());
    }

    String chem = "[H] what are some halogens?:::argon and xenon.";
    String law = "[E] how many amendments are there?:::27.";
    String sharks = "[H] what are the different types of sharks?:::bull, hammerhead, "
        + "and great white.";

    expectedSrStrings.add(sharks);
    expectedSrStrings.add(chem);
    expectedSrStrings.add(law);

    assertEquals(updatedSrStrings, expectedSrStrings);
  }

  /**
   * Simulates an easy question being marked hard. Checks if the stats show 1 question answered,
   * 1 question marked hard, 2 total easy questions in the file, and 1 total hard question in
   * and that the question's difficulty is updated after the session in the sr file.
   */
  @Test
  public void testMarkHardPlay() {
    String sequence = "src/test/resources/allEasy.sr\n1\n2\n";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(
        welcome + flashcardNum + monthCard.questionToString() + "\n" + options + studyStats
            + total + "1\n" + easyToHard + "1\n" + hardToEasy + "0\n" + totalEasy + "2\n"
            + totalHard + "1");

    assertEquals(output.toString(), expectedOutput.toString());

    reader = new FileReader(Path.of("src/test/resources/allEasy.sr"));
    ArrayList<StringBuilder> updatedSr = reader.read();
    ArrayList<String> updatedSrStrings = new ArrayList<>();

    for (StringBuilder s : updatedSr) {
      updatedSrStrings.add(s.toString());
    }

    String month = "[H] what is the month?:::may.";
    String dozen = "[E] how many is in a dozen?:::12.";
    String water = "[E] what is water?:::H2O.";

    expectedSrStrings.add(month);
    expectedSrStrings.add(dozen);
    expectedSrStrings.add(water);

    assertEquals(updatedSrStrings, expectedSrStrings);
  }

  /**
   * Simulates an easy question being marked easy. Checks if the stats show 1 question answered,
   * 0 question marked hard to easy, 3 total easy questions in the file, and 0 total hard question
   * in and that the question's difficulty is not updated after the session in the sr file.
   */
  @Test
  public void testMarkAlreadyEasyPlay() {
    String sequence = "src/test/resources/allEasy.sr\n1\n1";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(
        welcome + flashcardNum + monthCard.questionToString() + "\n" + options + studyStats
            + total + "1\n" + easyToHard + "0\n" + hardToEasy + "0\n" + totalEasy + "3\n"
            + totalHard + "0");

    assertEquals(output.toString(), expectedOutput.toString());

    reader = new FileReader(Path.of("src/test/resources/allEasy.sr"));
    ArrayList<StringBuilder> updatedSr = reader.read();
    ArrayList<String> updatedSrStrings = new ArrayList<>();

    for (StringBuilder s : updatedSr) {
      updatedSrStrings.add(s.toString());
    }

    String month = "[E] what is the month?:::may.";
    String dozen = "[E] how many is in a dozen?:::12.";
    String water = "[E] what is water?:::H2O.";

    expectedSrStrings.add(month);
    expectedSrStrings.add(dozen);
    expectedSrStrings.add(water);

    assertEquals(updatedSrStrings, expectedSrStrings);
  }

  /**
   * Simulates a hard question being marked hard. Checks if the stats show 1 question answered,
   * 0 question easy to hard, 0 total easy questions in the file, and 3 total hard question in
   * and that the question's difficulty is not updated after the session in the sr file.
   */
  @Test
  public void testMarkAlreadyHardPlay() {
    String sequence = "src/test/resources/allHard.sr\n1\n2\n";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(
        welcome + flashcardNum + lawCard.questionToString() + "\n" + options + studyStats
            + total + "1\n" + easyToHard + "0\n" + hardToEasy + "0\n" + totalEasy + "0\n"
            + totalHard + "3");

    assertEquals(output.toString(), expectedOutput.toString());

    reader = new FileReader(Path.of("src/test/resources/allHard.sr"));
    ArrayList<StringBuilder> updatedSr = reader.read();
    ArrayList<String> updatedSrStrings = new ArrayList<>();

    for (StringBuilder s : updatedSr) {
      updatedSrStrings.add(s.toString());
    }

    String chem = "[H] what are some halogens?:::argon and xenon.";
    String  law = "[H] how many amendments are there?:::27.";
    String sharksCard = "[H] what are the different types of sharks?:::bull, hammerhead, and great "
        + "white.";

    expectedSrStrings.add(law);
    expectedSrStrings.add(sharksCard);
    expectedSrStrings.add(chem);

    assertEquals(updatedSrStrings, expectedSrStrings);
  }

  /**
   * Simulates a user selecting show answer. Checks that the end stats are correct and that the sr
   * file is unchanged (no change in difficulty) after the session.
   */
  @Test
  public void testShowAnswerPlay() {
    String sequence = "src/test/resources/allEasy.sr\n1\n3\n";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(
        welcome + flashcardNum + monthCard.questionToString() + "\n" + options
            + monthCard.answerToString() + "\n" + studyStats + total + "1\n" + easyToHard + "0\n"
            + hardToEasy + "0\n" + totalEasy + "3\n" + totalHard + "0");

    assertEquals(output.toString(), expectedOutput.toString());

    reader = new FileReader(Path.of("src/test/resources/allEasy.sr"));
    ArrayList<StringBuilder> updatedSr = reader.read();
    ArrayList<String> updatedSrStrings = new ArrayList<>();

    for (StringBuilder s : updatedSr) {
      updatedSrStrings.add(s.toString());
    }

    String month = "[E] what is the month?:::may.";
    String dozen = "[E] how many is in a dozen?:::12.";
    String water = "[E] what is water?:::H2O.";

    expectedSrStrings.add(month);
    expectedSrStrings.add(dozen);
    expectedSrStrings.add(water);

    assertEquals(updatedSrStrings, expectedSrStrings);
  }

  /**
   * Simulates a user quitting the program after changing one question from hard to easy. Checks
   * that the program quits, displays the correct stats, and updates the .sr file with the new
   * difficulty.
   */
  @Test
  public void testQuitProgram() {
    String sequence = "src/test/resources/allHard.sr\n2\n1\n4";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(
        welcome + flashcardNum + lawCard.questionToString() + "\n" + options
            + sharksCard.questionToString() + "\n" + options + studyStats + total + "1\n"
            + easyToHard + "0\n" + hardToEasy + "1\n" + totalEasy + "1\n" + totalHard + "2");

    assertEquals(output.toString(), expectedOutput.toString());

    reader = new FileReader(Path.of("src/test/resources/allHard.sr"));
    ArrayList<StringBuilder> updatedSr = reader.read();
    ArrayList<String> updatedSrStrings = new ArrayList<>();

    for (StringBuilder s : updatedSr) {
      updatedSrStrings.add(s.toString());
    }

    String chem = "[H] what are some halogens?:::argon and xenon.";
    String law = "[E] how many amendments are there?:::27.";
    String sharksCard = "[H] what are the different types of sharks?:::bull, hammerhead, "
        + "and great white.";

    expectedSrStrings.add(sharksCard);
    expectedSrStrings.add(chem);
    expectedSrStrings.add(law);

    assertEquals(updatedSrStrings, expectedSrStrings);
  }

  /**
   * Simulates a user quitting the program after changing last question from hard to easy. Checks
   * that the program quits, displays the correct stats, and updates the .sr file with the new
   * difficulty.
   */
  @Test
  public void testQuitProgramTwo() {
    String sequence = "src/test/resources/allHard.sr\n1\n1\n4";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(
        welcome + flashcardNum + lawCard.questionToString() + "\n" + options + studyStats
            + total + "1\n" + easyToHard + "0\n" + hardToEasy + "1\n" + totalEasy + "1\n"
            + totalHard + "2");

    assertEquals(output.toString(), expectedOutput.toString());

    reader = new FileReader(Path.of("src/test/resources/allHard.sr"));
    ArrayList<StringBuilder> updatedSr = reader.read();
    ArrayList<String> updatedSrStrings = new ArrayList<>();

    for (StringBuilder s : updatedSr) {
      updatedSrStrings.add(s.toString());
    }

    String chem = "[H] what are some halogens?:::argon and xenon.";
    String law = "[E] how many amendments are there?:::27.";
    String sharksCard = "[H] what are the different types of sharks?:::bull, hammerhead, and "
        + "great white.";

    expectedSrStrings.add(sharksCard);
    expectedSrStrings.add(chem);
    expectedSrStrings.add(law);

    assertEquals(updatedSrStrings, expectedSrStrings);
  }

  /**
   * Simulates the user entering a non-existent file and then choosing not to enter a new file.
   * Checks that correct prompts are printed and that the program ends after the user selects "NO".
   */
  @Test
  public void testInvalidFile() {
    String sequence = "file\n2\n";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(welcome + invalidFile);

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Simulates a user entering an invalid file and then choosing to input another file. The user
   * inputs a valid file and begins a study session. User studies one flashcard and chooses to show
   * answer on the card. Checks that the program ends after 1 card and that the correct stats
   * are shown. Also checks that the difficulties are unchanged in the .sr file.
   */
  @Test
  public void testInvalidFileNewFile() {
    String sequence = "file\n1\nsrc/test/resources/allEasy.sr\n1\n3\n";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(
        welcome + invalidFile + welcome + flashcardNum + monthCard.questionToString() + "\n"
            + options + monthCard.answerToString() + "\n" + studyStats + total + "1\n" + easyToHard
            + "0\n" + hardToEasy + "0\n" + totalEasy + "3\n" + totalHard + "0");

    assertEquals(output.toString(), expectedOutput.toString());

    reader = new FileReader(Path.of("src/test/resources/allEasy.sr"));
    ArrayList<StringBuilder> updatedSr = reader.read();
    ArrayList<String> updatedSrStrings = new ArrayList<>();

    for (StringBuilder s : updatedSr) {
      updatedSrStrings.add(s.toString());
    }

    String month = "[E] what is the month?:::may.";
    String dozen = "[E] how many is in a dozen?:::12.";
    String water = "[E] what is water?:::H2O.";

    expectedSrStrings.add(month);
    expectedSrStrings.add(dozen);
    expectedSrStrings.add(water);

    assertEquals(updatedSrStrings, expectedSrStrings);
  }

  /**
   * Simulates a user inputting a random letter in response to the "1. YES  2. NO" for inputting
   * a new file. Checks that the invalid option dialogue occurs.
   */
  @Test
  public void testInvalidInputFile() {
    String sequence = "file\ng\n2";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(welcome + invalidFile + invalidOption + invalidFile);

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Simulates a user inputting a really high number in response to the "1. YES  2. NO" for
   * inputting a new file. Checks that the invalid option dialogue occurs.
   */
  @Test
  public void testInvalidNumInputFile() {
    String sequence = "file\n90\n2";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(welcome + invalidFile + invalidOption + invalidFile);

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Simulates the user entering a non-sr file and then choosing not to enter a new file. Checks
   * that correct prompts are printed and that the program ends after the user selects "NO".
   */
  @Test
  public void testNonSrFile() {
    String sequence = "src/test/resources/pa2_test_files/chem.md\n2\n";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(welcome + invalidFile);

    assertEquals(output.toString(), expectedOutput.toString());
  }

  /**
   * Simulates a user inputting a random letter in response to a flashcard question. Checks that
   * the incorrect option dialogue correctly displays.
   */
  @Test
  public void testInvalidInputLetter() {
    String sequence = "src/test/resources/allHard.sr\n1\nz\n1";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(
        welcome + flashcardNum + lawCard.questionToString() + "\n" + options + invalidOption
            + lawCard.questionToString() + "\n" + options + studyStats + total + "1\n" + easyToHard
            + "0\n" + hardToEasy + "1\n" + totalEasy + "1\n" + totalHard + "2");

    assertEquals(output.toString(), expectedOutput.toString());

    reader = new FileReader(Path.of("src/test/resources/allHard.sr"));
    ArrayList<StringBuilder> updatedSr = reader.read();
    ArrayList<String> updatedSrStrings = new ArrayList<>();

    for (StringBuilder s : updatedSr) {
      updatedSrStrings.add(s.toString());
    }

    String chem = "[H] what are some halogens?:::argon and xenon.";
    String law = "[E] how many amendments are there?:::27.";
    String sharksCard = "[H] what are the different types of sharks?:::bull, hammerhead, and "
        + "great white.";

    expectedSrStrings.add(sharksCard);
    expectedSrStrings.add(chem);
    expectedSrStrings.add(law);

    assertEquals(updatedSrStrings, expectedSrStrings);
  }

  /**
   * Simulates a user inputting a random number in response to a flashcard question. Checks that
   * the incorrect option dialogue correctly displays.
   */
  @Test
  public void testInvalidInputNumber() {
    String sequence = "src/test/resources/allHard.sr\n1\n8\n1";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(
        welcome + flashcardNum + lawCard.questionToString() + "\n" + options + invalidOption
            + lawCard.questionToString() + "\n" + options + studyStats + total + "1\n" + easyToHard
            + "0\n" + hardToEasy + "1\n" + totalEasy + "1\n" + totalHard + "2");

    assertEquals(output.toString(), expectedOutput.toString());

    reader = new FileReader(Path.of("src/test/resources/allHard.sr"));
    ArrayList<StringBuilder> updatedSr = reader.read();
    ArrayList<String> updatedSrStrings = new ArrayList<>();

    for (StringBuilder s : updatedSr) {
      updatedSrStrings.add(s.toString());
    }

    String chem = "[H] what are some halogens?:::argon and xenon.";
    String law = "[E] how many amendments are there?:::27.";
    String sharksCard = "[H] what are the different types of sharks?:::bull, hammerhead, and great "
        + "white.";

    expectedSrStrings.add(sharksCard);
    expectedSrStrings.add(chem);
    expectedSrStrings.add(law);

    assertEquals(updatedSrStrings, expectedSrStrings);
  }

  /**
   * Simulates a user inputting a letter for the number of flashcards. Checks that correct invalid
   * option message is shown.
   */
  @Test
  public void testLetterFlashcardNum() {
    String sequence = "src/test/resources/allHard.sr\nz\n1\n1";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(
        welcome + flashcardNum + invalidOption + flashcardNum + lawCard.questionToString() + "\n"
            + options + studyStats + total + "1\n" + easyToHard + "0\n" + hardToEasy + "1\n"
            + totalEasy + "1\n" + totalHard + "2");

    assertEquals(output.toString(), expectedOutput.toString());

    reader = new FileReader(Path.of("src/test/resources/allHard.sr"));
    ArrayList<StringBuilder> updatedSr = reader.read();
    ArrayList<String> updatedSrStrings = new ArrayList<>();

    for (StringBuilder s : updatedSr) {
      updatedSrStrings.add(s.toString());
    }

    String chem = "[H] what are some halogens?:::argon and xenon.";
    String law = "[E] how many amendments are there?:::27.";
    String sharksCard = "[H] what are the different types of sharks?:::bull, hammerhead, and "
        + "great white.";

    expectedSrStrings.add(sharksCard);
    expectedSrStrings.add(chem);
    expectedSrStrings.add(law);

    assertEquals(updatedSrStrings, expectedSrStrings);
  }

  /**
   * Simulates a user inputting a letter for the number of flashcards. Checks that correct invalid
   * option message is shown.
   */
  @Test
  public void testNegFlashcardNum() {
    String sequence = "src/test/resources/allHard.sr\n-5\n1\n1";
    Readable input = new StringReader(sequence);

    StudySessionController controller = new StudySessionController(input, output, new Random(1));

    controller.executeProgram();

    expectedOutput = new StringBuilder(
        welcome + flashcardNum + invalidOption + flashcardNum + lawCard.questionToString() + "\n"
            + options + studyStats + total + "1\n" + easyToHard + "0\n" + hardToEasy + "1\n"
            + totalEasy + "1\n" + totalHard + "2");

    assertEquals(output.toString(), expectedOutput.toString());

    reader = new FileReader(Path.of("src/test/resources/allHard.sr"));
    ArrayList<StringBuilder> updatedSr = reader.read();
    ArrayList<String> updatedSrStrings = new ArrayList<>();

    for (StringBuilder s : updatedSr) {
      updatedSrStrings.add(s.toString());
    }

    String chem = "[H] what are some halogens?:::argon and xenon.";
    String law = "[E] how many amendments are there?:::27.";
    String sharksCard = "[H] what are the different types of sharks?:::bull, hammerhead, and great "
        + "white.";

    expectedSrStrings.add(sharksCard);
    expectedSrStrings.add(chem);
    expectedSrStrings.add(law);

    assertEquals(updatedSrStrings, expectedSrStrings);
  }
}