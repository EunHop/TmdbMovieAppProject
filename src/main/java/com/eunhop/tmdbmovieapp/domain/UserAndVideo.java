package com.eunhop.tmdbmovieapp.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "user_and_video")
@Entity
public class UserAndVideo extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  private User user;

  @ManyToOne
  private Video video;

  @Column(length = 100)
  private String review;

  // review만 쓸경우 false, 관심목록 추가 버튼을 눌러야 true로 표시
  private boolean wish;
}
