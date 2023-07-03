package cs3500.pa02.parser;

import cs3500.pa02.model.sgmodel.Header;
import cs3500.pa02.model.sgmodel.ImportantInfo;
import cs3500.pa02.model.sgmodel.StudyGuideContent;
import java.util.ArrayList;

/**
 * Represents a parser that parses a .md file.
 */
public class StudyGuideParser implements Parser<StudyGuideContent> {
  /**
   * Parses each StringBuilder in the given list into either a Header, ImportantInfo, or a
   * Flashcard. Adds all the parsed Headers and ImportantInfo to this list of sgContent.
   *
   * @param content The list of StringBuilders to be parsed.
   */
  @Override
  public ArrayList<StudyGuideContent> parse(ArrayList<StringBuilder> content) {
    ArrayList<StudyGuideContent> sgContent = new ArrayList<>();

    StringBuilder info = new StringBuilder();

    for (StringBuilder line : content) {
      if (line.length() > 1 && this.isHeaderFormat(line)) {
        sgContent.addAll(this.parseInfo(info));
        info = new StringBuilder();
        sgContent.add(this.parseHeader(line));
      } else {
        info.append(line);
      }
    }

    sgContent.addAll(this.parseInfo(info));
    return sgContent;
  }

  /**
   * Parses the given StringBuilder and constructs a Header
   *
   * @param s the StringBuilder to be parsed
   * @return a Header produced from parsing the given StringBuilder
   */
  private Header parseHeader(StringBuilder s) {
    StringBuilder header = new StringBuilder();
    int hashCount = 0;

    // while the first char of line is #, increase the indentCount by 1 and delete the just
    // parsed # from line
    while (s.length() > 0 && s.charAt(0) == '#') {
      hashCount += 1;
      s.deleteCharAt(0);
    }

    if (s.length() > 0) {
      s.deleteCharAt(0);
    }

    // while line has characters add the char to the header and delete the just parsed
    // char from content
    while (s.length() > 0) {
      header.append(s.charAt(0));
      s.deleteCharAt(0);
    }

    return new Header(header.toString(), hashCount);
  }

  /**
   * Parses the given StringBuilder. With every [[]] it encounters, checks if the block contains
   * ":::". If so, disregards the block, otherwise saves the text within the brackets and creates
   * a new ImportantInfo of the text and adds the ImportantInfo to a list of ImportantInfo.
   * Once parsing is complete, returns the list of ImportantInfo.
   *
   * @param content the given StringBuilder to be parsed
   * @return list of important information
   */
  private ArrayList<ImportantInfo> parseInfo(StringBuilder content) {
    ArrayList<ImportantInfo> summary = new ArrayList<>();

    // while content has characters, parse it
    while (content.length() > 0) {
      StringBuilder info = new StringBuilder();

      if (content.length() > 1 && (content.charAt(0) == '[') && (content.charAt(1) == '[')) {
        content.deleteCharAt(0);
        content.deleteCharAt(0);

        // content has characters and the first and second chars are not ']', add the first char
        // to info and delete the just parsed char from content
        while (content.length() > 0
            && !((content.charAt(0) == ']') && (content.charAt(1) == ']'))) {
          info.append(content.charAt(0));
          content.deleteCharAt(0);
        }

        if (content.length() > 0) {
          content.deleteCharAt(0);
          content.deleteCharAt(0);

          if (!info.toString().contains(":::")) {
            ImportantInfo i = new ImportantInfo(info.toString());
            summary.add(i);
          }

        }
      } else {
        content.deleteCharAt(0);
      }
    }
    return summary;
  }

  /**
   * Determines if the given StringBuilder is of valid header format:
   * begins with #
   * has no more than 6 #
   * # are consecutive
   * the text of the header follows after a space after the #s
   * a header with no text and only #s is valid (as long as there are 6 or less #s)
   *
   * @param s the StringBuilder to be decided as valid header format
   * @return whether the given StringBuilder is of valid header format
   */
  private boolean isHeaderFormat(StringBuilder s) {
    final int maxHashNum = 6;
    final boolean isOnlyHash = "#".repeat(s.length()).contentEquals(s);

    if (s.charAt(0) != '#') {
      return false;
    } else if (isOnlyHash && s.length() <= maxHashNum) {
      // if the given StringBuilder is only #s and does not exceed the # max number
      return true;
    } else {
      int hashCount = 0;
      // iterates through s and counts the #s in s to make sure it is 6 or less and that there is a
      // space after the #s and before the header text
      for (int i = 0; i < s.length() - 1 && hashCount <= maxHashNum; i += 1) {
        if (s.charAt(i) == '#') {
          hashCount += 1;
          if (s.charAt(i + 1) == ' ') {
            return true;
          }
        }
      }
      return false;
    }
  }
}
