package com.mojistep.vocab;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity @Table(name="vocabulary_translations")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VocabularyTranslation {
  @Id
  @GeneratedValue(strategy=GenerationType.UUID)
  private UUID id;

  @ManyToOne @JoinColumn(name="vocabulary_id")
  private Vocabulary vocabulary;

  private String lang;
  @Column(columnDefinition="text")
  private String meaning;
  @Column(columnDefinition="text")
  private String exampleSentence;
}
