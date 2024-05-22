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
@Table(name = "oauth2")
@Entity
public class OAuth2 extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(length = 100)
  private String email;

  @NotNull
  @Column(length = 100)
  private String name;

  @NotNull
  @Column(length = 100)
  private String provider;

  @ManyToOne
  private User user;
}
