package com.kongheng.spring.data.jpa.repository;

import com.kongheng.spring.data.jpa.entity.Course;
import com.kongheng.spring.data.jpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseMaterialRepositoryTest {

  @Autowired
  private CourseMaterialRepository repository;

  @Test
  public void saveCourseMaterial() {
    Course course = Course.builder()
        .courseTitle("DSA")
        .credit(6)
        .build();
    CourseMaterial courseMaterial = CourseMaterial.builder()
        .url("www.google.com")
        .course(course)
        .build();
    repository.save(courseMaterial);
  }

  @Test
  public void printAllCourseMaterial() {
    System.out.println(repository.findAll());
  }
}