package cs3500.pa02;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test class for the Driver class.
 */
class DriverTest {
  Driver dr;

  /**
   * initialize data
   */
  @BeforeEach
  public void initData() {
    dr = new Driver();
  }

  /**
   * Checks if main writes file correctly for filename
   */
  @Test
  public void testMainName() {
    String[] args = new String[] {"src/test/resources/ex_root", "filename",
        "src/test/resources/ex_sds/filename_ex.md"};
    Path p = Path.of("src/test/resources/ex_sds/filename_ex.md");
    assertFalse(Files.exists(p));
    dr.main(args);
    assertTrue(Files.exists(p));
    try {
      Files.delete(p);
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Checks if main writes file correctly for modified
   */
  @Test
  public void testMainModified() {
    String[] args = new String[] {"src/test/resources/ex_root", "modified",
        "src/test/resources/ex_sds/modified_ex.md"};
    Path p = Path.of("src/test/resources/ex_sds/modified_ex.md");
    assertFalse(Files.exists(p));
    dr.main(args);
    assertTrue(Files.exists(p));
    try {
      Files.delete(p);
    } catch (IOException e) {
      fail();
    }
  }

  /**
   * Checks if main writes file correctly for created
   */
  @Test
  public void testMainCreated() {
    String[] args = new String[] {"src/test/resources/ex_root", "created",
        "src/test/resources/ex_sds/created_ex.md"};
    Path p = Path.of("src/test/resources/ex_sds/created_ex.md");
    assertFalse(Files.exists(p));
    dr.main(args);
    assertTrue(Files.exists(p));
    try {
      Files.delete(p);
    } catch (IOException e) {
      fail();
    }
  }
}