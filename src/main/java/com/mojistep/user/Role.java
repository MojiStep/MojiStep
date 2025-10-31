package com.mojistep.user;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import java.util.*;

@Entity @Table(name="roles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(columnDefinition = "uuid")
  private UUID id;

  @Column(nullable=false, unique=true, length=50)
  private String code;

  @Column(nullable=false, length=100)
  private String name;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "role_permissions",
    joinColumns = @JoinColumn(name="role_id"),
    inverseJoinColumns = @JoinColumn(name="permission_id")
  )
  private Set<Permission> permissions = new HashSet<>();
}
