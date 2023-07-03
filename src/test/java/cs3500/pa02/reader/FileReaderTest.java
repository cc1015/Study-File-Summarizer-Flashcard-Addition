package cs3500.pa02.reader;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test class for the FileReader class.
 */
class FileReaderTest {
  ArrayList<StringBuilder> strings;

  /**
   * Initialize data; creates a study guide with a main header (# header)
   */
  @BeforeEach
  public void initData() {
    this.strings = new ArrayList<>();
  }

  /**
   * Checks that a non-existent file cannot be used to construct a FileSummarizer
   */
  @Test
  public void testNoAccessFile() {
    assertThrows(RuntimeException.class,
        () -> new FileReader(Path.of("src/test/resources/test_files/no_file.md")));
  }

  /**
   * Checks that an .md file with headers, bullets, and important info is read correctly.
   */
  @Test
  public void testReadMd() {
    StringBuilder s1 = new StringBuilder("# header");
    StringBuilder s2 =
        new StringBuilder("- this is an example and here is [[important information");
    StringBuilder s3 = new StringBuilder("that spans two lines.]]");

    strings.add(s1);
    strings.add(s2);
    strings.add(s3);

    ArrayList<String> expectedStrings = new ArrayList<>();

    for (StringBuilder s : strings) {
      expectedStrings.add(s.toString());
    }

    FileReader reader =
        new FileReader(Path.of("src/test/resources/test_files/example_read_one.md"));

    ArrayList<StringBuilder> readContent = reader.read();
    ArrayList<String> readContentAsStrings = new ArrayList<>();

    for (StringBuilder s : readContent) {
      readContentAsStrings.add(s.toString());
    }

    assertArrayEquals(expectedStrings.toArray(), readContentAsStrings.toArray());
  }

  /**
   * Checks that an .sr file formatted [H]Q?:::A, where [H] can be [H] or [E] for hard or easy,
   * is read correctly.
   */
  @Test
  public void testReadSr() {
    StringBuilder s1 = new StringBuilder("[H] What is the name of the protein responsible for "
        + "carrying oxygen in the blood?:::Hemoglobin.");
    StringBuilder s2 =
        new StringBuilder("[H] What is DNA?:::DNA is genetic information.");
    StringBuilder s3 = new StringBuilder("[E] What is the largest organ in the human body?"
        + ":::Skin.");
    StringBuilder s4 = new StringBuilder("[E] What holds the DNA in a cell?:::The nucleus.");

    strings.add(s1);
    strings.add(s2);
    strings.add(s3);
    strings.add(s4);

    ArrayList<String> expectedStrings = new ArrayList<>();

    for (StringBuilder s : strings) {
      expectedStrings.add(s.toString());
    }

    FileReader reader =
        new FileReader(Path.of("src/test/resources/ex_sr/sample_no_change.sr"));

    ArrayList<StringBuilder> readContent = reader.read();
    ArrayList<String> readContentAsStrings = new ArrayList<>();

    for (StringBuilder s : readContent) {
      readContentAsStrings.add(s.toString());
    }

    assertArrayEquals(expectedStrings.toArray(), readContentAsStrings.toArray());
  }
}