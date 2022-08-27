package com.kongheng.springboot.tutorial.repository;

import com.kongheng.springboot.tutorial.entity.Department;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

  List<Department> findByDepartmentName(String departmentName);
  List<Department> findByDepartmentNameIgnoreCase(String departmentName);
}
