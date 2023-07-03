package cs3500.pa02.creator;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa02.reader.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * Test class for StudyCollectionCreator abstract class.
 */
class StudyCollectionCreatorTest {
  FileReader reader;

  /**
   * Tests readInput and checks that it returns the expected list of strings.
   */
  @Test
  public void testReadInput() {
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
}