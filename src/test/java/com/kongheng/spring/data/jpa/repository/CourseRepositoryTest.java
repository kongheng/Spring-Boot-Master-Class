package com.kongheng.spring.data.jpa.repository;

import com.kongheng.spring.data.jpa.entity.Course;
import com.kongheng.spring.data.jpa.entity.Student;
import com.kongheng.spring.data.jpa.entity.Teacher;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@SpringBootTest
class CourseRepositoryTest {

  @Autowired
  private CourseRepository courseRepository;

  @Test
  public void printCourse() {
    System.out.println(courseRepository.findAll());
  }

  @Test
  public void saveCourseWithTeacher() {
    Teacher teacher = Teacher.builder()
        .firstName("sok")
        .lastName("dara")
        .build();
    Course course = Course.builder()
        .courseTitle("Python")
        .credit(6)
        .teacher(teacher)
        .build();
    courseRepository.save(course);
  }

  @Test
  public void findAllPagination() {
    Pageable firstPageWithThreeRecords = PageRequest.of(0, 3);
    Pageable secondPageWithTwoRecords = PageRequest.of(1, 2);
    List<Course> courses = courseRepository.findAll(secondPageWithTwoRecords).getContent();
    long totalElements = courseRepository.findAll(secondPageWithTwoRecords).getTotalElements();
    int totalPages = courseRepository.findAll(secondPageWithTwoRecords).getTotalPages();
    System.out.println("totalPages = " + totalPages);
    System.out.println("totalElements = " + totalElements);
    System.out.println("courses = " + courses);
  }

  @Test
  public void findAllSorting() {
    Pageable sortByTitle = PageRequest.of(0, 2, Sort.by("courseTitle"));
    Pageable sortByCreditDesc = PageRequest.of(0, 2, Sort.by("credit").descending());
    Pageable sortByTitleAndCreditDesc = PageRequest.of(0, 2,
        Sort.by("courseTitle").descending().and(Sort.by("credit")));
    List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
    System.out.println(courses);
  }

  @Test
  public void printFindByCourseTitleContaining() {
    Pageable firstPageTenRecords = PageRequest.of(0, 10);
    List<Course> courses = courseRepository.findByCourseTitleContaining("D", firstPageTenRecords)
        .getContent();
    System.out.println(courses);
  }

  @Test
  public void saveCourseWithStudentAndTeacher() {
    Teacher teacher = Teacher.builder()
        .lastName("chan")
        .firstName("dara")
        .build();
    Student student = Student.builder()
        .firstName("long")
        .lastName("kongheng")
        .emailId("kongheng.long123@gmail.com")
        .build();
    Course course = Course.builder()
        .courseTitle("AI")
        .credit(12)
        .teacher(teacher)
        .build();
    course.addStudent(student);
    courseRepository.save(course);
  }
}