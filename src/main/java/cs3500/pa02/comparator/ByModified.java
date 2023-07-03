package cs3500.pa02.comparator;

import cs3500.pa02.model.sgmodel.MdFile;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;

/**
 * Represents a Comparator that compares two given MdFiles by last modified time.
 */
public class ByModified implements Comparator<MdFile> {

  /**
   * Takes in two MdFiles and compares by modified time: if file1 was modified before file2, returns
   * less than 0, if file2 was modified before file1, returns number greater than 0, if file1
   * and file2 have the same modified time, returns 0.
   *
   * @param file1 the first MdFile to be compared.
   *
   * @param file2 the second MdFile to be compared.
   *
   * @return integer less than 0 if file1 was modified before file2, greater than 0 if file1 was
   *         modified after file2, and 0 if file1 and file2 were modified at the same time.
   */
  @Override
  public int compare(MdFile file1, MdFile file2) {
    FileTime file1ModTime = file1.getModifyTime();
    FileTime file2ModTime = file2.getModifyTime();

    return file1ModTime.compareTo(file2ModTime);
  }
}
