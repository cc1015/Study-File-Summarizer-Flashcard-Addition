package cs3500.pa02.reader;

import java.util.ArrayList;

/**
 * Represents a reader.
 */
public interface Reader {
  /**
   * Reads and returns the read content as a list of StringBuilders.
   *
   * @return The read content.
   */
  ArrayList<StringBuilder> read();
}
