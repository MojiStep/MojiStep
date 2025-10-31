package com.mojistep.vocab;

import jakarta.persistence.*;
import lombok.*;
import com.mojistep.content.JlptLevel;
import java.time.OffsetDateTime;

@Entity @Table(name="vocabulary")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Vocabulary {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne @JoinColumn(name="jlpt_level_id")
  private JlptLevel jlptLevel;

  private String wordJapanese;
  private String wordHiragana;
  private String wordKatakana;
  private String wordRomaji;
  private String partOfSpeech;

  private OffsetDateTime createdAt;
  private OffsetDateTime updatedAt;
  private OffsetDateTime deletedAt;
}
