package com.kongheng.springsecurityclient.service;

import com.kongheng.springsecurityclient.entity.PasswordResetToken;
import com.kongheng.springsecurityclient.entity.User;
import com.kongheng.springsecurityclient.entity.VerificationToken;
import com.kongheng.springsecurityclient.model.UserModel;
import com.kongheng.springsecurityclient.repository.PasswordResetTokenRepository;
import com.kongheng.springsecurityclient.repository.UserRepository;
import com.kongheng.springsecurityclient.repository.VerificationTokenRepository;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private VerificationTokenRepository verificationTokenRepository;

  @Autowired
  private PasswordResetTokenRepository passwordResetTokenRepository;

  @Override
  public User registerUser(UserModel userModel) {
    User user = User.builder()
        .email(userModel.getEmail())
        .firstName(userModel.getFirstName())
        .lastName(userModel.getLastName())
        .role("USER")
        .password(passwordEncoder.encode(userModel.getPassword()))
        .build();
    userRepository.save(user);
    return user;
  }

  @Override
  public void saveVerificationTokenForUser(String token, User user) {
    VerificationToken verificationToken = new VerificationToken(user, token);
    verificationTokenRepository.save(verificationToken);
  }

  @Override
  public String validateVerificationToken(String token) {
    VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
    if (verificationToken == null) {
      return "Invalid token";
    }
    User user = verificationToken.getUser();
    Calendar calendar = Calendar.getInstance();
    if (verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
      verificationTokenRepository.delete(verificationToken);
      return "Expired token";
    }
    user.setEnabled(true);
    userRepository.save(user);
    return "Valid";
  }

  @Override
  public VerificationToken generateNewVerificationToken(String oldToken) {
    VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
    verificationToken.setToken(UUID.randomUUID().toString());
    verificationTokenRepository.save(verificationToken);
    return verificationToken;
  }

  @Override
  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public void createPasswordResetTokenForUser(User user, String token) {
    PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
    passwordResetTokenRepository.save(passwordResetToken);
  }

  @Override
  public String validatePasswordResetToken(String token) {
    PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
    if (passwordResetToken == null) {
      return "Invalid";
    }
    Calendar calendar = Calendar.getInstance();
    if ((passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0) {
      passwordResetTokenRepository.delete(passwordResetToken);
      return "Expired";
    }
    return "Valid";
  }

  @Override
  public Optional<User> getUserByPasswordResetToken(String token) {
    return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
  }

  @Override
  public void changePassword(User user, String newPassword) {
    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
  }

  @Override
  public boolean checkIfValidOldPassword(User user, String oldPassword) {
    return passwordEncoder.matches(oldPassword, user.getPassword());
  }
}
