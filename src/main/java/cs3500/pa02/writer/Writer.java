package cs3500.pa02.writer;

import cs3500.pa02.model.StudyCollection;
import java.io.IOException;

/**
 * Represents a writer.
 */
public interface Writer {
  /**
   * Writes the given content to the given destination path.
   *
   * @param destinationPath The destination path getting written to.
   *
   * @param content The content to be written.
   *
   * @throws IOException If file cannot be written.
   */
  void write(String destinationPath, StudyCollection content) throws IOException;
}
