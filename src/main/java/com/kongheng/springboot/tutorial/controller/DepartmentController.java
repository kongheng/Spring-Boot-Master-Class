package com.kongheng.springboot.tutorial.controller;

import com.kongheng.springboot.tutorial.entity.Department;
import com.kongheng.springboot.tutorial.error.DepartmentNotFoundException;
import com.kongheng.springboot.tutorial.service.DepartmentService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class DepartmentController {

  @Autowired
  private DepartmentService departmentService;

  @PostMapping("/departments")
  public Department saveDepartment(@Valid @RequestBody Department department) {
    return departmentService.saveDepartment(department);
  }

  @GetMapping("/departments")
  public List<Department> fetchDepartmentList() {
    return departmentService.fetchDepartmentList();
  }

  @GetMapping("/departments/{id}")
  public Department fetchDepartmentById(@PathVariable("id") Long departmentId)
      throws DepartmentNotFoundException {
    return departmentService.fetchDepartmentById(departmentId);
  }

  @DeleteMapping("/departments/{id}")
  public String deleteDepartmentById(@PathVariable("id") Long departmentId) {
    departmentService.deleteDepartmentById(departmentId);
    return "Department deleted successfully!";
  }

  @PutMapping("/departments/{id}")
  public Department updateDepartment(@PathVariable("id") Long departmentId,
      @RequestBody Department department) {
    return departmentService.updateDepartment(departmentId, department);
  }

  @GetMapping("/departments/name/{name}")
  public List<Department> fetchDepartmentByName(@PathVariable("name") String departmentName) {
    return departmentService.fetchDepartmentByDepartmentName(departmentName);
  }
}
