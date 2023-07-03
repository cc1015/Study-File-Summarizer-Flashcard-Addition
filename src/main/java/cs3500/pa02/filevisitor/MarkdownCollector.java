package cs3500.pa02.filevisitor;

import cs3500.pa02.model.sgmodel.MdFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * Represents a file visitor that walks the file system and creates a MdFile for every .md file
 * it encounters and adds the MdFile to a list (to keep track of metadata). Also adds the
 * file itself to another list to store the file itself.
 */
public class MarkdownCollector implements FileVisitor<Path> {
  private final ArrayList<MdFile> mdFiles;
  private final ArrayList<File> files;

  /**
   * Constructor for MarkdownCollector that sets mdFiles to the given list of MdFiles and sets
   * files to the given list of files.
   *
   * @param mdFiles the given list of MdFiles
   * @param files the given list of files
   */
  public MarkdownCollector(ArrayList<MdFile> mdFiles, ArrayList<File> files) {
    this.mdFiles = mdFiles;
    this.files = files;
  }

  /**
   * Before visiting a file.
   *
   * @param dir   a reference to the directory
   * @param attrs the directory's basic attributes
   *
   * @return the visit result
   * @throws IOException if an I/O error occurs
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
    return FileVisitResult.CONTINUE;
  }

  /**
   * When visiting a file, check if is a .md file. If so, create a new MdFile with the metadata
   * and add it to mdFiles list.
   *
   * @param file  a reference to the file
   * @param attrs the file's basic attributes
   *
   * @return the visit result
   * @throws IOException if an I/O error occurs
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
    File f = new File(file.toString());
    String name = f.getName();
    if (name.endsWith(".md")) {
      this.files.add(f);
      MdFile md = new MdFile(name, f.getPath(), attrs.creationTime(), attrs.lastModifiedTime());
      this.mdFiles.add(md);
    }
    return FileVisitResult.CONTINUE;
  }

  /**
   * When a file cannot be visited.
   *
   * @param file a reference to the file
   * @param exc  the I/O exception that prevented the file from being visited
   *
   * @return the visit result
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) {
    failHandler();
    return FileVisitResult.CONTINUE;
  }

  /**
   * handler for visitFileFailed, throws a RuntimeException noting that the file visit has failed
   *
   */
  public void failHandler() {
    throw new RuntimeException("file cannot be visited");
  }

  /**
   * After a file is visited.
   *
   * @param dir a reference to the directory
   * @param exc {@code null} if the iteration of the directory completes without
   *            an error; otherwise the I/O exception that caused the iteration
   *            of the directory to complete prematurely
   *
   * @return the visit result
   * @throws IOException if an I/O error occurs
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
    return FileVisitResult.CONTINUE;
  }
}