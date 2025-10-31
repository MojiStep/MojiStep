package com.mojistep.content;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="jlpt_levels")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JlptLevel {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @Column(nullable=false, unique=true)
  private String levelCode;

  private String levelName;
  private String description;
  private Integer displayOrder;
}
