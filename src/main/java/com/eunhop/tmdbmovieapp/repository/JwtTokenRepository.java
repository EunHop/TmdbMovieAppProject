package com.eunhop.tmdbmovieapp.repository;

import com.eunhop.tmdbmovieapp.domain.Jwt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository<Jwt, Long> {
  Optional<Jwt> findByAccessToken(String accessToken);
  Optional<Jwt> findByRefreshToken(String refreshToken);
  Optional<Jwt> findByUserId(Long id);
}
