package cs3500.pa02.view;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa02.MockAppendable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the StudySessionView class.
 */
class StudySessionViewTest {
  StudySessionView view;
  Appendable appendable;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    appendable = new StringBuilder();
    view = new StudySessionView(appendable);
  }

  /**
   * Checks that display to choose number of questions is appended to output
   */
  @Test
  public void testDisplayChooseQuestion() {
    view.displayChooseQuestion();
    assertEquals(appendable.toString(),
        "How many flashcards would you like to study? (enter a number)\n");
  }

  /**
   * Checks that display question is appended to output
   */
  public void displayQuestion(String question) {
    view.displayQuestion("question");
    assertEquals(appendable.toString(), "question");
  }

  /**
   * Checks that display answer is appended to output
   */
  public void displayAnswer(String answer) {
    view.displayAnswer("answer");
    assertEquals(appendable.toString(), "answer");
  }

  /**
   * Checks that display options is appended to output
   */
  @Test
  public void testDisplayOptions() {
    view.displayOptions();
    assertEquals(appendable.toString(), "1. mark easy  2. mark hard  3. show answer  4. exit\n");
  }

  /**
   * Checks that display incorrect option is appended to output
   */
  public void displayIncorrectOption() {
    view.displayIncorrectOption();
    assertEquals(appendable.toString(),
        "Please only select from one of the options listed in the question.");
  }

  /**
   * Checks that display no file error is appended to output
   */
  public void displayNoFileError() {
    view.displayNoFileError();
    assertEquals(appendable.toString(), "The given file is not a .sr file or does not exist."
        + " Would you like to enter another .sr file?\n1.YES 2. NO");
  }

  /**
   * Checks that the view correctly displays the given final stats of a study session.
   */
  @Test
  void displayStats() {
    view.displayStats(1, 2, 3, 4, 5);

    String s0 = "Study session stats:\n";
    String s1 = "Total number of questions answered: 1\n";
    String s2 = "Total number of questions changed from easy to hard: 2\n";
    String s3 = "Total number of questions changed from hard to easy: 3\n";
    String s4 = "Total number of easy questions: 4\n";
    String s5 = "Total number of hard questions: 5";

    String stats = s0 + s1 + s2 + s3 + s4 + s5;

    assertEquals(appendable.toString(), stats);
  }

  /**
   * Tests try/catch block for appendable in study session view.
   */
  @Test
  public void testFailure() {
    this.view = new StudySessionView(new MockAppendable());
    Exception e = assertThrows(RuntimeException.class, () -> this.view.displayAnswer("answer"),
        "Mock throwing an error");
    assertEquals("java.io.IOException: Mock throwing an error", e.getMessage());
  }

  /**
   * Tests try/catch block for appendable in study session view.
   */
  @Test
  public void testFailureStats() {
    this.view = new StudySessionView(new MockAppendable());
    Exception e = assertThrows(RuntimeException.class,
        () -> this.view.displayStats(1, 2, 3, 4, 5),
        "Mock throwing an error");
    assertEquals("java.io.IOException: Mock throwing an error", e.getMessage());
  }
}