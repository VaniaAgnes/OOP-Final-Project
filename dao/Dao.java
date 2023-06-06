package dao;

import java.util.ArrayList;

/**
 * @interface Dao
 * 
 * Consist of the method that an Object's Dao should implement.
 */
public interface Dao<T> {
  public void read();
  public void save();
  public T parseLine(String line);
  public ArrayList<T> getAll();
  public T get(String id);
  public void create(T data);
  public void remove(String id);
}
