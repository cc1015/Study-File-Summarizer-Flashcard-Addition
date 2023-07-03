package cs3500.pa02.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.model.sgmodel.MdFile;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

/**
 * Test class for the ByCreated class.
 */
class ByCreatedTest {
  FileTime firstCreatedTime = FileTime.from(Instant.parse("2022-05-14T04:00:00Z"));
  FileTime secondCreatedTime = FileTime.from(Instant.parse("2023-01-14T12:00:00Z"));
  FileTime modifiedTime = FileTime.from(Instant.parse("2023-05-14T04:00:00Z"));
  MdFile createdFirst = new MdFile("first", "ex/path", firstCreatedTime,
      modifiedTime);
  MdFile createdSecond = new MdFile("first", "ex/path", secondCreatedTime,
      modifiedTime);
  Comparator<MdFile> comparator = new ByCreated();

  /**
   * Check if the compare method correctly returns an int less than zero when the file that was
   * created first is being passed in as the first file.
   */
  @Test
  public void testCreatedFirst() {
    assertTrue(comparator.compare(createdFirst, createdSecond) < 0);
  }

  /**
   * Check if the compare method correctly returns an int greater than zero when the file that was
   * created second is being passed in as the first file.
   */
  @Test
  public void testCreatedSecond() {
    assertTrue(comparator.compare(createdSecond, createdFirst) > 0);
  }

  /**
   * Check if the compare method correctly returns 0 when the same file is passed in for both file
   * parameters (same creation time).
   */
  @Test
  public void testCreatedSame() {
    assertEquals(comparator.compare(createdFirst, createdFirst), 0);
  }
}