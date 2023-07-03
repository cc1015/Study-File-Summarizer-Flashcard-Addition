package cs3500.pa02.filevisitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import cs3500.pa02.model.sgmodel.MdFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the MarkDownCollector class.
 */
class MarkdownCollectorTest {
  MarkdownCollector md;
  ArrayList<MdFile> mdFiles;
  ArrayList<File> files;
  ArrayList<File> expectedFiles;

  /**
   * initialize data
   */
  @BeforeEach
  public void initData() {
    this.mdFiles = new ArrayList<>();
    this.files = new ArrayList<>();
    this.expectedFiles = new ArrayList<>();

    this.md = new MarkdownCollector(mdFiles, files);
  }

  /**
   * Check that the constructor initializes properly
   */
  @Test
  public void testConstruct() {
    assertNotNull(this.md);
  }

  /**
   * Check that the correct .md files are collected by the visitor
   */
  @Test
  public void testCollectFiles() {
    try {
      Files.walkFileTree(Path.of("src/test/resources/ex_root"), this.md);
    } catch (IOException e) {
      fail();
    }

    this.expectedFiles.add(new File("src/test/resources/ex_root/arrays.md"));
    this.expectedFiles.add(new File("src/test/resources/ex_root/vectors.md"));
    this.expectedFiles.add(new File("src/test/resources/ex_root/bio.md"));

    assertEquals(3, files.size());
    assertTrue(expectedFiles.containsAll(files));
  }

  /**
   * Checks that handler method for visitFileFailed handles the IOException given and throws a
   * RuntimeException; tests the behavior of visitFileFailed as visitFileFailed only calls this
   * handler method
   */
  @Test
  public void testFileFail() {
    assertThrows(RuntimeException.class, () -> this.md.failHandler());
  }
}