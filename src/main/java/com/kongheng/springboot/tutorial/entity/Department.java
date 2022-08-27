package com.kongheng.springboot.tutorial.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long departmentId;

  @NotBlank(message = "Please Add Department Name")
  private String departmentName;
  private String departmentAddress;
  private String departmentCode;
}
