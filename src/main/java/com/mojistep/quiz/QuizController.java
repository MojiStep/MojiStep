package com.mojistep.quiz;

import com.mojistep.user.User;
import com.mojistep.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/quiz")
@RequiredArgsConstructor
public class QuizController {
  private final QuizTestRepository testRepo;
  private final QuestionRepository questionRepo;
  private final QuizAttemptRepository attemptRepo;
  private final UserRepository userRepo;

  @GetMapping("/tests")
  public List<QuizTest> tests(){ return testRepo.findAll(); }

  @GetMapping("/tests/{testId}/questions")
  public List<Question> questions(@PathVariable Integer testId){
    return questionRepo.findByQuizTestIdOrderByDisplayOrderAsc(testId);
  }

  @PostMapping("/tests/{testId}/start")
  public ResponseEntity<QuizAttempt> start(@PathVariable Integer testId, @AuthenticationPrincipal UserDetails ud){
    User user = userRepo.findByEmail(ud.getUsername()).orElseThrow();
    int attemptNo = attemptRepo.countByUserIdAndQuizTestId(user.getId(), testId) + 1;
    QuizAttempt attempt = QuizAttempt.builder()
      .quizTest(testRepo.findById(testId).orElseThrow())
      .user(user).attemptNumber(attemptNo)
      .startTime(OffsetDateTime.now()).status("IN_PROGRESS").build();
    return ResponseEntity.ok(attemptRepo.save(attempt));
  }
}
