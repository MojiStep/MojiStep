package com.mojistep.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
  Permission findByCode(String code);
}
