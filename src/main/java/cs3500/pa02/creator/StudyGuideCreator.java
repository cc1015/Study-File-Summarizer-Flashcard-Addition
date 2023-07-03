package cs3500.pa02.creator;

import cs3500.pa02.model.sgmodel.MdFile;
import cs3500.pa02.model.sgmodel.StudyGuide;
import cs3500.pa02.model.sgmodel.StudyGuideContent;
import cs3500.pa02.parser.Parser;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents a creator that creates a StudyGuide from a list of files.
 */
public class StudyGuideCreator extends StudyCollectionCreator {
  private Parser<StudyGuideContent> parser;

  /**
   * Calls super constructor to set the given files to the files field. Sets the parser field to
   * the given parse.
   *
   * @param files The list of MdFiles the files field is set to.
   *
   * @param parser The parserthe parser field is set to.
   */
  public StudyGuideCreator(ArrayList<MdFile> files, Parser<StudyGuideContent> parser) {
    super(files);
    this.parser = parser;
  }

  /**
   * Creates a StudyGuide from parsing the content of this list of files.
   *
   * @return The StudyGuide created from this list of files.
   */
  public StudyGuide createStudyGuide() {
    ArrayList<StudyGuideContent> summaries = new ArrayList<>();

    // for every file, read it and parse it to create a list of StudyGuideContent
    for (MdFile f : this.files) {
      String filePath = f.getPath();
      ArrayList<StringBuilder> content = readInput(Path.of(filePath));
      ArrayList<StudyGuideContent> newSummaries = parser.parse(content);
      summaries.addAll(newSummaries);
    }

    StudyGuide sd = new StudyGuide(summaries);
    return sd;
  }
}
