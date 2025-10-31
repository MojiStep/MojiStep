package com.mojistep.quiz;

import jakarta.persistence.*;
import lombok.*;
import com.mojistep.user.User;
import java.time.OffsetDateTime;

@Entity @Table(name="quiz_attempts")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class QuizAttempt {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne @JoinColumn(name="user_id")
  private User user;

  @ManyToOne @JoinColumn(name="quiz_test_id")
  private QuizTest quizTest;

  private Integer attemptNumber;
  private OffsetDateTime startTime;
  private OffsetDateTime endTime;
  private Integer score;
  private Integer totalQuestions;
  private Integer correctAnswers;
  private Integer wrongAnswers;
  private String status;
  private Integer timeTakenSeconds;
}
