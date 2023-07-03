package cs3500.pa02.model.ssmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for StudySessionData class.
 */
class StudySessionDataTest {
  StudySessionData ssd;

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    this.ssd = new StudySessionData();
  }

  /**
   * Checks that getQuestionsAnswered returns 0.
   */
  @Test
  public void testGetQuestionsAnswered() {
    assertEquals(ssd.getQuestionsAnswered(), 0);
  }

  /**
   * Checks that getHardToEasyCount returns 0.
   */
  @Test
  public void testGetHardToEasyCount() {
    assertEquals(ssd.getHardToEasyCount(), 0);
  }

  /**
   * Checks that getEasyToHardCount returns 0.
   */
  @Test
  public void testGetEasyToHardCount() {
    assertEquals(ssd.getEasyToHardCount(), 0);
  }

  /**
   * Checks that calling updateQuestionsAnswered twice changes the questionsAnswered to 2.
   */
  @Test
  public void testUpdateQuestionsAnswered() {
    ssd.updateQuestionsAnswered();
    ssd.updateQuestionsAnswered();
    assertEquals(ssd.getQuestionsAnswered(), 2);
  }

  /**
   * Checks that calling updateHardToEasy twice changes the hardToEasyCount to 2.
   */
  @Test
  public void testUpdateHardToEasyCount() {
    ssd.updateHardToEasyCount();
    ssd.updateHardToEasyCount();
    assertEquals(ssd.getHardToEasyCount(), 2);
  }

  /**
   * Checks that calling updateEasyToHardCount twice changes the easyToHardCount to 2.
   */
  @Test
  public void testUpdateEasyToHardCount() {
    ssd.updateEasyToHardCount();
    ssd.updateEasyToHardCount();
    assertEquals(ssd.getEasyToHardCount(), 2);
  }
}