package model;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Student extends Model {
  private String name;
  private boolean gender; // 1 -> male, 0 -> female
  private String address;
  private String father;
  private String mother;
  private String contactPhone;
  private Calendar birthDate;

  // Constructor that will be used when we just want to initialize an empty Student (which probably we don't use it)
  public Student() {
    this.id = String.valueOf(new Date().getTime());
    this.name = "";
    this.gender = true;
    this.address = this.father = this.mother = this.contactPhone = "";
    this.birthDate = Calendar.getInstance();
  }

  // Constructor that will be used when creating a new Student data.
  public Student(String name, boolean gender, String address, String father, String mother, String contactPhone, Calendar birthDate) {
    this.id = String.valueOf(new Date().getTime());
    this.name = name;
    this.gender = gender;
    this.address = address;
    this.father = father;
    this.mother = mother;
    this.contactPhone = contactPhone;
    this.birthDate = birthDate;
  }

  // Constructor that will be used when we load Student data from file
  public Student(String id, String name, boolean gender, String address, String father, String mother, String contactPhone, Calendar birthDate) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.address = address;
    this.father = father;
    this.mother = mother;
    this.contactPhone = contactPhone;
    this.birthDate = birthDate;
  }

  @Override
  public String toLine() {
    String line = String.format("%s;%s;%d;%s;%s;%s;%s;%d;%d;%d", 
      id,
      name,
      gender ? 1 : 0,
      address,
      father,
      mother,
      contactPhone,
      birthDate.get(Calendar.DATE),
      birthDate.get(Calendar.MONTH),
      birthDate.get(Calendar.YEAR)
    );

    return line;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isGender() {
    return gender;
  }

  public void setGender(boolean gender) {
    this.gender = gender;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getFather() {
    return father;
  }

  public void setFather(String father) {
    this.father = father;
  }

  public String getMother() {
    return mother;
  }

  public void setMother(String mother) {
    this.mother = mother;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }

  public Calendar getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Calendar birthDate) {
    this.birthDate = birthDate;
  }

  public void printDetails() {
    System.out.printf("ID\t\t\t\t: %s\n", this.id);
    System.out.printf("Name\t\t\t: %s\n", this.name);
    System.out.printf("Birth date\t\t: %s\n",
        new SimpleDateFormat("dd-MM-yyyy").format((this.birthDate.getTime())));
    System.out.printf("Gender\t\t\t: %s\n", this.gender ? "Male" : "Female");
    System.out.printf("Father name\t\t: %s\n", this.father);
    System.out.printf("Mother name\t\t: %s\n", this.mother);
    System.out.printf("Contact phone\t: %s\n", this.contactPhone);
    System.out.printf("Address\t\t\t: %s\n", this.address);
  }
}
