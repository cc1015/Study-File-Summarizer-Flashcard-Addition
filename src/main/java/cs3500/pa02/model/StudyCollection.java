package cs3500.pa02.model;

import java.util.ArrayList;

/**
 * Represents a collection of study material in the study program.
 */
public interface StudyCollection {
  /**
   * Returns each element of this StudyCollection as a String in a list of Strings.
   *
   * @return The list of Strings that are the elements of this StudyCollection as Strings.
   */
  public ArrayList<String> writeCollection();
}
