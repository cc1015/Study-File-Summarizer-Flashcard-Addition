package cs3500.pa02.parser;

import cs3500.pa02.model.ssmodel.Difficulty;
import cs3500.pa02.model.ssmodel.Flashcard;
import java.util.ArrayList;

/**
 * Represents a parser that parses StringBuilders to Flashcards.
 */
public class QandAnsParser implements Parser<Flashcard> {
  /**
   * Parses the given list of StringBuilders into a list Flashcards with default difficulty of hard.
   *
   * @param content The StringBuilders to be parsed.
   *
   * @return The list of Flashcards as a result of parsing.
   */
  @Override
  public ArrayList<Flashcard> parse(ArrayList<StringBuilder> content) {
    ArrayList<Flashcard> allCards = new ArrayList<>();

    StringBuilder info = new StringBuilder();

    // Create a single StringBuilder from the list of StringBuilders
    for (StringBuilder line : content) {
      info.append(line);
    }

    allCards = parseFlashcards(info);
    return allCards;
  }

  /**
   * Check each character, and save as Flashcard if the character sequence matches the structure of
   * a q and a block.
   *
   * @param content The StringBuilder to be parsed.
   *
   * @return The list of flashcards as a result of parsing.
   */
  private ArrayList<Flashcard> parseFlashcards(StringBuilder content) {
    ArrayList<Flashcard> cards = new ArrayList<>();

    // while content has characters, parse it
    while (content.length() > 0) {
      StringBuilder qblock = new StringBuilder();

      if (content.length() > 1 && (content.charAt(0) == '[') && (content.charAt(1) == '[')) {
        content.deleteCharAt(0);
        content.deleteCharAt(0);

        // content has characters and the first and second chars are not ']', add the first char
        // to qAndaBlock and delete the just parsed char from content
        while (content.length() > 0
            && !((content.charAt(0) == ']') && (content.charAt(1) == ']'))) {
          qblock.append(content.charAt(0));
          content.deleteCharAt(0);
        }

        if (content.length() > 0) {
          content.deleteCharAt(0);
          content.deleteCharAt(0);

          // create flashcard
          if (qblock.toString().contains(":::")) {
            Flashcard f = createFlashcard(qblock);
            cards.add(f);
          }

        }
      } else {
        content.deleteCharAt(0);
      }
    }
    return cards;

  }

  /**
   * Create a new Flashcard with a default difficulty of hard with the given StringBuilder.
   *
   * @param s The given StringBuilder to be turned into a Flashcard.
   *
   * @return The new Flashcard.
   */
  private Flashcard createFlashcard(StringBuilder s) {
    int questionStart = 0;
    int divider = s.indexOf(":::");
    int questionEnd = divider;
    int answerStart = divider + 3;

    // get rid of whitespaces
    while (s.charAt(questionEnd - 1) == ' ') {
      questionEnd -= 1;
    }

    // get rid of whitespaces
    while (s.charAt(answerStart) == ' ') {
      answerStart += 1;
    }

    Flashcard f = new Flashcard(s.substring(questionStart, questionEnd), s.substring(answerStart),
        Difficulty.HARD);
    return f;
  }
}
