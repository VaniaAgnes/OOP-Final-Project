package dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.ScoreRecord;
// score record implements the dao interface
public class ScoreRecordDao implements Dao<ScoreRecord> {
 // holds the file path
  private String FILE_PATH = "resources/grade-record.txt";
  private ArrayList<ScoreRecord> gradeRecords;
  private StudentDao studentDao;

  public ScoreRecordDao() {
    this.gradeRecords = new ArrayList<ScoreRecord>();
    this.studentDao = new StudentDao();

    File file = new File(FILE_PATH);

    if (file.exists()) {
      System.out.println("grade-record.txt exist!");
      this.read();
    } else {
      System.out.println("grade-record.txt created!");
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

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
          gradeRecords.add(this.parseLine(lines[i]));
        }
      }

      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
// saves the data in the file
  @Override
  public void save() {
    try {
      BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));

      StringBuilder builder = new StringBuilder();

      this.gradeRecords.forEach(gradeRecord -> {
        builder.append(gradeRecord.toLine()).append("\n");
      });

      writer.write(builder.toString());

      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
// seperates the line into tokens using semicolon
  @Override
  public ScoreRecord parseLine(String line) {
    String tokens[] = line.split(";");

    String id = tokens[0];
    String studentId = tokens[1];
    double scienceScore = Double.parseDouble(tokens[2]);
    double socialScore = Double.parseDouble(tokens[3]);
    double mathScore = Double.parseDouble(tokens[4]);
    double religionScore = Double.parseDouble(tokens[5]);
    double linguisticScore = Double.parseDouble(tokens[6]);
    double ethicScore = Double.parseDouble(tokens[7]);

    return new ScoreRecord(id, this.studentDao.get(studentId), scienceScore, socialScore, mathScore,
        religionScore, linguisticScore, ethicScore);
  }

  @Override
  public ArrayList<ScoreRecord> getAll() {
    return this.gradeRecords;
  }
// see an object based on it's id
  @Override
  public ScoreRecord get(String id) {
    for (int i = 0; i < this.gradeRecords.size(); i++) {
      if (this.gradeRecords.get(i).getId().equals(id)) {
        return this.gradeRecords.get(i);
      }
    }

    return null; // if the grade doesn't exist!
  }

  // retrieving the data based on the id
  public ScoreRecord getByStudentId(String studentId) {
    for (int i = 0; i < this.gradeRecords.size(); i++) {
      if (this.gradeRecords.get(i).getStudent().getId().equals(studentId)) {
        return this.gradeRecords.get(i);
      }
    }

    return null;
  }
// this is used to update an inputted score
  @Override
  public void create(ScoreRecord gradeRecord) {
    this.gradeRecords.add(gradeRecord);
    this.save();
  }
// remove an object based on it's id
  @Override
  public void remove(String id) {
    ScoreRecord gradeRecord = this.get(id);
    if (gradeRecord != null) {
      this.gradeRecords.remove(gradeRecord);
      this.save();
    }
  }
}
