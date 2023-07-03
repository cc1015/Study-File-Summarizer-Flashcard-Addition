package cs3500.pa02.model.ssmodel;

/**
 * Represents a flashcard in a study session that has a question, answer, and difficulty.
 */
public class Flashcard {
  private String question;
  private String answer;
  private Difficulty difficulty;

  /**
   * Sets fields to given question, answer, and difficulty.
   *
   * @param question   The String the question field is set to.
   * @param answer     The String the answer field is set to.
   * @param difficulty The Difficulty enum the difficulty field is set to.
   */
  public Flashcard(String question, String answer, Difficulty difficulty) {
    this.question = question;
    this.answer = answer;
    this.difficulty = difficulty;
  }

  /**
   * Returns this Flashcard as a String, formatted: Q?:::A.
   *
   * @return This Flashcard as a String.
   */
  @Override
  public String toString() {
    return "[" + this.difficulty.toString().substring(0, 1) + "] " + this.question + ":::"
        + this.answer;
  }

  /**
   * Returns this Flashcard's question.
   *
   * @return This Flashcard's question.
   */
  public String questionToString() {
    return this.question;
  }

  /**
   * Returns this Flashcard's answer.
   *
   * @return This Flashcard's answer.
   */
  public String answerToString() {
    return this.answer;
  }

  /**
   * Returns this Flashcard's difficulty.
   *
   * @return This Flashcard's difficulty.
   */
  public Difficulty getDifficulty() {
    return this.difficulty;
  }

  /**
   * Sets this Flashcard's difficulty to the given difficulty.
   */
  public void changeDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * Determine if this Flashcard and the given object are equal (have same question, answer, and
   * difficulty.
   *
   * @param other The other object being compared.
   * @return Whether or not the two are equal.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Flashcard)) {
      return false;
    }
    Flashcard that = (Flashcard) other;
    return this.toString().equals(that.toString());
  }
}
