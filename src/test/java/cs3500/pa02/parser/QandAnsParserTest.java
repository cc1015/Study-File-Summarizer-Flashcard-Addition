package cs3500.pa02.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa02.model.ssmodel.Difficulty;
import cs3500.pa02.model.ssmodel.Flashcard;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the QandAnsParser class.
 */
class QandAnsParserTest {
  QandAnsParser parser;
  ArrayList<StringBuilder> strings;
  ArrayList<Flashcard> cards;

  /**
   * Initialize data; creates a study guide with a main header (# header)
   */
  @BeforeEach
  public void initData() {
    this.parser = new QandAnsParser();
    this.strings = new ArrayList<>();
    this.cards = new ArrayList<>();
  }

  /**
   * Checks that a qAnda block in one line is parsed correctly
   */
  @Test
  public void testQuestionParse() {
    cards.add(new Flashcard("question?", "answer", Difficulty.HARD));

    strings.add(new StringBuilder("this is a [[question?:::answer]]"));

    assertEquals(parser.parse(strings), cards);
  }

  /**
   * Checks that a qAndA block that spans two lines is parsed as one Flashcard.
   * Input:
   * this is a [[question?
   * :::answer]]
   * Expected output:
   * question?:::answer
   */
  @Test
  public void testQuestionBlockTwoLines() {
    strings.add(new StringBuilder("this is a [[question?"));
    strings.add(new StringBuilder(":::answer]]"));

    cards.add(new Flashcard("question?", "answer", Difficulty.HARD));

    assertEquals(parser.parse(strings), cards);
  }

  /**
   * Checks that a line with two qAnda blocks is parsed correctly into two Flashcards.
   * Input:
   * this is a [[question?:::answer]] and another [[question2?:::answer]]
   * Expected output:
   * question?:::answer
   * question2?:::answer
   */
  @Test
  public void testQuestionBlockTwoInLine() {
    strings.add(
        new StringBuilder("this is a [[question?:::answer]] and another [[question2?:::answer]]"));

    cards.add(new Flashcard("question?", "answer", Difficulty.HARD));
    cards.add(new Flashcard("question2?", "answer", Difficulty.HARD));


    assertEquals(parser.parse(strings), cards);
  }

  /**
   * Checks that a qAndA block only surrounded by a beginning bracket is not parsed as a Flashcard.
   * Input:
   * [[this is a question?:::answer
   * Expected output:
   */
  @Test
  public void testOneBracket() {
    strings.add(new StringBuilder("[[this is a question?:::answer"));

    assertEquals(parser.parse(strings), cards);
  }

  /**
   * Checks that a qAndA block not surrounded by double brackets is not parsed as a Flashcard.
   * Input:
   * this is a question?:::answer
   * Expected output:
   */
  @Test
  public void testNoBracket() {
    strings.add(new StringBuilder("this is a question?:::answer"));

    assertEquals(parser.parse(strings), cards);
  }

  /**
   * Checks that a q and a block with white spaces is parsed correctly.
   * Input:
   * [[question? :::    answer]]
   * Expected output:
   * question?:::answer
   */
  @Test
  public void testWhiteSpace() {
    strings.add(new StringBuilder("[[question? :::    answer]]"));

    cards.add(new Flashcard("question?", "answer", Difficulty.HARD));

    assertEquals(parser.parse(strings), cards);
  }

  /*
  edges cases tested:
  two line q and a block
  two q and a blocks in one line
  only beginning double bracket block
  no bracket block
  q and a block with white spaces between question, :::, and answer
   */
}