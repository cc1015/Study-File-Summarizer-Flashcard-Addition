package cs3500.pa02.model.sgmodel;

/**
 * Represents content in a .md file; has text content represented as a String field.
 */
public abstract class StudyGuideContent {
  private String content;

  /**
   * Constructor sets content to the given String.
   *
   * @param content the given String to be set to this StudyGuideContent's content.
   */
  public StudyGuideContent(String content) {
    this.content = content;
  }

  /**
   * Returns this StudyGuideContent's content.
   *
   * @return this StudyGuideContent's content
   */
  public String getContent() {
    return this.content;
  }

  /**
   * Returns this StudyGuideContent as a String in the correct format.
   *
   * @return this StudyGuideContent as a String in the correct format
   */
  @Override
  public abstract String toString();

  /**
   * Determines whether this StudyGuideContent is the same as the given object
   * by comparing toString output.
   *
   * @param other the other object being compared
   * @return boolean indicating equality
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof StudyGuideContent)) {
      return false;
    }
    StudyGuideContent that = (StudyGuideContent) other;
    return this.toString().equals(that.toString());
  }
}
