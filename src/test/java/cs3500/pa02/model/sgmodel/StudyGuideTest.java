package cs3500.pa02.model.sgmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * A test class for the StudyGuide class
 */
class StudyGuideTest {
  Header mainHeader;
  ImportantInfo cellDef;
  Header nucleus;
  ImportantInfo nucleusDef;
  Header er;
  Header roughEr;
  ImportantInfo roughErdef;
  ImportantInfo roughErInfo;
  Header smoothEr;
  ImportantInfo smoothErdef;
  ArrayList<StudyGuideContent> notes;
  StudyGuide bio;

  ImportantInfo info;
  StudyGuide onlyInfo;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    this.mainHeader = new Header("The Cell", 1);
    this.cellDef = new ImportantInfo("smallest unit of an organism");
    this.nucleus = new Header("Nucleus", 2);
    this.nucleusDef = new ImportantInfo("contains DNA");
    this.er = new Header("ER", 2);
    this.roughEr = new Header("Rough ER", 3);
    this.roughErdef = new ImportantInfo("synthesizes proteins");
    this.roughErInfo = new ImportantInfo("holds ribosomes");
    this.smoothEr = new Header("Smooth ER", 3);
    this.smoothErdef = new ImportantInfo("synthesizes lipids");

    this.notes = new ArrayList<>();
    notes.add(mainHeader);
    notes.add(cellDef);
    notes.add(nucleus);
    notes.add(nucleusDef);
    notes.add(er);
    notes.add(roughEr);
    notes.add(roughErdef);
    notes.add(roughErInfo);
    notes.add(smoothEr);
    notes.add(smoothErdef);

    this.bio = new StudyGuide(notes);

    this.info = new ImportantInfo("info");
    ArrayList<StudyGuideContent> n = new ArrayList<>();
    n.add(info);
    this.onlyInfo = new StudyGuide(n);
  }

  /**
   * Checks that constructor initializes properly
   */
  @Test
  public void testConstruct() {
    assertNotNull(this.onlyInfo);
  }

  /**
   * Checks that writeCollection produces the correct list of Strings
   */
  @Test
  public void testWriteCollection() {
    ArrayList<String> strings = new ArrayList<>();
    strings.add("\n# The Cell");
    strings.add("- smallest unit of an organism");
    strings.add("\n## Nucleus");
    strings.add("- contains DNA");
    strings.add("\n## ER");
    strings.add("\n### Rough ER");
    strings.add("- synthesizes proteins");
    strings.add("- holds ribosomes");
    strings.add("\n### Smooth ER");
    strings.add("- synthesizes lipids");

    assertEquals(this.bio.writeCollection(), strings);
  }

  /**
   * Checks that a study guide is equal to another study guide with the same content.
   */
  @Test
  public void testEqualsPass() {
    Header h1 = new Header("header", 1);
    ArrayList<StudyGuideContent> list1 = new ArrayList<>();
    list1.add(h1);
    StudyGuide sg1 = new StudyGuide(list1);

    Header h2 = new Header("header", 1);
    ArrayList<StudyGuideContent> list2 = new ArrayList<>();
    list2.add(h2);
    StudyGuide sg2 = new StudyGuide(list2);

    assertTrue(sg1.equals(sg2));
  }

  /**
   * Checks that a study guide is not equal to a String.
   */
  @Test
  public void testEqualsFail() {
    assertFalse(onlyInfo.equals("string"));
  }
}