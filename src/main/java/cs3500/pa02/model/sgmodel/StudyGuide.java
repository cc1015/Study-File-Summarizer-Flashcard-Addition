package cs3500.pa02.model.sgmodel;

import cs3500.pa02.model.StudyCollection;
import java.util.ArrayList;

/**
 * Represents a study guide that has a list of AbContent representing the notes in the study
 * guide.
 */
public class StudyGuide implements StudyCollection {
  private ArrayList<StudyGuideContent> notes;

  /**
   * Constructor sets this StudyGuide's notes to the given list of AbContent.
   *
   * @param notes the list of AbContent to be set to this StudyGuide's notes.
   */
  public StudyGuide(ArrayList<StudyGuideContent> notes) {
    this.notes = notes;
  }

  /**
   * Writes each AbContent as a String in this StudyGuides notes by calling writeContent for
   * each AbContent.
   *
   * @return a list of Strings representing the list of AbContent as Strings
   */
  public ArrayList<String> writeCollection() {
    ArrayList<String> strings = new ArrayList<>();

    // for every AbContent in this StudyGuide's list of notes, call writeContent method to convert
    // to String
    for (StudyGuideContent a : this.notes) {
      strings.add(a.toString());
    }
    return strings;
  }

  /**
   * Two StudyGuides are equal if all content in the study guides are equal.
   *
   * @param other The other object being compared to.
   *
   * @return Whether this StudyGuide and the given object are the same.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof StudyGuide)) {
      return false;
    }
    StudyGuide that = (StudyGuide) other;
    return this.writeCollection().equals(that.writeCollection());
  }
}
