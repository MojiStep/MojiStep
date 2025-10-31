package com.mojistep.security;

import com.mojistep.user.User;
import com.mojistep.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmail(username)
        .or(() -> userRepository.findByUsername(username))
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    Set<GrantedAuthority> authorities = user.getRoles().stream()
      .flatMap(role -> role.getPermissions().stream())
      .map(p -> new SimpleGrantedAuthority(p.getCode()))
      .collect(Collectors.toSet());

    return org.springframework.security.core.userdetails.User.withUsername(user.getEmail())
      .password(user.getPasswordHash())
      .authorities(authorities)
      .accountLocked(Boolean.FALSE.equals(user.getIsActive()))
      .build();
  }
}
