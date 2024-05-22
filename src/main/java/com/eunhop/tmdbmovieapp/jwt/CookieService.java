package com.eunhop.tmdbmovieapp.jwt;

import com.eunhop.tmdbmovieapp.domain.User;
import com.eunhop.tmdbmovieapp.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CookieService {
  private final JwtService jwtService;

  public void createCookieUsingAuthentication(HttpServletResponse response, Authentication authentication) {
    if(jwtService.dataAlreadyExist(authentication.getName())) {
      log.info("jwtToken already exist so I removed it");
    } else {
      log.info("jwtToken not exist so I created it");
    }
    String accessToken = JwtUtils.createAccessToken(authentication.getName());
    String refreshToken = JwtUtils.createRefreshToken(authentication.getName());
    jwtService.newToken(accessToken, refreshToken, authentication.getName());
    // 쿠키 생성
    createCookie(response, accessToken, refreshToken);
  }

  public void createCookieUsingUser(HttpServletResponse response, User user) {
    if(jwtService.dataAlreadyExist(user.getEmail())) {
      log.info("jwtToken already exist so I removed it");
    } else {
      log.info("jwtToken not exist so I created it");
    }
    String accessToken = JwtUtils.createAccessToken(user.getEmail());
    String refreshToken = JwtUtils.createRefreshToken(user.getEmail());
    jwtService.newToken(accessToken, refreshToken, user.getEmail());
    // 쿠키 생성
    createCookie(response, accessToken, refreshToken);
  }

  public void createCookie(HttpServletResponse response, String jwtAccessToken, String jwtRefreshToken) {
    Cookie cookie1 = new Cookie(JwtProperties.ACCESS_TOKEN.getDescription(), jwtAccessToken);
    cookie1.setMaxAge(JwtProperties.ACCESS_TOKEN.getTime()); // 쿠키의 만료시간 설정
    cookie1.setSecure(true);
    cookie1.setHttpOnly(true);
    cookie1.setPath("/");
    Cookie cookie2 = new Cookie(JwtProperties.REFRESH_TOKEN.getDescription(), jwtRefreshToken);
    cookie2.setMaxAge(JwtProperties.REFRESH_TOKEN.getTime()); // 쿠키의 만료시간 설정
    cookie2.setSecure(true);
    cookie2.setHttpOnly(true);
    cookie2.setPath("/");
    response.addCookie(cookie1);
    response.addCookie(cookie2);
  }

  public void deleteCookie(HttpServletResponse response) {
    Cookie cookie1 = new Cookie(JwtProperties.ACCESS_TOKEN.getDescription(), null);
    cookie1.setMaxAge(0);
    cookie1.setPath("/"); // 모든 경로에서 삭제됐음을 알린다.
    Cookie cookie2 = new Cookie(JwtProperties.REFRESH_TOKEN.getDescription(), null);
    cookie2.setMaxAge(0);
    cookie2.setPath("/");
    response.addCookie(cookie1);
    response.addCookie(cookie2);
  }
}
