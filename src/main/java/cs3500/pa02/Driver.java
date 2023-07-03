package cs3500.pa02;

import cs3500.pa02.controller.Controller;
import cs3500.pa02.controller.StudyGuideController;
import cs3500.pa02.controller.StudySessionController;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Random;

/**
 * The main method of this project. Houses the main method entry point.
 */
public class Driver {
  /**
   * Program entry point; if 3 args are given, creates a new instance of the study guide controller
   * and executes the program. If no args are given, creates a new instance of the study session
   * controller, passing in System.in and System.out as input and output, respective, and executes
   * the program.
   *
   * @param args root directory, sorting mechanism, destination directory OR no args
   */
  public static void main(String[] args) {
    Controller controller;

    if (args.length == 3) {
      String root = args[0];
      String sortingMech = args[1];
      String destination = args[2];
      controller = new StudyGuideController(root, sortingMech, destination);
    } else {
      Readable input = new InputStreamReader(System.in);
      Appendable output = new PrintStream(System.out);
      controller = new StudySessionController(input, output, new Random());
    }

    controller.executeProgram();
  }
}
