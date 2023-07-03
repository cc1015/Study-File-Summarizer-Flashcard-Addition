package cs3500.pa02.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.model.sgmodel.MdFile;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

/**
 * Test class for the ByName class.
 */
class ByNameTest {
  FileTime time = FileTime.from(Instant.parse("2022-05-14T04:00:00Z"));
  MdFile alphaFirst = new MdFile("apple", "ex/path", time, time);
  MdFile alphaSecond = new MdFile("banana", "ex/path", time, time);
  Comparator<MdFile> comparator = new ByName();

  /**
   * Check if the compare method correctly returns an int less than zero when the file that is
   * alphabetically first is being passed in as the first file.
   */
  @Test
  public void testCreatedFirst() {
    assertTrue(comparator.compare(alphaFirst, alphaSecond) < 0);
  }

  /**
   * Check if the compare method correctly returns an int greater than zero when the file that is
   * alphabetically second is being passed in as the first file.
   */
  @Test
  public void testCreatedSecond() {
    assertTrue(comparator.compare(alphaSecond, alphaFirst) > 0);
  }

  /**
   * Check if the compare method correctly returns 0 when the same file is passed in for both file
   * parameters (same name).
   */
  @Test
  public void testCreatedSame() {
    assertEquals(comparator.compare(alphaFirst, alphaFirst), 0);
  }
}