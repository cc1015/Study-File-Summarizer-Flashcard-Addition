package cs3500.pa02.creator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.model.sgmodel.Header;
import cs3500.pa02.model.sgmodel.ImportantInfo;
import cs3500.pa02.model.sgmodel.MdFile;
import cs3500.pa02.model.sgmodel.StudyGuide;
import cs3500.pa02.model.sgmodel.StudyGuideContent;
import cs3500.pa02.parser.StudyGuideParser;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the StudyGuideCreator class.
 */
class StudyGuideCreatorTest {
  FileTime mockTime;
  MdFile chem;
  MdFile law;
  MdFile python;
  MdFile sharks;
  ArrayList<MdFile> files;
  StudyGuideCreator creator;
  Header periodicTable;
  Header metals;
  ImportantInfo info;
  Header nonmetals;
  Header halogens;
  Header stoichiometry;
  Header amendments;
  Header fourteenthAmendment;
  Header pythonHeader;
  Header jupyterNotebook;
  Header nocturnal;
  Header types;
  StudyGuide sg;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    mockTime = FileTime.from(Instant.parse("2022-05-14T04:00:00Z"));
    chem = new MdFile("chem", "src/test/resources/pa2_test_files/chem.md", mockTime,
        mockTime);
    law = new MdFile("law", "src/test/resources/pa2_test_files/law.md", mockTime,
        mockTime);
    python =
        new MdFile("python", "src/test/resources/pa2_test_files/python.md", mockTime, mockTime);
    sharks =
        new MdFile("sharks", "src/test/resources/pa2_test_files/sharks.md", mockTime, mockTime);

    files = new ArrayList<>();
    files.add(chem);
    files.add(law);
    files.add(python);
    files.add(sharks);

    creator = new StudyGuideCreator(files, new StudyGuideParser());

    periodicTable = new Header("periodic table", 1);
    metals = new Header("metals", 2);
    info = new ImportantInfo("iron, aluminum, magnesium, tin, gold, silver");
    nonmetals = new Header("nonmetals", 2);
    halogens = new Header("halogens", 2);
    stoichiometry = new Header("stoichiometry", 1);
    amendments = new Header("amendments", 1);
    fourteenthAmendment = new Header("14th amendment", 2);
    pythonHeader = new Header("python", 1);
    jupyterNotebook = new Header("jupyter notebook", 2);
    nocturnal = new Header("sharks are nocturnal", 1);
    types = new Header("types of sharks", 2);

    ArrayList<StudyGuideContent> content = new ArrayList<>();
    content.add(periodicTable);
    content.add(metals);
    content.add(info);
    content.add(nonmetals);
    content.add(halogens);
    content.add(stoichiometry);
    content.add(amendments);
    content.add(fourteenthAmendment);
    content.add(pythonHeader);
    content.add(jupyterNotebook);
    content.add(nocturnal);
    content.add(types);
    sg = new StudyGuide(content);
  }

  /**
   * Tests that a StudyGuide is created correctly.
   */
  @Test
  public void testCreateStudyBank() {
    assertEquals(creator.createStudyGuide(), sg);
  }
}