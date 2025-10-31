package com.mojistep.content;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity @Table(name="categories")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Category {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable=false, unique=true, length=100)
  private String name;

  @Column(nullable=false, unique=true, length=100)
  private String slug;

  private String icon;
  private String description;
  private Integer displayOrder;
  private Boolean isActive = true;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private OffsetDateTime deletedAt;
}
