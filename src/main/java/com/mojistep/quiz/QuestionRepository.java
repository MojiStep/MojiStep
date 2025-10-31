package com.mojistep.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
  List<Question> findByQuizTestIdOrderByDisplayOrderAsc(Integer quizTestId);
}
