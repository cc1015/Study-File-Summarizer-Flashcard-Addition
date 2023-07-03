package cs3500.pa02.model.sgmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StudyGuideContentTest {
  Header h1 = new Header("header", 2);
  Header h2 = new Header("header", 2);
  ImportantInfo i1 = new ImportantInfo("info");

  /**
   * Check that getContent returns correct string of content
   */
  @Test
  public void testGetContent() {
    assertEquals(h1.getContent(), "header");
    assertEquals(i1.getContent(), "info");
  }

  /**
   * Check that sameAbContent returns true for two different headers that have the same content
   * and number of #
   */
  @Test
  public void testEqualsPass() {
    assertTrue(h1.equals(h2));
  }

  /**
   * Check that samAbContent returns false for a header and important info
   */
  @Test
  public void testEqualsFail() {
    assertFalse(i1.equals(h1));
  }

  /**
   * Check that samAbContent returns false for a header and a String
   */
  @Test
  public void testEqualsFailTwo() {
    assertFalse(i1.equals("String"));
  }
}
