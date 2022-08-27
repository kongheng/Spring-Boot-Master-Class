package com.kongheng.springboot.tutorial.controller;

import com.kongheng.springboot.tutorial.entity.Department;
import com.kongheng.springboot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DepartmentService departmentService;

  private Department department;

  @BeforeEach
  void setUp() {
    department = Department.builder()
        .departmentName("IT")
        .departmentAddress("Kompong Cham")
        .departmentCode("IT-09")
        .departmentId(1L)
        .build();
  }

  @Test
  void saveDepartment() throws Exception {
    Department inputDepartment = Department.builder()
        .departmentName("IT")
        .departmentAddress("Kompong Cham")
        .departmentCode("IT-09")
        .build();
    Mockito.when(departmentService.saveDepartment(inputDepartment))
        .thenReturn(department);
    mockMvc.perform(MockMvcRequestBuilders.post("/departments")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n"
            + "    \"departmentName\": \"IT\",\n"
            + "    \"departmentAddress\": \"Kampong Cham\",\n"
            + "    \"departmentCode\": \"IT-09\"\n"
            + "}"))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void fetchDepartmentById() throws Exception {
    Mockito.when(departmentService.fetchDepartmentById(1L))
        .thenReturn(department);
    mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.departmentName")
            .value(department.getDepartmentName()));
  }
}