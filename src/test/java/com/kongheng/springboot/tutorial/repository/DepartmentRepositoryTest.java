package com.kongheng.springboot.tutorial.repository;

import com.kongheng.springboot.tutorial.entity.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class DepartmentRepositoryTest {

  @Autowired
  private DepartmentRepository departmentRepository;

  @Autowired
  private TestEntityManager entityManager;

  @BeforeEach
  void setUp() {
    Department department = Department.builder()
        .departmentName("Mechanical Engineering")
        .departmentCode("ME - 01")
        .departmentAddress("Phnom Penh")
        .build();
    entityManager.persist(department);
  }

  @Test
  @DisplayName("When find by id then return department")
  public void whenFindById_thenReturnDepartment() {
    Department department = departmentRepository.findById(1L).get();
    Assertions.assertEquals(department.getDepartmentName(), "Mechanical Engineering");
  }
}