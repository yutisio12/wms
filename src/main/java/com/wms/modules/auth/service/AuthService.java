package com.wms.modules.auth.service;

import com.wms.modules.auth.domain.User;
// import com.wms.modules.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
  // private final UserRepository userRepo;

  public String login(String username, String password) {

    // User user = userRepo.findByUsername(username)
    // .orElseThrow(() -> new RuntimeException("User not found"));

    // if (!user.getPassword().equals(password)) {
    // throw new RuntimeException("Wrong password");
    // }

    // if (user.getTwoFaSecret() != null) {
    // return "2FA_REQUIRED";
    // }

    // return user.getId().toString();
    return null;
  }
}
