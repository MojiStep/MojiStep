package com.mojistep.auth;

import com.mojistep.security.JwtService;
import com.mojistep.user.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
  private final AuthenticationManager authManager;
  private final JwtService jwtService;
  private final UserRepository userRepo;
  private final RoleRepository roleRepo;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req){
    if (userRepo.findByEmail(req.getEmail()).isPresent()) return ResponseEntity.badRequest().body(Map.of("error","email-exists"));
    if (userRepo.findByUsername(req.getUsername()).isPresent()) return ResponseEntity.badRequest().body(Map.of("error","username-exists"));

    Role userRole = roleRepo.findByCode("ROLE_USER");
    User user = User.builder()
      .email(req.getEmail())
      .username(req.getUsername())
      .fullName(req.getFullName())
      .passwordHash(passwordEncoder.encode(req.getPassword()))
      .build();
    if(userRole != null) user.getRoles().add(userRole);
    userRepo.save(user);

    String access = jwtService.generateToken(user.getEmail(), Map.of("uid", user.getId().toString()), false);
    String refresh = jwtService.generateToken(user.getEmail(), Map.of("uid", user.getId().toString(), "typ","refresh"), true);
    return ResponseEntity.ok(new TokenResponse(access, refresh));
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest req){
    authManager.authenticate(new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword()));
    var user = userRepo.findByEmail(req.getUsername()).orElseGet(() -> userRepo.findByUsername(req.getUsername()).orElseThrow());
    String access = jwtService.generateToken(user.getEmail(), Map.of("uid", user.getId().toString()), false);
    String refresh = jwtService.generateToken(user.getEmail(), Map.of("uid", user.getId().toString(), "typ","refresh"), true);
    return ResponseEntity.ok(new TokenResponse(access, refresh));
  }

  @PostMapping("/refresh")
  public ResponseEntity<?> refresh(@RequestHeader("Authorization") String authHeader){
    String token = authHeader.replace("Bearer ", "");
    String username = jwtService.extractUsername(token);
    String newAccess = jwtService.generateToken(username, Map.of(), false);
    return ResponseEntity.ok(new TokenResponse(newAccess, token));
  }
}
