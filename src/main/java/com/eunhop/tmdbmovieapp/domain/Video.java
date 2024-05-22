package com.eunhop.tmdbmovieapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "video")
@Entity
public class Video extends BaseEntity {
  // TMDB API의 movie나 TV의 id 그대로 기본키 지정
  @Id
  private int id;

  @NotNull
  @Column(length = 100)
  private String title;

  private String tagline;

  @Column(length = 500)
  private String poster_path;

  @NotNull
  private String media_type;

  @Column(length = 100)
  private String release_date;

  private int score;
}
