package cs3500.pa02.model.sgmodel;

/**
 * Represents a header in a .md file; has a String content field representing the text of the
 * header and an int hashNum field representing the indentation level of the header.
 */
public class Header extends StudyGuideContent {
  private final int hashNum;

  /**
   * Constructor calls super constructor to set content to the given String and
   * sets nestedNum to the given int. Throws IllegalArgumentException if the given hashNum is
   * 0 or less.
   *
   * @param content the given String to be set to this Header's content
   * @param hashNum the given int to be set to this Header's hashNum
   */
  public Header(String content, int hashNum) {
    super(content);
    if (hashNum <= 0 || hashNum > 6) {
      throw new IllegalArgumentException("Header must have at least 1 hash and at most 6");
    } else {
      this.hashNum = hashNum;
    }
  }

  /**
   * Returns this Header as a String, formatted with the correct number of # indicating the
   * indentation level and the content after the #s. A blank line added before the header text.
   *
   * @return String representing header as it would appear on .md file.
   */
  @Override
  public String toString() {
    return "\n" + "#".repeat(this.hashNum) + " " + this.getContent();
  }
}
