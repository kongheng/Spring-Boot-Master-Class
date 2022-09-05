package com.kongheng.spring.data.jpa.repository;

import com.kongheng.spring.data.jpa.entity.Course;
import com.kongheng.spring.data.jpa.entity.Teacher;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TeacherRepositoryTest {

  @Autowired
  private TeacherRepository teacherRepository;

  @Test
  public void saveTeacher() {
    Course courseDBA = Course.builder()
        .courseTitle("DBA")
        .credit(5)
        .build();
    Course courseJava = Course.builder()
        .courseTitle("Java")
        .credit(6)
        .build();
    Teacher teacher = Teacher.builder()
        .firstName("Van")
        .lastName("Thida")
//        .courses(List.of(courseDBA, courseJava))
        .build();
    teacherRepository.save(teacher);
  }
}