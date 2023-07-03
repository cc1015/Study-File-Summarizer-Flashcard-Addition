package cs3500.pa02.model.sgmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for the Header class.
 */
class HeaderTest {
  Header mainHeader = new Header("example header", 1);
  Header mainHeader2 = new Header("example header", 1);
  Header subHeader = new Header("example indented header", 2);
  Header subHeader2 = new Header("example header", 2);
  Header subSubHeader = new Header("example header indented twice", 3);

  /**
   * Check if a Header with valid content and hashes is initialized and constructed properly
   */
  @Test
  public void testValidConstruct() {
    Header h = new Header("h", 2);
    assertNotNull(h);
  }

  /**
   * Checks if a Header being made with zero hashes throws an IllegalArgumentException.
   */
  @Test
  public void testInvalidIndent1() {
    assertThrows(
        IllegalArgumentException.class, () -> new Header("bad example", 0)
    );
  }

  /**
   * Checks if a Header being made with negative three hashes throws an IllegalArgumentException.
   */
  @Test
  public void testInvalidIndent2() {
    assertThrows(
        IllegalArgumentException.class, () -> new Header("bad example", -3)
    );
  }

  /**
   * Checks if a Header being made with eight hashes throws an IllegalArgumentException.
   */
  @Test
  public void testInvalidIndent3() {
    assertThrows(
        IllegalArgumentException.class, () -> new Header("bad example", 8)
    );
  }

  /**
   * Checks if writeContent() returns a blank line with "# example header" under it on a Header
   * with content " example header" and a hashNum of 1.
   */
  @Test
  public void testToStringOneHash() {
    assertEquals(mainHeader.toString(), "\n# example header");
  }

  /**
   * Checks if toString() returns a blank line with "## example indented header" under it on a
   * Header with content " example indented header" and a hashNum of 2.
   */
  @Test
  public void testToStringTwoHash() {
    assertEquals(subHeader.toString(), "\n## example indented header");
  }

  /**
   * Checks if toString() returns a blank line with "### example header indented twice" under
   * it on a Header with content " example header indented twice" and a hashNum of 3.
   */
  @Test
  public void testToStringThreeHash() {
    assertEquals(subSubHeader.toString(), "\n### example header indented twice");
  }

  /**
   * Checks that equals returns true for two headers with the same content String and
   * # count.
   */
  @Test
  public void testEqualsContentTrue() {
    assertTrue(mainHeader.equals(mainHeader2));
  }

  /**
   * Checks that equals returns false for two headers with different content String but the
   * same # count
   */
  @Test
  public void testEqualsFalse() {
    assertFalse(subHeader.equals(subHeader2));
  }

  /**
   * Checks that equals returns false for two headers with the same content String but
   * different # count
   */
  @Test
  public void testEqualsFalse2() {
    assertFalse(mainHeader2.equals(subHeader2));
  }
}