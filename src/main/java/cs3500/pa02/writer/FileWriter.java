package cs3500.pa02.writer;

import cs3500.pa02.model.StudyCollection;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents a file writer.
 */
public class FileWriter implements Writer {
  /**
   * Writes the given content to the given destination path.
   *
   * @param destinationPath The destination file to be written to.
   *
   * @param content The content to be written.
   *
   * @throws IOException If IOException occurs while trying to write.
   */
  @Override
  public void write(String destinationPath, StudyCollection content) throws IOException {
    if (!Files.exists(Path.of(destinationPath))) {
      Files.createDirectories(Path.of(destinationPath).getParent());
    }

    java.io.FileWriter writer = new java.io.FileWriter(destinationPath);

    ArrayList<String> strings = content.writeCollection();

    // iterate through the list of strings and write each one; if the first string starts with \n,
    // delete the \n
    for (int i = 0; i < strings.size(); i += 1) {
      String s = strings.get(i);
      if (i == 0 && s.charAt(0) == '\n') {
        s = s.substring(1);
      }
      writer.write(s);
      writer.write("\n");
    }
    writer.close();
  }
}