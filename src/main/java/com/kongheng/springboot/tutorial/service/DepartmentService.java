package com.kongheng.springboot.tutorial.service;

import com.kongheng.springboot.tutorial.entity.Department;
import com.kongheng.springboot.tutorial.error.DepartmentNotFoundException;
import java.util.List;

public interface DepartmentService {

  Department saveDepartment(Department department);

  List<Department> fetchDepartmentList();

  Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException;

  void deleteDepartmentById(Long departmentId);

  Department updateDepartment(Long departmentId, Department department);

  List<Department> fetchDepartmentByDepartmentName(String departmentName);
}
