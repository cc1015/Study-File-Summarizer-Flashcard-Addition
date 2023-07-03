package cs3500.pa02.parser;

import cs3500.pa02.model.ssmodel.Difficulty;
import cs3500.pa02.model.ssmodel.Flashcard;
import java.util.ArrayList;

/**
 * Represents a parser that parses a .sr file.
 */
public class SrParser implements Parser<Flashcard> {

  /**
   * Parses each StringBuilder in the given list into a Flashcard. Adds all the parsed Flashcards
   * to this list of cards.
   *
   * @param content The list of StringBuilders to be parsed.
   */
  @Override
  public ArrayList<Flashcard> parse(ArrayList<StringBuilder> content) {
    ArrayList<Flashcard> cards = new ArrayList<>();

    for (StringBuilder line : content) {
      cards.add(parseBlock(line));
    }

    return cards;
  }

  /**
   * Parses the given StringBuilder question block and creates a Flashcard from it.
   *
   * @param qblock The StringBuilder to be parsed.
   * @return The resulting Flashcard from parsing.
   */
  private Flashcard parseBlock(StringBuilder qblock) {
    int questionStart = qblock.indexOf("] ") + 2;
    int divider = qblock.indexOf(":::");
    int answerStart = divider + 3;

    String question = qblock.substring(questionStart, divider);
    String answer = qblock.substring(answerStart);

    String difficulty = qblock.substring(0, 3);
    Difficulty diff;

    if (difficulty.equals("[H]")) {
      diff = Difficulty.HARD;
    } else {
      diff = Difficulty.EASY;
    }

    Flashcard f = new Flashcard(question, answer, diff);
    return f;
  }
}
