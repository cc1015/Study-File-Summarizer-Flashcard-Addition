package cs3500.pa02.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import cs3500.pa02.model.ssmodel.Difficulty;
import cs3500.pa02.model.ssmodel.Flashcard;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents a test class for the SrParser class.
 */
class SrParserTest {
  SrParser parser;
  ArrayList<StringBuilder> strings;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    this.parser = new SrParser();
    this.strings = new ArrayList<>();
  }

  /**
   * Checks that the constructor initializes properly
   */
  @Test
  public void testConstruct() {
    StudyGuideParser fs = new StudyGuideParser();
    assertNotNull(fs);
  }

  /**
   * Checks that parse correctly parses an easy and a hard question.
   */
  @Test
  public void testParse() {
    ArrayList<Flashcard> expectedList = new ArrayList<>();
    expectedList.add(new Flashcard("is this a hard question?", "yes.", Difficulty.HARD));

    expectedList.add(new Flashcard("is this an easy question?", "yes.", Difficulty.EASY));

    strings.add(new StringBuilder("[H] is this a hard question?:::yes."));
    strings.add(new StringBuilder("[E] is this an easy question?:::yes."));

    assertEquals(parser.parse(strings), expectedList);
  }
}