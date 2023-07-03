package cs3500.pa02;

import java.io.IOException;

/**
 * A mock appendable class for testing try/catch blocks
 */
public class MockAppendable implements Appendable {

  /**
   * Calls throwInOut() to throw IOException
   *
   * @param csq
   *         The character sequence to append.  If {@code csq} is
   *         {@code null}, then the four characters {@code "null"} are
   *         appended to this Appendable.
   *
   * @return null
   * @throws IOException "Mock throwing an error"
   */
  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throwInOut();
    return null;
  }

  /**
   * Calls throwInOut() to throw IOException
   *
   * @param csq
   *         The character sequence from which a subsequence will be
   *         appended.  If {@code csq} is {@code null}, then characters
   *         will be appended as if {@code csq} contained the four
   *         characters {@code "null"}.
   *
   * @param start
   *         The index of the first character in the subsequence
   *
   * @param end
   *         The index of the character following the last character in the
   *         subsequence
   *
   * @return null
   * @throws IOException "Mock throwing an error"
   */
  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throwInOut();
    return null;
  }

  /**
   * Calls throwInOut() to throw IOException
   *
   * @param c The character to append
   *
   * @return null
   * @throws IOException "Mock throwing an error"
   */
  @Override
  public Appendable append(char c) throws IOException {
    throwInOut();
    return null;
  }

  /**
   * Throws IOException
   *
   * @throws IOException "Mock throwing an error"
   */
  private void throwInOut() throws IOException {
    throw new IOException("Mock throwing an error");
  }
}
