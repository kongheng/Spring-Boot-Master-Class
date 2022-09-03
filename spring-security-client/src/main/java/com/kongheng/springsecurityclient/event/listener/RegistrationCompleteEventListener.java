package com.kongheng.springsecurityclient.event.listener;

import com.kongheng.springsecurityclient.entity.User;
import com.kongheng.springsecurityclient.event.RegistrationCompleteEvent;
import com.kongheng.springsecurityclient.service.UserService;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements
    ApplicationListener<RegistrationCompleteEvent> {

  @Autowired
  private UserService userService;

  @Override
  public void onApplicationEvent(RegistrationCompleteEvent event) {
    //Create the verification token for the user with link
    User user = event.getUser();
    String token = UUID.randomUUID().toString();
    userService.saveVerificationTokenForUser(token, user);
    //Send mail to user
    String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;
    //Send verification email
    log.info("Click the link to verify your account: {}", url);
  }
}
