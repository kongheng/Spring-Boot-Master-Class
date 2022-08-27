package com.kongheng.springboot.tutorial.service;

import com.kongheng.springboot.tutorial.entity.Department;
import com.kongheng.springboot.tutorial.error.DepartmentNotFoundException;
import com.kongheng.springboot.tutorial.repository.DepartmentRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

  @Autowired
  private DepartmentRepository departmentRepository;

  @Override
  public Department saveDepartment(Department department) {
    return departmentRepository.save(department);
  }

  @Override
  public List<Department> fetchDepartmentList() {
    return departmentRepository.findAll();
  }

  @Override
  public Department fetchDepartmentById(Long departmentId) throws DepartmentNotFoundException {
    Optional<Department> department = departmentRepository.findById(departmentId);
    if (department.isEmpty()) {
      throw new DepartmentNotFoundException("No Department Available!");
    }
    return department.get();
  }

  @Override
  public void deleteDepartmentById(Long departmentId) {
    departmentRepository.deleteById(departmentId);
  }

  @Override
  public Department updateDepartment(Long departmentId, Department department) {
    Department depDB = departmentRepository.findById(departmentId).get();
    if (Objects.nonNull(department.getDepartmentName()) && !""
        .equalsIgnoreCase(department.getDepartmentName())) {
      depDB.setDepartmentName(department.getDepartmentName());
    }
    if (Objects.nonNull(department.getDepartmentCode()) && !""
        .equalsIgnoreCase(department.getDepartmentCode())) {
      depDB.setDepartmentCode(department.getDepartmentCode());
    }
    if (Objects.nonNull(department.getDepartmentAddress()) && !""
        .equalsIgnoreCase(department.getDepartmentAddress())) {
      depDB.setDepartmentAddress(department.getDepartmentAddress());
    }
    return departmentRepository.save(depDB);
  }

  @Override
  public List<Department> fetchDepartmentByDepartmentName(String departmentName) {
    return departmentRepository.findByDepartmentNameIgnoreCase(departmentName);
  }
}
