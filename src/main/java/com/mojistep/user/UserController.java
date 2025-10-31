package com.mojistep.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
  private final UserRepository userRepo;

  @GetMapping("/me")
  public ResponseEntity<User> me(@AuthenticationPrincipal UserDetails ud){
    return userRepo.findByEmail(ud.getUsername()).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }
}
