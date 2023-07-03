package cs3500.pa02.parser;

import java.util.ArrayList;

/**
 * Represents a parser that parses StringBuilder's into relevant information.
 */
public interface Parser<T> {
  /**
   * Parses the given list of StringBuilders into relevant information.
   *
   * @param content The StringBuilders to be parsed.
   */
  public ArrayList<T> parse(ArrayList<StringBuilder> content);
}
