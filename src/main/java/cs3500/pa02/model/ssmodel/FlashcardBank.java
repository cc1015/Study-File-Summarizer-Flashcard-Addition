package cs3500.pa02.model.ssmodel;

import cs3500.pa02.model.StudyCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Represents a flashcard bank (a collection of flashcards) in a study session.
 */
public class FlashcardBank implements StudyCollection {
  private ArrayList<Flashcard> easyCards;
  private ArrayList<Flashcard> hardCards;

  /**
   * Convenience constructor that takes in a list of flashcards and calls filterCards to filter
   * them into the easyCards and hardCards fields based on difficulty.
   *
   * @param allCards The list of all cards in this FlashcardBank.
   */
  public FlashcardBank(ArrayList<Flashcard> allCards) {
    this.easyCards = new ArrayList<>();
    this.hardCards = new ArrayList<>();
    filterCards(allCards);
  }

  /**
   * Writes each flashcard as a String in this FlashCard's list of cards (hard first)
   * by calling writeContent for each Flashcard and adding the returned String to a list.
   *
   * @return A list of Strings that are the Flashcard's as Strings.
   */
  public ArrayList<String> writeCollection() {
    ArrayList<String> cards = new ArrayList<>();

    for (Flashcard card : this.hardCards) {
      cards.add(card.toString());
    }

    for (Flashcard card : this.easyCards) {
      cards.add(card.toString());
    }

    return cards;
  }

  /**
   * Generates the given number of random cards, hard cards first.
   *
   * @param cardCount The number of cards to be returned.
   * @param rand      The random object to shuffle the cards.
   * @return The shuffled cards.
   */
  public ArrayList<Flashcard> generateRandomCards(int cardCount, Random rand) {
    Collections.shuffle(this.hardCards, rand);
    Collections.shuffle(this.easyCards, rand);

    ArrayList<Flashcard> allCards = new ArrayList<>();
    allCards.addAll(this.hardCards);
    allCards.addAll(this.easyCards);

    ArrayList<Flashcard> generatedCards = new ArrayList<>();

    if (cardCount > allCards.size()) {
      cardCount = allCards.size();
    }

    for (int i = 0; i < cardCount; i += 1) {
      Flashcard nextCard = allCards.get(i);
      generatedCards.add(nextCard);
    }

    return generatedCards;
  }

  /**
   * If the given flashcard is the opposite difficulty of the given new difficulty, then the given
   * card's difficulty is switched, and the card is removed from its original difficulty list
   * and added to the other list.
   *
   * @param f             The flashcard to be updated.
   * @param newDifficulty The new difficulty of the flashcard.
   * @return True if flashcard's difficulty was updated, false otherwise.
   */
  public boolean updateDifficulty(Flashcard f, Difficulty newDifficulty) {
    boolean isEasy = this.easyCards.contains(f);
    boolean isHard = this.hardCards.contains(f);

    if (!isEasy && !isHard) {
      throw new RuntimeException("The given card is not in this flashcard bank.");
    }

    switch (newDifficulty) {
      case EASY:
        if (isHard) {
          f.changeDifficulty(Difficulty.EASY);
          this.hardCards.remove(f);
          this.easyCards.add(f);
          return true;
        }
        return false;
      case HARD:
        if (isEasy) {
          f.changeDifficulty(Difficulty.HARD);
          this.easyCards.remove(f);
          this.hardCards.add(f);
          return true;
        }
        return false;
      default:
        return false;
    }
  }

  /**
   * Returns the number of cards in the easyCards list.
   *
   * @return The number of easy cards in this FlashcardBank.
   */
  public int getEasyCount() {
    return this.easyCards.size();
  }

  /**
   * Returns the number of cards in the hardCards list.
   *
   * @return The number of hard cards in this FlashcardBank.
   */
  public int getHardCount() {
    return this.hardCards.size();
  }

  /**
   * Filters the given list of Flashcards into this Flashcard's list of easyCards and hardCards.
   *
   * @param allCards The list of Flashcards to be filtered.
   */
  private void filterCards(ArrayList<Flashcard> allCards) {
    for (Flashcard card : allCards) {
      switch (card.getDifficulty()) {
        case EASY:
          this.easyCards.add(card);
          break;
        case HARD:
          this.hardCards.add(card);
          break;
        default:
          throw new RuntimeException("Difficulty could not be obtained.");
      }
    }
  }

  /**
   * Two FlashcardBanks are equal if all flashcards in the banks are equal.
   *
   * @param other The other object being compared to.
   * @return Whether this FlashcardBank and the given object are the same.
   */
  @Override
  public boolean equals(Object other) {
    if (!(other instanceof FlashcardBank)) {
      return false;
    }
    FlashcardBank that = (FlashcardBank) other;
    return this.writeCollection().equals(that.writeCollection());
  }
}
