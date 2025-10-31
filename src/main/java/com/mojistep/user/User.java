package com.mojistep.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.time.OffsetDateTime;
import java.util.*;

@Entity @Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class User {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(columnDefinition = "uuid")
  private UUID id;

  @Column(nullable=false, unique=true)
  private String email;

  @Column(nullable=false, unique=true)
  private String username;

  @Column(name="password_hash", nullable=false)
  private String passwordHash;

  private String fullName;

  private Boolean isActive = true;

  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private OffsetDateTime lastLogin;
  private OffsetDateTime deletedAt;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "user_roles",
    joinColumns = @JoinColumn(name="user_id"),
    inverseJoinColumns = @JoinColumn(name="role_id")
  )
  private Set<Role> roles = new HashSet<>();

  @PrePersist
  void onCreate() { createdAt = OffsetDateTime.now(); isActive = true; }

  @PreUpdate
  void onUpdate() { updatedAt = OffsetDateTime.now(); }
}
