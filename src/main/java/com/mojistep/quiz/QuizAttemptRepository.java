package com.mojistep.quiz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Integer> {
  int countByUserIdAndQuizTestId(java.util.UUID userId, Integer quizTestId);
}
