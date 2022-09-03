package com.kongheng.springsecurityclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String matchingPassword;
}
