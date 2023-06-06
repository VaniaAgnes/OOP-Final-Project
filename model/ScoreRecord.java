package model;

import java.util.UUID;

public class ScoreRecord extends Model {
  private Student student;
  private double scienceScore, socialScore, mathScore, religionScore, linguisticScore, ethicScore;

  //default constructor
  public ScoreRecord() {
    this.id = UUID.randomUUID().toString();
    this.student = null;
    this.scienceScore = this.socialScore = this.mathScore = this.religionScore = this.linguisticScore = this.ethicScore = 0;
  }

  //takes the student object as parameters and initialize the instance variables
  public ScoreRecord(Student student, double scienceScore, double socialScore, double mathScore, double religionScore,
      double linguisticScore, double ethicScore) {
    this.id = UUID.randomUUID().toString();
    this.student = student;
    this.scienceScore = scienceScore;
    this.socialScore = socialScore;
    this.mathScore = mathScore;
    this.religionScore = religionScore;
    this.linguisticScore = linguisticScore;
    this.ethicScore = ethicScore;
  }

  public ScoreRecord(String id, Student student, double scienceScore, double socialScore, double mathScore,
      double religionScore, double linguisticScore, double ethicScore) {
    this.id = id;
    this.student = student;
    this.scienceScore = scienceScore;
    this.socialScore = socialScore;
    this.mathScore = mathScore;
    this.religionScore = religionScore;
    this.linguisticScore = linguisticScore;
    this.ethicScore = ethicScore;
  }

  @Override
  public String toLine() {
    String line = String.format("%s;%s;%f;%f;%f;%f;%f;%f",
        id,
        student.getId(),
        scienceScore,
        socialScore,
        mathScore,
        religionScore,
        linguisticScore,
        ethicScore);
    return line;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  public double getScienceScore() {
    return scienceScore;
  }

  public void setScienceScore(double scienceScore) {
    this.scienceScore = scienceScore;
  }

  public double getSocialScore() {
    return socialScore;
  }

  public void setSocialScore(double socialScore) {
    this.socialScore = socialScore;
  }

  public double getMathScore() {
    return mathScore;
  }

  public void setMathScore(double mathScore) {
    this.mathScore = mathScore;
  }

  public double getReligionScore() {
    return religionScore;
  }

  public void setReligionScore(double religionScore) {
    this.religionScore = religionScore;
  }

  public double getLinguisticScore() {
    return linguisticScore;
  }

  public void setLinguisticScore(double linguisticScore) {
    this.linguisticScore = linguisticScore;
  }

  public double getEthicScore() {
    return ethicScore;
  }

  public void setEthicScore(double ethicScore) {
    this.ethicScore = ethicScore;
  }

  public void printDetails() {
    System.out.printf("Student id\t\t\t: %s\n", this.student.getId());
    System.out.printf("Student name\t\t: %s\n", this.student.getName());
    System.out.printf("Science score\t\t: %s\n", this.scienceScore);
    System.out.printf("Social score\t\t: %s\n", this.socialScore);
    System.out.printf("Math score\t\t\t: %s\n", this.mathScore);
    System.out.printf("Linguistic score\t: %s\n", this.linguisticScore);
    System.out.printf("Religion scpre\t\t: %s\n", this.religionScore);
    System.out.printf("Ethic score\t\t\t: %s\n", this.ethicScore);
  }
}
