package cs3500.pa02.model.sgmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Test class for the ImportantInfo class.
 */
class ImportantInfoTest {
  ImportantInfo blankInfo = new ImportantInfo("");
  ImportantInfo impInfo = new ImportantInfo("example info");
  ImportantInfo impInfo2 = new ImportantInfo("example info");

  /**
   * Checks if ImportantInfo is initialized and constructed properly.
   */
  @Test
  public void testConstructInfo() {
    ImportantInfo i = new ImportantInfo("i");
    assertNotNull(i);
  }

  /**
   * Checks if toString() returns "- " on ImportantInfo that has "" as content.
   */
  @Test
  public void testToStringBlank() {
    assertEquals(blankInfo.toString(), "- ");
  }

  /**
   * Checks if toString() returns "- example info" on ImportantInfo that has "example info"
   * as content.
   */
  @Test
  public void testToStringInfo() {
    assertEquals(impInfo.toString(), "- example info");
  }

  /**
   * Checks that equals returns true for two ImportantInfos with the same String content
   */
  @Test
  public void testEqualsTrue() {
    assertTrue(impInfo.equals(impInfo2));
  }

  /**
   * Checks that equals returns false for two ImportantInfos with different String content
   */
  @Test
  public void testEqualsFalse() {
    assertFalse(impInfo.equals(blankInfo));
  }
}