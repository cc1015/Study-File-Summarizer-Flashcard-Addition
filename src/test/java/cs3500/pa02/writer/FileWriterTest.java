package cs3500.pa02.writer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa02.model.sgmodel.Header;
import cs3500.pa02.model.sgmodel.ImportantInfo;
import cs3500.pa02.model.sgmodel.StudyGuide;
import cs3500.pa02.model.sgmodel.StudyGuideContent;
import cs3500.pa02.model.ssmodel.Difficulty;
import cs3500.pa02.model.ssmodel.Flashcard;
import cs3500.pa02.model.ssmodel.FlashcardBank;
import cs3500.pa02.reader.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Test class for the FileWriter class.
 */
class FileWriterTest {
  FileWriter writer = new FileWriter();
  FileReader reader;


  /**
   * Checks that StudyGuideContent is written correctly.
   */
  @Test
  public void testWriteStudyGuide() {
    Header h1 = new Header("discrete structures", 1);
    ImportantInfo i1 = new ImportantInfo("important in computer and data sciences");
    Header h2 = new Header("probability", 2);
    Header h3 = new Header("combination", 3);
    ImportantInfo i2 = new ImportantInfo("n choose k");
    ImportantInfo i3 = new ImportantInfo("example");

    ArrayList<StudyGuideContent> content = new ArrayList<>();
    content.add(h1);
    content.add(i1);
    content.add(h2);
    content.add(h3);
    content.add(i2);
    content.add(i3);

    StudyGuide sg = new StudyGuide(content);

    try {
      writer.write("src/test/resources/writer/sg.md", sg);
    } catch (IOException e) {
      fail();
    }

    reader = new FileReader(Path.of("src/test/resources/writer/sg.md"));
    ArrayList<StringBuilder> sgStringBuilders = reader.read();
    ArrayList<String> sgStrings = new ArrayList<>();

    for (StringBuilder s : sgStringBuilders) {
      sgStrings.add(s.toString());
    }

    ArrayList<String> expectedSgStrings = new ArrayList<>();

    expectedSgStrings.add("# discrete structures");
    expectedSgStrings.add("- important in computer and data sciences");
    expectedSgStrings.add("");
    expectedSgStrings.add("## probability");
    expectedSgStrings.add("");
    expectedSgStrings.add("### combination");
    expectedSgStrings.add("- n choose k");
    expectedSgStrings.add("- example");

    assertEquals(sgStrings, expectedSgStrings);
  }

  /**
   * Checks that FlashcardBank is written correctly.
   */
  @Test
  public void testWriteFlashcardBank() {
    Flashcard f1 = new Flashcard("question1", "answer1", Difficulty.HARD);
    Flashcard f2 = new Flashcard("question2", "answer2", Difficulty.EASY);
    Flashcard f3 = new Flashcard("question3", "answer3", Difficulty.HARD);
    Flashcard f4 = new Flashcard("question4", "answer4", Difficulty.EASY);
    Flashcard f5 = new Flashcard("question5", "answer5", Difficulty.HARD);

    ArrayList<Flashcard> cards = new ArrayList<>();
    cards.add(f1);
    cards.add(f2);
    cards.add(f3);
    cards.add(f4);
    cards.add(f5);

    FlashcardBank bank = new FlashcardBank(cards);

    try {
      writer.write("src/test/resources/writer/bank.sr", bank);
    } catch (IOException e) {
      fail();
    }

    reader = new FileReader(Path.of("src/test/resources/writer/bank.sr"));
    ArrayList<StringBuilder> bankStringBuilders = reader.read();
    ArrayList<String> bankStrings = new ArrayList<>();

    for (StringBuilder s : bankStringBuilders) {
      bankStrings.add(s.toString());
    }

    ArrayList<String> expectedBankStrings = new ArrayList<>();

    expectedBankStrings.add(f1.toString());
    expectedBankStrings.add(f3.toString());
    expectedBankStrings.add(f5.toString());
    expectedBankStrings.add(f2.toString());
    expectedBankStrings.add(f4.toString());

    assertEquals(bankStrings, expectedBankStrings);
  }
}