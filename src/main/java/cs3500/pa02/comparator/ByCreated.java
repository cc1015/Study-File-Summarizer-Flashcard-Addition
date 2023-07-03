package cs3500.pa02.comparator;

import cs3500.pa02.model.sgmodel.MdFile;
import java.nio.file.attribute.FileTime;
import java.util.Comparator;

/**
 * Represents a Comparator that compares two given MdFiles by creation time.
 */
public class ByCreated implements Comparator<MdFile> {

  /**
   * Takes in two MdFiles and compares by time created: if file1 was created before file2, returns
   * number greater than 0, if file2 was created before file1, returns number less than 0, if
   * file1 and file2 have the same creation time, returns 0.
   *
   * @param file1 the first MdFile to be compared.
   *
   * @param file2 the second MdFile to be compared.
   *
   * @return integer less than 0 if file1 was created before file2, greater than 0 if file1 was
   *         created after file2, and 0 if file1 and file2 were created at the same time.
   */
  @Override
  public int compare(MdFile file1, MdFile file2) {
    FileTime file1Time = file1.getCreationTime();
    FileTime file2Time = file2.getCreationTime();

    return file1Time.compareTo(file2Time);
  }
}
