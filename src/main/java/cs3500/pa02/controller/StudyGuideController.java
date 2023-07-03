package cs3500.pa02.controller;

import cs3500.pa02.comparator.ByCreated;
import cs3500.pa02.comparator.ByModified;
import cs3500.pa02.comparator.ByName;
import cs3500.pa02.creator.FlashcardBankCreator;
import cs3500.pa02.creator.StudyGuideCreator;
import cs3500.pa02.filevisitor.MarkdownCollector;
import cs3500.pa02.model.StudyCollection;
import cs3500.pa02.model.sgmodel.MdFile;
import cs3500.pa02.model.sgmodel.StudyGuide;
import cs3500.pa02.model.ssmodel.FlashcardBank;
import cs3500.pa02.parser.QandAnsParser;
import cs3500.pa02.parser.StudyGuideParser;
import cs3500.pa02.sorter.FileSorter;
import cs3500.pa02.writer.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents a controller that summarizes .md files into one .md file and .sr file.
 */
public class StudyGuideController implements Controller {
  private Path rootPath;
  private Comparator<MdFile> comp;
  private String studyGuideDestPath;
  private String flashcardDestPath;
  private ArrayList<MdFile> sortedMdFiles;

  /**
   * Constructs with given rootPath, comp, and destPath. Throws IllegalArgumentException if comp is
   * not filename, modified, or created, or if the destPath is not an .md file.
   *
   * @param rootPath           the root directory of the .md files to be collected and summarized
   * @param comp               the comparator to sort the files by
   * @param studyGuideDestPath the destination path of the study guide file
   */
  public StudyGuideController(String rootPath, String comp, String studyGuideDestPath) {
    this.rootPath = Path.of(rootPath);

    if (comp.equalsIgnoreCase("filename")) {
      this.comp = new ByName();
    } else if (comp.equalsIgnoreCase("modified")) {
      this.comp = new ByModified();
    } else if ((comp.equalsIgnoreCase("created"))) {
      this.comp = new ByCreated();
    } else {
      throw new IllegalArgumentException("Sorting mechanism must either be 'filename', "
          + "'modified', or 'created'");
    }

    if (studyGuideDestPath.endsWith(".md")) {
      this.studyGuideDestPath = studyGuideDestPath;

      int extensionIdx = studyGuideDestPath.indexOf(".md");
      String path = studyGuideDestPath.substring(0, extensionIdx);
      this.flashcardDestPath = path + ".sr";

    } else {
      throw new IllegalArgumentException("Study guide file must be .md");
    }

    this.sortedMdFiles = collectMd();
    sortFiles(this.sortedMdFiles);
  }

  /**
   * Executes the summarizing of .md files into a study guide and flashcard bank by calling
   * subsequent helper methods to read, parse, and write files.
   */
  @Override
  public void executeProgram() {
    StudyGuide studyGuide =
        new StudyGuideCreator(this.sortedMdFiles, new StudyGuideParser()).createStudyGuide();
    writeFile(studyGuide, studyGuideDestPath);

    FlashcardBank bank =
        new FlashcardBankCreator(this.sortedMdFiles, new QandAnsParser()).createStudyBank();
    writeFile(bank, flashcardDestPath);
  }

  /**
   * Writes the given content at the given destination path.
   *
   * @param content  The content to be written.
   * @param destPath The path of the file to be written on.
   */
  @Override
  public void writeFile(StudyCollection content, String destPath) {
    FileWriter writer = new FileWriter();
    try {
      writer.write(destPath, content);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Creates a new instance of the MarkdownCollector visitor. Calls walkFileTree and creates a
   * list of .md files in this FileCreator's root directory and returns the list.
   * Throws RuntimeException if walkFileTree throws an IOException.
   *
   * @return the list of ordered .md files
   */
  private ArrayList<MdFile> collectMd() {
    ArrayList<MdFile> mdFiles = new ArrayList<>();
    ArrayList<File> files = new ArrayList<>();
    MarkdownCollector collector = new MarkdownCollector(mdFiles, files);

    try {
      Files.walkFileTree(this.rootPath, collector);
    } catch (IOException e) {
      throw new RuntimeException("File(s) could not be visited");
    }

    return mdFiles;
  }

  /**
   * Sorts the given list of files by this FileCreator's comparator by creating an instance of the
   * FileSorter class
   *
   * @param mdFiles the list to be sorted
   */
  private void sortFiles(ArrayList<MdFile> mdFiles) {
    FileSorter f = new FileSorter(mdFiles, this.comp);
    f.sort();
  }
}