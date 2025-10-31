package com.mojistep.user;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity @Table(name="user_profiles")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserProfile {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(optional=false)
  @JoinColumn(name="user_id")
  private User user;

  private Integer currentLevelId;
  private Integer targetLevelId;
  private Integer dailyGoal;
  private Integer studyStreak;
  private Integer totalPoints;
  private Integer totalStudyTime;
  private String preferredLanguage;

  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private OffsetDateTime deletedAt;

  @PrePersist void onCreate(){ createdAt = OffsetDateTime.now(); }
  @PreUpdate void onUpdate(){ updatedAt = OffsetDateTime.now(); }
}
