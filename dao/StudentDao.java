package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import model.Student;

// Student Data Access Object
public class StudentDao implements Dao<Student> {
  private String FILE_PATH = "resources/students.txt";
  // stores the data of the student
  private ArrayList<Student> students;

  public StudentDao() {
    this.students = new ArrayList<Student>();

    File file = new File(FILE_PATH);

    if (file.exists()) {
      // Load the file if file exist.
      System.out.println("students.txt exist!");
      this.read();
    } else {
      // Create the file if file doesn't exist
      System.out.println("students.txt created!");
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  // Load student data from the file.
  @Override
  public void read() {
    StringBuilder fileContent = new StringBuilder();
    try {
      // Reading the file (load all of the data from the file)
      BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));

      String line;
      while ((line = reader.readLine()) != null) {
        fileContent.append(line).append("\n");
      }

      // If the file is not empty
      if (fileContent.toString().length() != 0) {
        String lines[] = fileContent.toString().split("\n");
        for (int i = 0; i < lines.length; i++) {
          // parse the line to Student object, then add the object to `students`.
          students.add(this.parseLine(lines[i]));
        }
      }

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Save the students ArrayList data to the file
  @Override
  public void save() {
    try {
      // Writing the file (save all of the data at students ArrayList to the file)
      BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

      StringBuilder builder = new StringBuilder();

      this.students.forEach(student -> {
        builder.append(student.toLine()).append("\n");
      });

      writer.write(builder.toString());

      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // parse a single line of student data stored in file to Student object
  @Override
  public Student parseLine(String line) {
    String tokens[] = line.split(";");

    String id = tokens[0];
    String name = tokens[1];
    boolean gender = Integer.valueOf(tokens[2]) == 1 ? true : false;
    String address = tokens[3];
    String father = tokens[4];
    String mother = tokens[5];
    String contactPhone = tokens[6];
    Calendar birthDate = Calendar.getInstance();
    birthDate.set(Integer.valueOf(tokens[9]), Integer.valueOf(tokens[8]), Integer.valueOf(tokens[7]));

    return new Student(id, name, gender, address, father, mother, contactPhone, birthDate);
  }

  // Retrieve all of the students data
  @Override
  public ArrayList<Student> getAll() {
    return this.students;
  }
  
  // Retrieve a single student data based on it's id
  @Override
  public Student get(String id) {
    for (int i = 0; i < this.students.size(); i++) {
      if (this.students.get(i).getId().equals(id)) {
        return this.students.get(i);
      }
    }

    return null; // if the studentId doesn't exist!
  }

  // Store a new Student object into the students list
  @Override
  public void create(Student student) {
    this.students.add(student);
    this.save();
  }

  // Remove a student based on the given student id
  @Override
  public void remove(String id) {
    Student student = this.get(id);
    if (student != null) {
      this.students.remove(student);
      this.save();
    }
  }
}
