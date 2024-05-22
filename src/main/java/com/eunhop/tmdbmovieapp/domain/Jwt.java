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
@Table(name = "jwt")
@Entity
public class Jwt extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String accessToken;

  @NotNull
  private String refreshToken;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;
}
