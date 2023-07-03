package cs3500.pa02.model.ssmodel;

/**
 * Represents data of a study session.
 */
public class StudySessionData {
  private int questionsAnswered;
  private int hardToEasyCount;
  private int easyToHardCount;

  /**
   * Sets questionsAnswered, hardToEasyCount, and easyToHardCount to 0.
   */
  public StudySessionData() {
    this.questionsAnswered = 0;
    this.hardToEasyCount = 0;
    this.easyToHardCount = 0;
  }

  /**
   * Returns this StudySessionData's questionsAnswered.
   *
   * @return This StudySessionData's questionsAnswered.
   */
  public int getQuestionsAnswered() {
    return questionsAnswered;
  }

  /**
   * Returns this StudySessionData's hardToEasyCount.
   *
   * @return This StudySessionData's hardToEasyCount.
   */
  public int getHardToEasyCount() {
    return hardToEasyCount;
  }

  /**
   * Returns this StudySessionData's easyToHardCount.
   *
   * @return This StudySessionData's easyToHardCount.
   */
  public int getEasyToHardCount() {
    return easyToHardCount;
  }

  /**
   * Increments this StudySessionData's questionsAnswered field by 1.
   */
  public void updateQuestionsAnswered() {
    this.questionsAnswered += 1;
  }

  /**
   * Increments this StudySessionData's hardToEasyCount field by 1.
   */
  public void updateHardToEasyCount() {
    this.hardToEasyCount += 1;
  }

  /**
   * Increments this StudySessionData's easyToHardCount field by 1.
   */
  public void updateEasyToHardCount() {
    this.easyToHardCount += 1;
  }
}
