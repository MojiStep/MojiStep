package com.mojistep.quiz;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="question_options")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class QuestionOption {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne @JoinColumn(name="question_id")
  private Question question;

  @Column(columnDefinition="text")
  private String optionText;

  private Boolean isCorrect = false;
  private Integer displayOrder;
}
