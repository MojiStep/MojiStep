package com.mojistep.content;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity @Table(name="lessons")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Lesson {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne @JoinColumn(name="category_id")
  private Category category;

  @ManyToOne @JoinColumn(name="jlpt_level_id")
  private JlptLevel jlptLevel;

  @Column(nullable=false)
  private String title;

  private String description;
  private Integer lessonNumber;
  private String contentType;
  private String contentUrl;
  private Integer durationMinutes;
  private String difficulty;
  private Boolean isPremium = false;
  private Boolean isPublished = false;
  private Integer displayOrder;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private OffsetDateTime deletedAt;
}
