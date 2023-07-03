package cs3500.pa02.model.ssmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * A test class for the Flashcard class.
 */
class FlashcardTest {
  Flashcard easy = new Flashcard("How long is a foot?", "12 inches.", Difficulty.EASY);
  Flashcard hard = new Flashcard("What system is responsible for protein degradation in cells?",
      "The ubiquitin-proteasome pathway.", Difficulty.HARD);

  /**
   * Test constructor.
   */
  @Test
  public void testConstruct() {
    assertNotNull(easy);
    assertNotNull(easy);
  }

  /**
   * Test toString for easy flashcard.
   */
  @Test
  public void testToStringEasy() {
    assertEquals(easy.toString(), "[E] How long is a foot?:::12 inches.");
  }

  /**
   * Test toString for hard Flashcard
   */
  @Test
  public void testToStringHard() {
    assertEquals(hard.toString(),
        "[H] What system is responsible for protein degradation in cells?:::"
            + "The ubiquitin-proteasome pathway.");
  }

  /**
   * Test questionToString for easy Flashcard.
   */
  @Test
  public void testQuestionToStringEasy() {
    assertEquals(easy.questionToString(), "How long is a foot?");
  }

  /**
   * Test questionToString for hard Flashcard.
   */
  @Test
  public void testQuestionToStringHard() {
    assertEquals(hard.questionToString(),
        "What system is responsible for protein degradation in cells?");
  }

  /**
   * Test answerToString for easy Flashcard.
   */
  @Test
  public void testAnswerToStringEasy() {
    assertEquals(easy.answerToString(), "12 inches.");
  }

  /**
   * Test answerToString for hard Flashcard.
   */
  @Test
  public void testAnswerToStringHard() {
    assertEquals(hard.answerToString(), "The ubiquitin-proteasome pathway.");
  }

  /**
   * Test getDifficulty for an easy Flashcard.
   */
  @Test
  public void testGetDifficultyEasy() {
    assertEquals(easy.getDifficulty(), Difficulty.EASY);
  }

  /**
   * Test getDifficulty for a hard Flashcard.
   */
  @Test
  public void testGetDifficultyHard() {
    assertEquals(hard.getDifficulty(), Difficulty.HARD);
  }

  /**
   * Test change difficulty from easy to hard.
   */
  @Test
  public void testChangeDifficultyEasy() {
    easy.changeDifficulty(Difficulty.HARD);
    assertEquals(easy.toString(), "[H] How long is a foot?:::12 inches.");
  }

  /**
   * Test change difficulty from hard to easy.
   */
  @Test
  public void testChangeDifficultyHard() {
    hard.changeDifficulty(Difficulty.EASY);
    assertEquals(hard.toString(), "[E] What system is responsible for protein degradation "
        + "in cells?:::The ubiquitin-proteasome pathway.");
  }

  /**
   * Checks that equals returns true for two different flashcards that have the same question,
   * answer, and difficulty.
   */
  @Test
  public void testEqualsPass() {
    Flashcard easy2 = new Flashcard("How long is a foot?", "12 inches.", Difficulty.EASY);
    assertTrue(easy.equals(easy2));
  }

  /**
   * Checks that equals returns false for a string and a flashcard.
   */
  @Test
  public void testEqualsFail() {
    assertFalse(easy.equals("String"));
  }
}