package cs3500.pa02.comparator;

import cs3500.pa02.model.sgmodel.MdFile;
import java.util.Comparator;

/**
 * Represents a Comparator that compares two given MdFiles alphabetically by name.
 */
public class ByName implements Comparator<MdFile> {

  /**
   * Takes in two MdFiles and compares by file name: if file1 is alphabetically before file2,
   * returns number greater than 0, if file2 is alphabetically before file1, returns number less
   * than 0, if file1 and file2 have the same name, returns 0.
   *
   * @param file1 the first MdFile to be compared.
   * @param file2 the second MdFile to be compared.
   *
   * @return integer less than 0 if file1 is alphabetically before file2, greater than 0 if file1 is
   *         alphabetically after file2, and 0 if file1 and file2 hae the same name.
   */
  @Override
  public int compare(MdFile file1, MdFile file2) {
    String file1Name = file1.getName();
    String file2Name = file2.getName();

    return file1Name.compareTo(file2Name);
  }
}
