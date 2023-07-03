package cs3500.pa02.model.sgmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.attribute.FileTime;
import java.time.Instant;
import org.junit.jupiter.api.Test;

/**
 * Test class for the MdFile class.
 */
class MdFileTest {
  FileTime createdTime = FileTime.from(Instant.parse("2022-05-14T04:00:00Z"));
  FileTime modifyTime = FileTime.from(Instant.parse("2023-05-14T12:00:00Z"));
  MdFile exampleMd = new MdFile("example.md", "ex/path", createdTime, modifyTime);

  /**
   * Checks if Constructor constructs and initializes properly
   */
  @Test
  public void testConstruct() {
    assertNotNull(exampleMd);
  }

  /**
   * Checks if constructor throws IllegalArgumentException for a creation time after modified time
   */
  @Test
  public void testInvalidTimes() {
    assertThrows(IllegalArgumentException.class,
        () -> new MdFile("file", "path", modifyTime, createdTime));
  }

  /**
   * Checks if getName correctly returns name
   */
  @Test
  public void testGetName() {
    assertEquals(exampleMd.getName(), "example.md");
  }

  /**
   * Checks if getPath correctly returns path
   */
  @Test
  public void testGetPath() {
    assertEquals(exampleMd.getPath(), "ex/path");
  }

  /**
   * Checks if getCreationTime correctly return creation time
   */
  @Test
  public void testGetCreationTime() {
    assertEquals(exampleMd.getCreationTime(), createdTime);
  }

  /**
   * Checks if getModifyTime correctly returns modify time
   */
  @Test
  public void testGetModifyTime() {
    assertEquals(exampleMd.getModifyTime(), modifyTime);
  }
}