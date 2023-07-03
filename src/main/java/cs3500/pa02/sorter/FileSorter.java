package cs3500.pa02.sorter;

import cs3500.pa02.model.sgmodel.MdFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents a sorter that sorts its list of MdFiles by its comparator.
 */
public class FileSorter implements Sorter {
  private ArrayList<MdFile> files;
  private Comparator<MdFile> comp;

  /**
   * Constructor sets fields to given files and comp
   *
   * @param files the list of MdFile files is set to
   *
   * @param comp the comparator comp is set to
   */
  public FileSorter(ArrayList<MdFile> files, Comparator<MdFile> comp) {
    this.files = files;
    this.comp = comp;
  }

  /**
   * Sorts this FileSorter's list of files by this FileSorter's comparator
   */
  public void sort() {
    Collections.sort(files, comp);
  }
}
