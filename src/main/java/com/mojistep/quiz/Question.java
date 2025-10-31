package com.mojistep.quiz;

import jakarta.persistence.*;
import lombok.*;
import com.mojistep.content.Category;
import com.mojistep.content.JlptLevel;
import java.util.List;

@Entity @Table(name="questions")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Question {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne @JoinColumn(name="quiz_test_id")
  private QuizTest quizTest;

  @ManyToOne @JoinColumn(name="category_id")
  private Category category;

  @ManyToOne @JoinColumn(name="jlpt_level_id")
  private JlptLevel jlptLevel;

  @Column(columnDefinition="text")
  private String questionText;

  private String questionType;
  @Column(columnDefinition="text")
  private String explanation;
  private String difficulty;
  private Integer points;
  private Integer displayOrder;
}
