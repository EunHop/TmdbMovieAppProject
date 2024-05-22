package com.eunhop.tmdbmovieapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "user")
@Entity
public class User extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // @Column(nullable = false)은  엔티티의 필드 값이 null로 채워진 상태에서도 정상적으로 수행되다가
  // 데이터베이스 쪽으로 SQL 쿼리가 도착한 순간에 예외가 발생하고
  // @NotNull은 엔티티의 필드 값이 null로 채워지는 순간 예외가 발생하므로
  // @NotNull이 더 전 단계에서 예외 발생 시키므로 훨씬 더 안전하다. 2023/09/28
  @NotNull
  @Column(length = 100)
  private String email;

  @NotNull
  @Column(length = 100)
  private String password;

  @NotNull
  @Column(length = 100)
  private String name;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Roles role;

  @NotNull
  private boolean enabled;

  @OneToMany
  @JoinColumn(name = "user_id")
  @ToString.Exclude
  // WARNING 에서 추천한대로 설정
  @Builder.Default
  private List<OAuth2> userByOAuth2 = new ArrayList<>();
}
