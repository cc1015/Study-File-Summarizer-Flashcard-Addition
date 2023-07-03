package cs3500.pa02.creator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.model.sgmodel.MdFile;
import cs3500.pa02.model.ssmodel.Difficulty;
import cs3500.pa02.model.ssmodel.Flashcard;
import cs3500.pa02.model.ssmodel.FlashcardBank;
import cs3500.pa02.parser.QandAnsParser;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for FlashcardBankCreator class.
 */
class FlashcardBankCreatorTest {
  FileTime mockTime;
  MdFile chem;
  MdFile law;
  MdFile python;
  MdFile sharks;
  ArrayList<MdFile> files;
  FlashcardBankCreator creator;
  Flashcard chemCard;
  Flashcard lawCard;
  Flashcard pythonCard;
  Flashcard sharksCard;
  FlashcardBank bank;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    mockTime = FileTime.from(Instant.parse("2022-05-14T04:00:00Z"));
    chem = new MdFile("chem", "src/test/resources/pa2_test_files/chem.md", mockTime,
        mockTime);
    law = new MdFile("law", "src/test/resources/pa2_test_files/law.md", mockTime,
        mockTime);
    python =
        new MdFile("python", "src/test/resources/pa2_test_files/python.md", mockTime, mockTime);
    sharks =
        new MdFile("sharks", "src/test/resources/pa2_test_files/sharks.md", mockTime, mockTime);

    files = new ArrayList<>();
    files.add(chem);
    files.add(law);
    files.add(python);
    files.add(sharks);

    creator = new FlashcardBankCreator(files, new QandAnsParser());

    chemCard = new Flashcard("what are some halogens?", "argon and xenon.", Difficulty.HARD);
    lawCard = new Flashcard("how many amendments are there?", "27.", Difficulty.HARD);
    pythonCard = new Flashcard("what can you use to read csv files?", "pandas.", Difficulty.HARD);
    sharksCard = new Flashcard("what are the different types of sharks?",
        "bull, hammerhead, and great white.", Difficulty.HARD);

    ArrayList<Flashcard> cards = new ArrayList<>();
    cards.add(chemCard);
    cards.add(lawCard);
    cards.add(pythonCard);
    cards.add(sharksCard);

    bank = new FlashcardBank(cards);
  }

  /**
   * Tests that a StudyBank is created correctly.
   */
  @Test
  public void testCreateStudyBank() {
    assertEquals(creator.createStudyBank(), bank);
  }
}