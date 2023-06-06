package model;

/**
 * @abstract Model
 *
 * An abstract class Model that consist of attribute and method
 * that an Object's Model should have. The standard is to have
 * id attributes and getId() method.
 */
public abstract class Model {
  protected String id;

  public String getId() {
    return id;
  }

  public abstract String toLine();
}
