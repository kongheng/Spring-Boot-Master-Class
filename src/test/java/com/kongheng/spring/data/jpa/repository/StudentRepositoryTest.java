package com.kongheng.spring.data.jpa.repository;

import com.kongheng.spring.data.jpa.entity.Guardian;
import com.kongheng.spring.data.jpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentRepositoryTest {

  @Autowired
  private StudentRepository studentRepository;

  @Test
  public void saveStudent() {
    Student student = Student.builder().emailId("kongheng.long@gmail.com").firstName("long")
        .lastName("kongheng").build();
    studentRepository.save(student);
  }

  @Test
  public void saveStudentWithGuardian() {
    Guardian guardian = Guardian.builder().name("jonh").mobile("1234567").email("wick@gmail.com")
        .build();
    Student student = Student.builder().firstName("jonh").lastName("wick")
        .emailId("jonh.wick@gmail.com").guardian(guardian).build();
    studentRepository.save(student);
  }

  @Test
  public void printStudentByFirstName() {
    System.out.println(studentRepository.findByFirstName("Long"));
  }

  @Test
  public void printStudentByFirstNameContaining() {
    System.out.println(studentRepository.findByFirstNameContaining("o"));
  }

  @Test
  public void printStudentBasedOnGuardianName() {
    System.out.println(studentRepository.findByGuardianName("jonh"));
  }

  @Test
  public void printAllStudent() {
    System.out.println(studentRepository.findAll());
  }

  @Test
  public void printStudentByEmailAddress() {
    System.out.println(studentRepository.getStudentByEmailAddress("kongheng.long@gmail.com"));
  }

  @Test
  public void printStudentFirstNameByEmailAddress() {
    System.out.println(
        studentRepository.getStudentFirstNameByEmailAddress("kongheng.long@gmail.com"));
  }

  @Test
  public void printStudentByEmailAddressNative() {
    System.out.println(studentRepository
        .getStudentByEmailAddressNative("kongheng.long@gmail.com"));
  }

  @Test
  public void printGetStudentByEmailAddressNativeNamedParam() {
    System.out.println(
        studentRepository.getStudentByEmailAddressNativeNamedParam("kongheng.long@gmail.com"));
  }

  @Test
  public void updateStudentFirstNameByEmailId() {
    studentRepository.updateStudentFirstNameByEmailId(
        "welcome",
        "kongheng.long@gmail.com");
  }
}