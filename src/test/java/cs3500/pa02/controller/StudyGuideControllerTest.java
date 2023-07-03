package cs3500.pa02.controller;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

/**
 * Test class for StudyGuideController class
 */
class StudyGuideControllerTest {
  /*
  example root directory layout:
  ex_root
    arrays.md
    bio.md
    image.png
    lecture.pdf
    vectors.md
   */

  StudyGuideController byName = new StudyGuideController("src/test/resources/ex_root",
      "filename", "src/test/resources/ex_sds/filename_ex.md");
  StudyGuideController byModified =
      new StudyGuideController("src/test/resources/ex_root", "modified",
          "src/test/resources/ex_sds/modified_ex.md");
  StudyGuideController byCreated = new StudyGuideController("src/test/resources/ex_root",
      "created", "src/test/resources/ex_sds/created_ex.md");
  StudyGuideController qaBlock =
      new StudyGuideController("src/test/resources/pa2_test_files", "created",
          "src/test/resources/pa2_test_files/qa.md");
  StudyGuideController exists = new StudyGuideController("src/test/resources/ex_root",
      "created", "src/test/resources/existing_sd/sd.md");

  Path filenameEx = Path.of("src/test/resources/ex_sds/filename_ex.md");
  Path modifiedEx = Path.of("src/test/resources/ex_sds/modified_ex.md");
  Path createdEx = Path.of("src/test/resources/ex_sds/created_ex.md");
  Path qaExMd = Path.of("src/test/resources/pa2_test_files/qa.md");
  Path qaExSr = Path.of("src/test/resources/pa2_test_files/qa.sr");
  Path alreadyExist = Path.of("src/test/resources/existing_sd/sd.md");

  /**
   * Checks that the FileCreator Constructor initializes correctly
   */
  @Test
  public void testConstruct() {
    assertNotNull(byName);
    assertNotNull(byModified);
    assertNotNull(byCreated);
    assertNotNull(exists);
  }

  /**
   * Checks that file is created for 'by name' sorting mechanism and checks that the content
   * is formatted in the correct order (no q and a blocks)
   */
  @Test
  public void testCreateStudyGuideFileName() {
    assertFalse(Files.exists(filenameEx));
    byName.executeProgram();
    assertTrue(Files.exists(filenameEx));
    try {
      Files.delete(filenameEx);
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Checks that file is created for by modified sorting mechanism (no q and a blocks)
   */
  @Test
  public void testCreateStudyGuideFileModified() {
    assertFalse(Files.exists(modifiedEx));
    byModified.executeProgram();
    assertTrue(Files.exists(modifiedEx));
    try {
      Files.delete(modifiedEx);
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Checks that file is created for by created sorting mechanism (no q and a blocks)
   */
  @Test
  public void testCreateStudyGuideFileCreated() {
    assertFalse(Files.exists(createdEx));
    byCreated.executeProgram();
    assertTrue(Files.exists(createdEx));
    try {
      Files.delete(createdEx);
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Checks that a .md file and a .sr file is created.
   */
  @Test
  public void testCreateSrFile() {
    assertFalse(Files.exists(qaExMd));
    assertFalse(Files.exists(qaExSr));
    qaBlock.executeProgram();
    assertTrue(Files.exists(qaExMd));
    assertTrue(Files.exists(qaExSr));
    try {
      Files.delete(qaExMd);
      Files.delete(qaExSr);
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Checks that a file that already exists gets written over
   */
  @Test
  public void testExistingFile() {
    assertTrue(Files.exists(alreadyExist));
    exists.executeProgram();
    assertTrue(Files.exists(alreadyExist));
  }

  /**
   * Checks if a destination that is not an .md file causes an IllegalArgumentException to be
   * thrown.
   */
  @Test
  public void testInvalidDestinationNotMd() {
    assertThrows(IllegalArgumentException.class,
        () -> new StudyGuideController("src/test/resources/ex_root", "created",
            "src/test/resources/ex_root/file.pdf"));
  }

  /**
   * Checks if an invalid sorting mechanism causes an IllegalArgumentException to be thrown.
   */
  @Test
  public void testInvalidSortMech() {
    assertThrows(IllegalArgumentException.class,
        () -> new StudyGuideController("src/test/resources/ex_root", "directory",
            "src/test/resources/ex_sds/filename_ex.md"));
  }

  /**
   * Checks if an invalid destination causes an IllegalArgumentException to be thrown.
   */
  @Test
  public void testInvalidDest() {
    assertThrows(IllegalArgumentException.class,
        () -> new StudyGuideController("src/test/resources/ex_root", "directory",
            "invalid: file name").executeProgram());
  }

  /**
   * Checks if an invalid root causes an IllegalArgumentException to be thrown.
   */
  @Test
  public void testInvalidRoot() {
    assertThrows(IllegalArgumentException.class,
        () -> new StudyGuideController("invalid: file name", "filename",
            "src/test/resources/ex_root").executeProgram());
  }
}