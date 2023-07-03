package cs3500.pa02.sorter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import cs3500.pa02.comparator.ByCreated;
import cs3500.pa02.comparator.ByModified;
import cs3500.pa02.comparator.ByName;
import cs3500.pa02.model.sgmodel.MdFile;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the FileSorter class.
 */
class FileSorterTest {
  FileSorter nameSort;
  FileSorter createSort;
  FileSorter modifySort;
  ArrayList<MdFile> files;
  ArrayList<MdFile> expectedOrder;
  Comparator<MdFile> nameComp;
  Comparator<MdFile> createComp;
  Comparator<MdFile> modifyComp;
  MdFile notes;
  MdFile numbers;
  MdFile passwords;
  MdFile journal;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    notes = new MdFile("notes.md", "ex/path",
        FileTime.from(Instant.parse("2017-05-14T04:00:00Z")),
        FileTime.from(Instant.parse("2022-05-14T04:00:00Z")));

    numbers = new MdFile("numbers.md", "ex/path",
        FileTime.from(Instant.parse("2019-07-14T04:00:00Z")),
        FileTime.from(Instant.parse("2023-04-14T04:00:00Z")));

    passwords = new MdFile("passwords.md", "ex/path",
        FileTime.from(Instant.parse("2020-05-14T04:00:00Z")),
        FileTime.from(Instant.parse("2021-05-14T04:00:00Z")));

    journal = new MdFile("journal.md", "ex/path",
        FileTime.from(Instant.parse("2023-01-14T04:00:00Z")),
        FileTime.from(Instant.parse("2023-05-14T04:00:00Z")));

    nameComp = new ByName();
    createComp = new ByCreated();
    modifyComp = new ByModified();

    expectedOrder = new ArrayList<>();

    files = new ArrayList<>();
    files.add(notes);
    files.add(numbers);
    files.add(passwords);
    files.add(journal);
    nameSort = new FileSorter(files, nameComp);
    createSort = new FileSorter(files, createComp);
    modifySort = new FileSorter(files, modifyComp);
  }

  /**
   * Checks that the FileSorte constructor initializes properly
   */
  @Test
  public void testConstruct() {
    assertNotNull(nameSort);
    assertNotNull(createSort);
    assertNotNull(modifySort);
  }

  /**
   * Checks that files are sorted correctly by file name
   */
  @Test
  public void testSortByName() {
    expectedOrder.add(journal);
    expectedOrder.add(notes);
    expectedOrder.add(numbers);
    expectedOrder.add(passwords);

    nameSort.sort();

    assertEquals(files, expectedOrder);
  }

  /**
   * Checks that files are sorted correctly by creation time
   */
  @Test
  public void testSortByCreated() {
    expectedOrder.add(notes);
    expectedOrder.add(numbers);
    expectedOrder.add(passwords);
    expectedOrder.add(journal);

    createSort.sort();

    assertEquals(files, expectedOrder);
  }

  /**
   * Checks that files are sorted correctly by modified time
   */
  @Test
  public void testSortByModified() {
    expectedOrder.add(passwords);
    expectedOrder.add(notes);
    expectedOrder.add(numbers);
    expectedOrder.add(journal);

    modifySort.sort();

    assertEquals(files, expectedOrder);
  }
}