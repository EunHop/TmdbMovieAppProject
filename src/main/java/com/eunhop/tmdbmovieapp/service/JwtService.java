package com.eunhop.tmdbmovieapp.service;

import com.eunhop.tmdbmovieapp.domain.Jwt;
import com.eunhop.tmdbmovieapp.domain.User;
import com.eunhop.tmdbmovieapp.repository.JwtTokenRepository;
import com.eunhop.tmdbmovieapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class JwtService {
  private final JwtTokenRepository jwtTokenRepository;
  private final UserRepository userRepository;

  public void newToken(String accessToken,String refreshToken, String email) {
    Jwt jwt = Jwt.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    Optional<User> user = userRepository.findByEmail(email);
    if(user.isPresent()) {
      jwt.setUser(user.get());
      jwtTokenRepository.save(jwt);
    }
  }

  public Optional<Jwt> findByAccessToken(String accessToken) {
    return jwtTokenRepository.findByAccessToken(accessToken);
  }

  public Optional<Jwt> findByRefreshToken(String refreshToken) {
    return jwtTokenRepository.findByRefreshToken(refreshToken);
  }

  public void deleteByRefreshToken(String refreshToken) {
    Optional<Jwt> jwt = jwtTokenRepository.findByRefreshToken(refreshToken);
    if(jwt.isPresent()) {
      jwtTokenRepository.delete(jwt.get());
    }
  }

  public void deleteByAccessToken(String accessToken) {
    Optional<Jwt> jwt = jwtTokenRepository.findByAccessToken(accessToken);
    if(jwt.isPresent()) {
      jwtTokenRepository.delete(jwt.get());
    }
  }

  public boolean dataAlreadyExist(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    if(user.isPresent()) {
      Optional<Jwt> jwtToken = jwtTokenRepository.findByUserId(user.get().getId());
      if(jwtToken.isPresent()) {
        jwtTokenRepository.delete(jwtToken.get());
        return true;
      }
      return false;
    }
    return false;
  }

  public boolean userIsPresent(String email) {
    Optional<User> user = userRepository.findByEmail(email);
    return user.isPresent();
  }

  public void updateNewAccessToken(String newAccessToken, String refreshToken) {
    Optional<Jwt> jwtToken = jwtTokenRepository.findByRefreshToken(refreshToken);
    if(jwtToken.isPresent()) {
      jwtToken.get().setAccessToken(newAccessToken);
      jwtTokenRepository.save(jwtToken.get());
    } else {
      throw new RuntimeException("업데이트 오류");
    }
  }
}
