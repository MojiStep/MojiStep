package com.mojistep.quiz;

import jakarta.persistence.*;
import lombok.*;
import com.mojistep.content.Category;
import com.mojistep.content.JlptLevel;
import java.time.OffsetDateTime;

@Entity @Table(name="quiz_tests")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class QuizTest {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  private String title;
  private String description;

  @ManyToOne @JoinColumn(name="category_id")
  private Category category;

  @ManyToOne @JoinColumn(name="jlpt_level_id")
  private JlptLevel jlptLevel;

  private String testType;
  private Integer totalQuestions;
  private Integer timeLimitMinutes;
  private Integer passingScore;
  private Boolean isPremium = false;
  private Boolean isPublished = false;
  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private OffsetDateTime deletedAt;
}
