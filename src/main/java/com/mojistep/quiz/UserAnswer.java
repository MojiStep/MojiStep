package com.mojistep.quiz;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name="user_answers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserAnswer {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne @JoinColumn(name="quiz_attempt_id")
  private QuizAttempt quizAttempt;

  @ManyToOne @JoinColumn(name="question_id")
  private Question question;

  private Integer selectedOptionId;
  @Column(columnDefinition="text")
  private String userAnswerText;
  private Boolean isCorrect;
  private Integer timeTakenSeconds;
}
