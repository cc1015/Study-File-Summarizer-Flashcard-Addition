package cs3500.pa02.creator;

import cs3500.pa02.model.sgmodel.MdFile;
import cs3500.pa02.model.ssmodel.Flashcard;
import cs3500.pa02.model.ssmodel.FlashcardBank;
import cs3500.pa02.parser.Parser;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Represents a creator that creates a Flashcard back from a list of MdFiles
 */
public class FlashcardBankCreator extends StudyCollectionCreator {
  private Parser<Flashcard> parser;

  /**
   * Calls super constructor on the given list of fields, sets parser to the given parser.
   *
   * @param files The files to be summarized into a Flashcard bank.
   *
   * @param parser The parser that parses through the content of the files.
   */
  public FlashcardBankCreator(ArrayList<MdFile> files, Parser<Flashcard> parser) {
    super(files);
    this.parser = parser;
  }

  /**
   * Creates a study bank from parsing the content of this list of files.
   *
   * @return The FlashcardBank created.
   */
  public FlashcardBank createStudyBank() {
    ArrayList<Flashcard> cards = new ArrayList<>();

    // for every file, read it and parse the read content
    for (MdFile f : this.files) {
      String filePath = f.getPath();
      Path path = Path.of(filePath);
      ArrayList<StringBuilder> content = readInput(path);
      ArrayList<Flashcard> newCards = parser.parse(content);
      cards.addAll(newCards);
    }

    FlashcardBank bank = new FlashcardBank(cards);
    return bank;
  }
}
