package cs3500.pa02.model.ssmodel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for FlashcardBank class.
 */
class FlashcardBankTest {
  FlashcardBank bank;
  Flashcard easyCardOne;
  Flashcard easyCardTwo;
  Flashcard easyCardThree;
  Flashcard hardCardOne;
  Flashcard hardCardTwo;

  ArrayList<Flashcard> allCards = new ArrayList<>();
  ArrayList<Flashcard> easyCards = new ArrayList<>();
  ArrayList<Flashcard> hardCards = new ArrayList<>();

  /**
   * Initialize data.
   */
  @BeforeEach
  public void initData() {
    easyCardOne = new Flashcard("easy question 1?", "answer 1.", Difficulty.EASY);
    easyCardTwo = new Flashcard("easy question 2?", "answer 2.", Difficulty.EASY);
    easyCardThree = new Flashcard("easy question 3?", "answer 3.", Difficulty.EASY);
    hardCardOne = new Flashcard("hard question 1?", "hard answer 1.", Difficulty.HARD);
    hardCardTwo = new Flashcard("hard question 2?", "hard answer 2.", Difficulty.HARD);

    allCards = new ArrayList<>();
    allCards.add(easyCardOne);
    allCards.add(easyCardTwo);
    allCards.add(easyCardThree);
    allCards.add(hardCardOne);
    allCards.add(hardCardTwo);

    easyCards = new ArrayList<>();
    easyCards.add(easyCardOne);
    easyCards.add(easyCardTwo);
    easyCards.add(easyCardThree);

    hardCards = new ArrayList<>();
    hardCards.add(hardCardOne);
    hardCards.add(hardCardTwo);
  }

  /**
   * Checks that the constructor correctly sorts easy cards to the easyCards field given
   * only easy cards.
   */
  @Test
  public void testConstructOne() {
    bank = new FlashcardBank(easyCards);
    assertNotNull(bank);
    assertEquals(bank.getEasyCount(), 3);
    assertEquals(bank.getHardCount(), 0);
  }

  /**
   * Checks that the constructor correctly sorts hard cards to the hardCards field given
   * only hard cards.
   */
  @Test
  public void testConstructTwo() {
    bank = new FlashcardBank(hardCards);
    assertNotNull(bank);
    assertEquals(bank.getEasyCount(), 0);
    assertEquals(bank.getHardCount(), 2);
  }

  /**
   * Checks that the constructor correctly sorts hard cards to the hardCards field and the easy
   * cards to the easyCards field given easy and hard cards.
   */
  @Test
  public void testConstructThree() {
    bank = new FlashcardBank(allCards);
    assertNotNull(bank);
    assertEquals(bank.getEasyCount(), 3);
    assertEquals(bank.getHardCount(), 2);
  }

  /**
   * Checks that writeCollection correctly converts Flashcards to strings, hard cards first.
   */
  @Test
  public void testWriteCollection() {
    bank = new FlashcardBank(allCards);
    String s1 = "[E] easy question 1?:::answer 1.";
    String s2 = "[E] easy question 2?:::answer 2.";
    String s3 = "[E] easy question 3?:::answer 3.";
    String s4 = "[H] hard question 1?:::hard answer 1.";
    String s5 = "[H] hard question 2?:::hard answer 2.";

    ArrayList<String> strings = new ArrayList<>();

    strings.add(s4);
    strings.add(s5);
    strings.add(s1);
    strings.add(s2);
    strings.add(s3);

    assertEquals(bank.writeCollection(), strings);
  }

  /**
   * Checks that generateRandomCards can generate 3 random cards, hard cards first.
   */
  @Test
  public void testGenerateRandomCards() {
    bank = new FlashcardBank(allCards);
    ArrayList<Flashcard> randCards = new ArrayList<>();
    randCards.add(hardCardOne);
    randCards.add(hardCardTwo);
    randCards.add(easyCardThree);

    assertEquals(bank.generateRandomCards(3, new Random(1)), randCards);
  }

  /**
   * Checks that generateRandomCards generates all cards, hard ones first, when given number
   * higher than number of total cards in the bank.
   */
  @Test
  public void testGenerateRandomCardsTwo() {
    bank = new FlashcardBank(allCards);
    ArrayList<Flashcard> randCards = new ArrayList<>();
    randCards.add(hardCardOne);
    randCards.add(hardCardTwo);
    randCards.add(easyCardThree);
    randCards.add(easyCardOne);
    randCards.add(easyCardTwo);

    assertEquals(bank.generateRandomCards(70, new Random(1)), randCards);
  }

  /**
   * Checks that an easy card is changed to a hard card and that it gets moved to the hard card
   * list in the FlashcardBank.
   */
  @Test
  public void testUpdateDiffEasy() {
    bank = new FlashcardBank(allCards);

    bank.updateDifficulty(easyCardOne, Difficulty.HARD);

    assertEquals(easyCardOne.getDifficulty(), Difficulty.HARD);
    assertEquals(bank.getEasyCount(), 2);
    assertEquals(bank.getHardCount(), 3);
  }

  /**
   * Checks that a hard card is changed to an easy card and that it gets moved to the easy card
   * list in the FlashcardBank.
   */
  @Test
  public void testUpdateDiffHard() {
    bank = new FlashcardBank(allCards);

    bank.updateDifficulty(hardCardOne, Difficulty.EASY);

    assertEquals(hardCardOne.getDifficulty(), Difficulty.EASY);
    assertEquals(bank.getEasyCount(), 4);
    assertEquals(bank.getHardCount(), 1);
  }

  /**
   * Checks that an easy card is not changed when passing in easy as the new difficulty.
   */
  @Test
  public void testUpdateDiffNoChangeEasy() {
    bank = new FlashcardBank(allCards);

    bank.updateDifficulty(easyCardOne, Difficulty.EASY);

    assertEquals(easyCardOne.getDifficulty(), Difficulty.EASY);
    assertEquals(bank.getEasyCount(), 3);
    assertEquals(bank.getHardCount(), 2);
  }

  /**
   * Checks that a hard card is not changed when passing in hard as the new difficulty.
   */
  @Test
  public void testUpdateDiffNoChangeHard() {
    bank = new FlashcardBank(allCards);

    bank.updateDifficulty(hardCardOne, Difficulty.HARD);

    assertEquals(hardCardOne.getDifficulty(), Difficulty.HARD);
    assertEquals(bank.getEasyCount(), 3);
    assertEquals(bank.getHardCount(), 2);
  }

  /**
   * Checks that updateDifficulty throws an error when a flashcard that is not in the bank is
   * passed in.
   */
  @Test
  public void testChangeDifficultyError() {
    bank = new FlashcardBank(allCards);
    Flashcard card = new Flashcard("q", "a", Difficulty.HARD);

    assertThrows(RuntimeException.class,
        () -> bank.updateDifficulty(card, Difficulty.EASY));
  }

  /**
   * Checks that equals fails for a non-flashcard bank
   */
  @Test
  public void testEqualsFail() {
    bank = new FlashcardBank(allCards);
    assertFalse(bank.equals("String"));
  }
}