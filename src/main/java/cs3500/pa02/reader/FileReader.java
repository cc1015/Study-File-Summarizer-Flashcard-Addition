package cs3500.pa02.reader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a reader that reads files.
 */
public class FileReader implements Reader {
  private Path path;
  private Scanner scanner;

  /**
   * The constructor takes in the path as a String, creates a new Path with the String, and
   * creates a Scanner with the Path as the input if the given path exists. If it does not exist,
   * a IllegalArgumentException is thrown.
   *
   * @param path name of path as String
   */
  public FileReader(Path path) {
    this.path = path;
    try {
      this.scanner = new Scanner(this.path);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Returns the contents of this FileReader's path file.
   *
   * @return Array list of StringBuilders read from the path file.
   */
  @Override
  public ArrayList<StringBuilder> read() {
    ArrayList<StringBuilder> content = new ArrayList<>();

    // while there is a next line, add the line to the building list of Strings
    while (this.scanner.hasNext()) {
      StringBuilder line = new StringBuilder(scanner.nextLine());
      content.add(line);
    }

    return content;
  }
}
