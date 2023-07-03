package cs3500.pa02.creator;

import cs3500.pa02.model.sgmodel.MdFile;
import cs3500.pa02.reader.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents a creatpr that creates a study collection.
 */
public abstract class StudyCollectionCreator {
  protected ArrayList<MdFile> files;

  /**
   * Sets the files field to the given list of MdFiles.
   *
   * @param files The list of MdFiles the files field is set to
   */
  public StudyCollectionCreator(ArrayList<MdFile> files) {
    this.files = files;
  }

  /**
   * Creates an instance of FileReader to read the file at the given input Path.
   *
   * @param input The given Path to be read.
   *
   * @return The content of the file as a list of StringBuilders.
   */
  public ArrayList<StringBuilder> readInput(Path input) {
    FileReader reader = new FileReader(input);
    return reader.read();
  }
}
