package cs3500.pa02.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.model.sgmodel.Header;
import cs3500.pa02.model.sgmodel.ImportantInfo;
import cs3500.pa02.model.sgmodel.StudyGuideContent;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the MdParser class.
 */
class StudyGuideParserTest {
  StudyGuideParser parser;
  ArrayList<StringBuilder> strings;
  StringBuilder mainHeaderString;
  Header mainHeader;
  Header subHeader;
  ArrayList<StudyGuideContent> summary;

  /**
   * Initialize data; creates a study guide with a main header (# header)
   */
  @BeforeEach
  public void initData() {
    this.parser = new StudyGuideParser();
    this.strings = new ArrayList<>();
    this.mainHeaderString = new StringBuilder("# header");
    this.strings.add(mainHeaderString);

    this.mainHeader = new Header("header", 1);
    this.subHeader = new Header("subheader", 2);

    this.summary = new ArrayList<>();
    summary.add(mainHeader);
  }

  /**
   * Checks that a header is parsed correctly
   */
  @Test
  public void testHeaderParse() {
    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that important info in one line is parsed correctly
   */
  @Test
  public void testInfoParse() {
    StringBuilder s1 =
        new StringBuilder("- this is an example and here is [[important information]]");
    strings.remove(mainHeaderString);
    strings.add(s1);

    ImportantInfo i1 = new ImportantInfo("important information");
    summary.remove(mainHeader);
    summary.add(i1);

    assertEquals(parser.parse(strings), summary);
  }


  /**
   * Checks that important information spanning two lines gets parsed correctly and saved as one
   * piece of important information
   * Input:
   * # header
   * - this is an example and here is [[important information
   * that spans two lines.]]
   * Expected output:
   * # header
   * - important information that spans two lines
   */
  @Test
  public void testTwoLineImpInfo() {
    StringBuilder s1 =
        new StringBuilder("- this is an example and here is [[important information ");
    StringBuilder s2 = new StringBuilder("that spans two lines.]]");

    strings.add(s1);
    strings.add(s2);

    ImportantInfo i1 = new ImportantInfo("important information that spans two lines.");
    summary.add(i1);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a line that has two pieces of important information gets parsed correctly and
   * saved as two separate pieces of important information
   * Input:
   * # header
   * - this is [[important info 1]] and [[important info 2]]
   * ## subheader
   * - this is [[important info 3]] and [[important info 4]]
   * Expected output:
   * # header
   * - important info 1
   * - important info 2
   * ## subheader
   * - important info
   * - important info
   */
  @Test
  public void testTwoImpInfoInLine() {
    StringBuilder s1 = new StringBuilder("- this is [[important info 1]] and [[important info 2]]");
    StringBuilder s2 = new StringBuilder("## subheader");
    StringBuilder s3 = new StringBuilder("- this is [[important info 3]] and [[important info 4]]");

    strings.add(s1);
    strings.add(s2);
    strings.add(s3);

    ImportantInfo i1 = new ImportantInfo("important info 1");
    ImportantInfo i2 = new ImportantInfo("important info 2");
    ImportantInfo i3 = new ImportantInfo("important info 3");
    ImportantInfo i4 = new ImportantInfo("important info 4");
    summary.add(i1);
    summary.add(i2);
    summary.add(subHeader);
    summary.add(i3);
    summary.add(i4);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a line that has a # (not important) gets parsed correctly and not mistaken
   * for a header
   * Input:
   * # header
   * - this is info # 1: a line with a pound sign
   * ## subheader
   * - this is [[important info]]
   * Expected output:
   * # header
   * ## header
   * - important info
   */
  @Test
  public void testPoundInBullet() {
    StringBuilder s1 = new StringBuilder("- this is info # 1: a line with a pound sign");
    StringBuilder s2 = new StringBuilder("## subheader");
    StringBuilder s3 = new StringBuilder("- this is [[important info]]");

    strings.add(s1);
    strings.add(s2);
    strings.add(s3);

    ImportantInfo i = new ImportantInfo("important info");
    summary.add(subHeader);
    summary.add(i);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a line that has a # at the start of a line (not important) gets parsed correctly
   * and not mistaken for a header
   * Input:
   * # header
   * - this is [[important info]] and also has a pound sign:
   * #.
   * ## subheader
   * Expected output:
   * # header
   * - important info
   * ## header
   */
  @Test
  public void testPoundBeginLine() {
    StringBuilder s1 = new StringBuilder("- this is [[important info]] and also has a pound sign:");
    StringBuilder s2 = new StringBuilder("#.");
    StringBuilder s3 = new StringBuilder("## subheader");

    strings.add(s1);
    strings.add(s2);
    strings.add(s3);

    ImportantInfo i = new ImportantInfo("important info");
    summary.add(i);
    summary.add(subHeader);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a line that has a # in between [[ ]] gets parsed correctly and saved as
   * important information
   * Input:
   * # header
   * - this is [[important info with a pound sign: #]]
   * - this is more [[# important info]]
   * ## subheader
   * - this is [[subheader important info with a pound sign: #]]
   * Expected output:
   * # header
   * - important info with a pound sign: #
   * - # important info
   * ## header
   * - subheader important info with a pound sign: #
   */
  @Test
  public void testPoundImpInfo() {
    StringBuilder s1 = new StringBuilder("- this is [[important info with a pound sign: #]]");
    StringBuilder s2 = new StringBuilder("- this is more [[# important info]]");
    StringBuilder s3 = new StringBuilder("## subheader");
    StringBuilder s4 =
        new StringBuilder("- this is [[subheader important info with a pound sign: #]]");

    strings.add(s1);
    strings.add(s2);
    strings.add(s3);
    strings.add(s4);

    ImportantInfo i1 = new ImportantInfo("important info with a pound sign: #");
    ImportantInfo i2 = new ImportantInfo("# important info");
    ImportantInfo i3 = new ImportantInfo("subheader important info with a pound sign: #");
    summary.add(i1);
    summary.add(i2);
    summary.add(subHeader);
    summary.add(i3);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a header with no bullet and another header underneath it gets parsed correctly
   * as two separate headers
   * Input:
   * # header
   * ## subheader
   * Expected output:
   * # header
   * ## subheader
   */
  @Test
  public void testHeaderNoBullet() {
    StringBuilder s1 = new StringBuilder("## subheader");

    strings.add(s1);

    summary.add(subHeader);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a bullet with important information with no header above and another bullet
   * underneath with no important information get parsed correctly
   * Input:
   * - this is [[a bullet with no header with important info]]
   * - this is a bullet with no header and no important info
   * Expected output:
   * - a bullet with no header with important info
   */
  @Test
  public void testBulletWithNoHeader() {
    StringBuilder s1 =
        new StringBuilder("- this is [[a bullet with no header with important info]]");
    StringBuilder s2 = new StringBuilder("- this is a bullet with no header and no important info");

    strings.remove(mainHeaderString);
    strings.add(s1);
    strings.add(s2);

    ImportantInfo i1 = new ImportantInfo("a bullet with no header with important info");
    summary.remove(mainHeader);
    summary.add(i1);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a single bracket gets parsed correctly and not mistaken as important information
   * Input:
   * # header
   * - this is a line with a single bracket [ ]
   * - this is [[important info]]
   * Expected output:
   * # header
   * - important info
   */
  @Test
  public void testSingleBracket() {
    StringBuilder s1 =
        new StringBuilder("- this is a line with a single bracket [ ]");
    StringBuilder s2 = new StringBuilder("- this is [[important info]]");

    strings.add(s1);
    strings.add(s2);

    ImportantInfo i = new ImportantInfo("important info");
    summary.add(i);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a single bracket in important information gets parsed correctly and
   * not mistaken as more important information
   * Input:
   * # header
   * - this is [[a line with [single brackets] inside]]
   * Expected output:
   * # header
   * - a line with [single brackets] inside
   */
  @Test
  public void testSingBracketInImpInfo() {
    StringBuilder s1 =
        new StringBuilder("- this is [[a line with [single brackets] inside]]");

    strings.add(s1);

    ImportantInfo i = new ImportantInfo("a line with [single brackets] inside");
    summary.add(i);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a line with a [[ but no ]] gets parsed correctly and not mistaken as important
   * information
   * Input:
   * # header
   * - this is [[a line with no ending double bracket
   * Expected output:
   * # header
   */
  @Test
  public void testNoEndingDoubleBracket() {
    StringBuilder s1 =
        new StringBuilder("- this is [[a line with no ending double bracket");

    strings.add(s1);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a line with a ]] but no [[ gets parsed correctly and not mistaken as important
   * information
   * Input:
   * # header
   * - this is a line with only end ]] double brackets
   * Expected output:
   * # header
   */
  @Test
  public void testNoStartDoubleBracket() {
    StringBuilder s1 =
        new StringBuilder("- this is a line with only end ]] double brackets");

    strings.add(s1);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a header with a # gets parsed correctly and not mistaken for another header
   * Input:
   * # header
   * ## subheader with # pound sign
   * Expected output:
   * # header
   * ## subheader with # pound sign
   */
  @Test
  public void testPoundInHeader() {
    StringBuilder s1 =
        new StringBuilder("## subheader with # pound sign");

    strings.add(s1);

    Header h = new Header("subheader with # pound sign", 2);
    summary.add(h);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a header that is just #s and no space after gets parsed correctly and saved
   * as a header
   * Input:
   * # header
   * ####
   * Expected output:
   * # header
   * ####
   */
  @Test
  public void testNoTextHeaderNoSpace() {
    StringBuilder s1 = new StringBuilder("####");

    strings.add(s1);

    Header h = new Header("", 4);
    summary.add(h);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a header that is just #s and a space after gets parsed correctly and saved
   * as a header
   * Input:
   * # header
   * #####
   * Expected output:
   * # header
   * #####
   */
  @Test
  public void testNoTextHeaderSpace() {
    StringBuilder s1 = new StringBuilder("##### ");

    strings.add(s1);

    Header h = new Header("", 5);
    summary.add(h);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a line with too many #s with text does not get parsed as a header
   * Input:
   * # header
   * ######## invalid header
   * Expected output:
   * # header
   */
  @Test
  public void testTooManyPound() {
    StringBuilder s1 = new StringBuilder("######## invalid header");

    strings.add(s1);


    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a line with too many #s and no text does not get parsed as a header
   * Input:
   * # header
   * ########
   * Expected output:
   * # header
   */
  @Test
  public void testTooManyPoundNoText() {
    StringBuilder s1 = new StringBuilder("########");

    strings.add(s1);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a line with #s and text not seperated by a space does not get parsed as a header
   * Input:
   * # header
   * ##bad header
   * Expected output:
   * # header
   */
  @Test
  public void testNoSpaceAfterPoundWithText() {
    StringBuilder s1 = new StringBuilder("##bad header");

    strings.add(s1);

    assertEquals(parser.parse(strings), summary);
  }

  /**
   * Checks that a q and a block is not parsed as important info
   */
  @Test
  public void testQandA() {
    StringBuilder s1 = new StringBuilder("[[question?:::answer)");

    strings.add(s1);

    assertEquals(parser.parse(strings), summary);
  }

  /*
  edge cases tested:
  - two line important info
  - two important info in one line
  - # in bullet
  - # at start of line
  - # in important info
  - header with no bullet under
  - bullet with no header
  - indented bullet
  - single bracket
  - single bracket inside imp info
  - no ending double bracket
  - only ending double bracket
  - # in header
  - # only header no space after
  - # only header space after
  - too many #
  - no space after # with text after
  - qAnda block not saved
 */
}