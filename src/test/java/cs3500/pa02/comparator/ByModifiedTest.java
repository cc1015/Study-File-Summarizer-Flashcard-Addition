package cs3500.pa02.comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa02.model.sgmodel.MdFile;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Comparator;
import org.junit.jupiter.api.Test;

/**
 * Test class for the ByModified class.
 */
class ByModifiedTest {
  FileTime firstTime = FileTime.from(Instant.parse("2022-05-14T04:00:00Z"));
  FileTime secondTime = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
  FileTime createdTime = FileTime.from(Instant.parse("2019-05-14T12:00:00Z"));
  MdFile modifiedLessRecent = new MdFile("first", "ex/path", createdTime, firstTime);
  MdFile modifiedMoreRecent = new MdFile("first", "ex/path", createdTime, secondTime);
  Comparator<MdFile> comparator = new ByModified();

  /**
   * Check if the compare method correctly returns an int greater than zero when the file that was
   * modified more recently is being passed in as the first file.
   */
  @Test
  public void testModifiedMoreRecent() {
    assertTrue(comparator.compare(modifiedMoreRecent, modifiedLessRecent) > 0);
  }

  /**
   * Check if the compare method correctly returns an int greater than zero when the file that was
   * modified less recently is being passed in as the first file.
   */
  @Test
  public void testModifiedLessRecent() {
    assertTrue(comparator.compare(modifiedLessRecent, modifiedMoreRecent) < 0);
  }

  /**
   * Check if the compare method correctly returns an int greater than zero when the same file is
   * passed in for both file parameters (same modification time).
   */
  @Test
  public void testSameModifiedTime() {
    assertEquals(comparator.compare(modifiedLessRecent, modifiedLessRecent), 0);
  }
}