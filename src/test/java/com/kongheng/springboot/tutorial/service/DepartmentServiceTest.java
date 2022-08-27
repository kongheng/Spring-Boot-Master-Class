package com.kongheng.springboot.tutorial.service;

import com.kongheng.springboot.tutorial.entity.Department;
import com.kongheng.springboot.tutorial.repository.DepartmentRepository;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class DepartmentServiceTest {

  @Autowired
  private DepartmentService departmentService;

  @MockBean
  private DepartmentRepository departmentRepository;

  @BeforeEach
  void setUp() {
    Department department = Department.builder()
        .departmentName("IT")
        .departmentAddress("Phnom Penh")
        .departmentCode("IT-07")
        .departmentId(1L)
        .build();
    Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT"))
        .thenReturn(List.of(department));
  }

  @Test
  @DisplayName("Get data based on valid Department Name")
  public void whenValidDepartmentName_thenDepartmentShouldFound() {
    String departmentName = "IT";
    List<Department> departments = departmentService
        .fetchDepartmentByDepartmentName(departmentName);
    departments.forEach(
        department -> Assertions.assertEquals(departmentName, department.getDepartmentName()));
  }
}