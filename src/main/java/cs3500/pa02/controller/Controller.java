package cs3500.pa02.controller;

import cs3500.pa02.model.StudyCollection;

/**
 * Represents a controller of a program.
 */
public interface Controller {
  /**
   * Executes the program to perform the functions of the controller.
   */
  public void executeProgram();

  /**
   * Writes the given content onto the given file path.
   *
   * @param content The content to be written.
   *
   * @param destPath The destination path of the file being written on.
   */
  public void writeFile(StudyCollection content, String destPath);
}
