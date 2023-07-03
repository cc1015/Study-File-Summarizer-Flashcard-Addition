package cs3500.pa02.model.sgmodel;

import java.nio.file.attribute.FileTime;

/**
 * Represents a traversed .md file.
 */
public class MdFile {
  private String name;
  private String path;
  private FileTime creationTime;
  private FileTime modifyTime;

  /**
   * Constructor initializes fields to given values. Throws IllegalArgumentException if the given
   * creation time is after the modified time.
   *
   * @param name         the String the name field is set to
   * @param path         the String the path field is set to
   * @param creationTime the FileTime the creationTime is set to
   * @param modifyTime   the FileTime the modifyTime is set to
   */
  public MdFile(String name, String path, FileTime creationTime, FileTime modifyTime) {
    this.name = name;
    this.path = path;

    if (creationTime.compareTo(modifyTime) > 0) {
      throw new IllegalArgumentException("file modified time cannot be before the modified time");
    } else {
      this.creationTime = creationTime;
      this.modifyTime = modifyTime;
    }
  }

  /**
   * Returns this MdFile's name.
   *
   * @return this MdFile's name
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns this MdFile's path.
   *
   * @return this MdFile's path
   */
  public String getPath() {
    return this.path;
  }

  /**
   * Returns this MdFile's creation time.
   *
   * @return this MdFile's creation time
   */
  public FileTime getCreationTime() {
    return this.creationTime;
  }

  /**
   * Returns this MdFile's modify time.
   *
   * @return this MdFile's modify time
   */
  public FileTime getModifyTime() {
    return this.modifyTime;
  }
}
