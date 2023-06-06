import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import dao.ScoreRecordDao;
import dao.StudentDao;
import model.ScoreRecord;
import model.Student;
import utils.Table;

public class Main {
  private static Scanner scanner;
  private static StudentDao studentDao;
  private static ScoreRecordDao scoreRecordDao;

  public static void main(String[] args) {
    scanner = new Scanner(System.in);
    studentDao = new StudentDao();
    scoreRecordDao = new ScoreRecordDao();

    while (true) {
      System.out.println();

      System.out.println("=============================");
      System.out.println("  Student Management System");
      System.out.println("=============================");
      System.out.println("1. Input new student");
      System.out.println("2. View student list");
      System.out.println("3. View student details");
      System.out.println("4. Update existing student");
      System.out.println("5. Remove student");
      System.out.println("6. Input student score");
      System.out.println("7. Update student score");
      System.out.println("8. View student score");
      System.out.println("9. Exit");
      System.out.print("Choose menu: ");

      int menu = scanner.nextInt();

      scanner.nextLine();

      System.out.println();

      switch (menu) {
        case 1:
          menuInputNewStudent();
          break;
        case 2:
          menuViewStudents();
          break;
        case 3:
          menuViewStudentDetails();
          break;
        case 4:
          menuUpdateExistingStudent();
          break;
        case 5:
          menuRemoveStudent();
          break;
        case 6:
          menuInputStudentScore();
          break;
        case 7:
          menuUpdateStudentScore();
          break;
        case 8:
          menuViewStudentScore();
          break;
        case 9:
          System.out.println("Exiting the system...");
          return;
        default:
          System.out.println("The menu doesn't exist. Please input the proper menu number!");
          break;
      }
    }
  }

  private static void menuInputNewStudent() {
    System.out.println("Menu: Input New Student\n");

    System.out.print("Input name: ");
    String name = scanner.nextLine();

    System.out.print("Input gender [M/F]: ");
    String gender = scanner.nextLine();

    System.out.print("Input address: ");
    String address = scanner.nextLine();

    System.out.print("Input father name: ");
    String father = scanner.nextLine();

    System.out.print("Input mother name: ");
    String mother = scanner.nextLine();

    System.out.print("Input contact phone: ");
    String contactPhone = scanner.nextLine();

    System.out.println("Input birth date details:");
    System.out.print("Year [number]: ");
    int year = scanner.nextInt();

    System.out.print("Month [number]: ");
    int month = scanner.nextInt();

    System.out.print("Date [number]: ");
    int date = scanner.nextInt();

    Calendar birthDate = Calendar.getInstance();

    System.out.printf("%d %d\n", month, date);
    birthDate.set(year, month-1, date);

    System.out.println();

    studentDao.create(
        new Student(
            name,
            gender.equals("M") ? true : false,
            address,
            father,
            mother,
            contactPhone,
            birthDate));
  }

  private static void menuViewStudents() {
    System.out.println("Menu: View Students\n");

    System.out.println("STUDENT TABLE");
    String columns[] = { "ID", "Full name", "Gender", "Birth Date", "Father", "Mother", "Contact Phone" };
    int size[] = { 18, 30, 8, 16, 24, 24, 18 };
    Table table = new Table(7, columns, size);

    table.printHeader();

    // get all of the students data and output it as a beautiful row
    studentDao.getAll().forEach(student -> {
      String row[] = {
          student.getId(),
          student.getName(),
          student.isGender() ? "Male" : "Female",
          new SimpleDateFormat("dd-MM-yyyy").format((student.getBirthDate().getTime())),
          student.getFather(),
          student.getMother(),
          student.getContactPhone()
      };

      table.printRows(row);
    });
  }

  private static void menuViewStudentDetails() {
    System.out.println("Menu: View Student Details\n");
    System.out.print("Input student id: ");
    String studentId = scanner.nextLine();

    System.out.println();

    Student student = studentDao.get(studentId);

    if (student == null) {
      System.out.println("Student id doesn't exist");
    } else {
      System.out.println("Student details:");
      student.printDetails();
    }
  }

  private static void menuUpdateExistingStudent() {
    System.out.println("Menu: Update Existing Student\n");
    System.out.print("Input student id: ");
    String studentId = scanner.nextLine();

    System.out.println();

    Student student = studentDao.get(studentId);

    if (student == null) {
      System.out.println("Student id doesn't exist");
    } else {
      System.out.println("Current student details:");
      student.printDetails();

      System.out.println("\nNew student details:");

      System.out.print("Input name [Press ENTER to skip]: ");
      String name = scanner.nextLine();
      if (name.length() != 0) {
        student.setName(name);
      }

      System.out.print("Input gender [M/F] [Press ENTER to skip]: ");
      String gender = scanner.nextLine();
      if (gender.length() != 0) {
        student.setGender(gender == "M" ? true : false);
      }

      System.out.print("Input address [Press ENTER to skip]: ");
      String address = scanner.nextLine();
      if (address.length() != 0) {
        student.setAddress(address);
      }

      System.out.print("Input father name [Press ENTER to skip]: ");
      String father = scanner.nextLine();
      if (father.length() != 0) {
        student.setFather(father);
      }

      System.out.print("Input mother name [Press ENTER to skip]: ");
      String mother = scanner.nextLine();
      if (mother.length() != 0) {
        student.setMother(mother);
      }

      System.out.print("Input contact phone [Press ENTER to skip]: ");
      String contactPhone = scanner.nextLine();
      if (contactPhone.length() != 0) {
        student.setContactPhone(contactPhone);
      }

      System.out.println("Input birth date details:");
      System.out.print("Year [number] [Input -1 to skip]: ");
      int year = scanner.nextInt();
      if (year != -1) {
        student.getBirthDate().set(Calendar.YEAR, year);
      }

      System.out.print("Month [number] [Input -1 to skip]: ");
      int month = scanner.nextInt();
      if (month != -1) {
        student.getBirthDate().set(Calendar.MONTH, month-1);
      }

      System.out.print("Date [number] [Input -1 to skip]: ");
      int date = scanner.nextInt();
      if (date != -1) {
        student.getBirthDate().set(Calendar.DATE, date);
      }

      studentDao.save(); // save updated data
    }
  }

  private static void menuRemoveStudent() {
    System.out.println("Menu: Remove Student\n");
    System.out.print("Input student id: ");
    String studentId = scanner.nextLine();

    System.out.println();

    Student student = studentDao.get(studentId);

    if (student == null) {
      System.out.println("Student id doesn't exist");
    } else {
      studentDao.remove(studentId);
      
      ScoreRecord scoreRecord = scoreRecordDao.getByStudentId(studentId);

      if (scoreRecord != null) {
        scoreRecordDao.remove(scoreRecord.getId());
      }

      System.out.println("Student records has been remove successfully");
    }
  }

  private static void menuInputStudentScore() {
    System.out.println("Menu: Input Student Score\n");
    System.out.print("Input student id: ");
    String studentId = scanner.nextLine();

    System.out.println();

    Student student = studentDao.get(studentId);

    if (student == null) {
      System.out.println("Student id doesn't exist");
      return;
    }

    ScoreRecord scoreRecord = scoreRecordDao.getByStudentId(studentId);
    if (scoreRecord != null) {
      System.out.println("Student's score has been inputted before.\nPlease do an update to the existing record.");
      return;
    }

    System.out.print("Input science score: ");
    double scienceScore = scanner.nextDouble();

    System.out.print("Input social score: ");
    double socialScore = scanner.nextDouble();

    System.out.print("Input math score: ");
    double mathScore = scanner.nextDouble();

    System.out.print("Input linguistic score: ");
    double linguisticScore = scanner.nextDouble();

    System.out.print("Input religion score: ");
    double religionScore = scanner.nextDouble();

    System.out.print("Input ethic score: ");
    double ethicScore = scanner.nextDouble();

    scoreRecordDao.create(
        new ScoreRecord(
            student,
            scienceScore,
            socialScore,
            mathScore,
            religionScore,
            linguisticScore,
            ethicScore));

    System.out.println("Student's score has been recoreded successfully");
  }

  private static void menuUpdateStudentScore() {
    System.out.println("Menu: Update Student Score\n");
    System.out.print("Input student id: ");
    String studentId = scanner.nextLine();

    System.out.println();

    Student student = studentDao.get(studentId);

    if (student == null) {
      System.out.println("Student id doesn't exist");
      return;
    }

    ScoreRecord scoreRecord = scoreRecordDao.getByStudentId(studentId);
    if (scoreRecord == null) {
      System.out
          .println("Student's score record doesn't exist. Can't be updated.\nPlease input the student score first.");
      return;
    }

    System.out.println("Current score score:");
    scoreRecord.printDetails();

    System.out.println();

    System.out.print("Input science score [Input -1 to skip]: ");
    double scienceScore = scanner.nextDouble();
    if (scienceScore != -1) {
      scoreRecord.setScienceScore(scienceScore);
    }

    System.out.print("Input social score [Input -1 to skip]: ");
    double socialScore = scanner.nextDouble();
    if (socialScore != -1) {
      scoreRecord.setSocialScore(socialScore);
    }

    System.out.print("Input math score [Input -1 to skip]: ");
    double mathScore = scanner.nextDouble();
    if (mathScore != -1) {
      scoreRecord.setMathScore(mathScore);
    }

    System.out.print("Input linguistic score [Input -1 to skip]: ");
    double linguisticScore = scanner.nextDouble();
    if (linguisticScore != -1) {
      scoreRecord.setLinguisticScore(linguisticScore);
    }

    System.out.print("Input religion score [Input -1 to skip]: ");
    double religionScore = scanner.nextDouble();
    if (religionScore != -1) {
      scoreRecord.setReligionScore(religionScore);
    }

    System.out.print("Input ethic score [Input -1 to skip]: ");
    double ethicScore = scanner.nextDouble();

    if (ethicScore != -1) {
      scoreRecord.setEthicScore(ethicScore);
    }
  }

  private static void menuViewStudentScore() {
    System.out.println("STUDENT SCORE TABLE");
    String columns[] = { "Student name", "Science", "Social", "Math", "Religion", "Linguistic", "Ethic" };
    int size[] = { 30, 14, 14, 14, 14, 14, 14 };
    Table table = new Table(7, columns, size);

    table.printHeader();

    scoreRecordDao.getAll().forEach(score -> {
      String row[] = {
          score.getStudent().getName(),
          String.valueOf(score.getScienceScore()),
          String.valueOf(score.getSocialScore()),
          String.valueOf(score.getMathScore()),
          String.valueOf(score.getReligionScore()),
          String.valueOf(score.getLinguisticScore()),
          String.valueOf(score.getEthicScore()),
      };

      table.printRows(row);
    });
  }
}