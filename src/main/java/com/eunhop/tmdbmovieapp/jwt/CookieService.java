package com.eunhop.tmdbmovieapp.jwt;

import com.eunhop.tmdbmovieapp.domain.User;
import com.eunhop.tmdbmovieapp.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CookieService {
  private final JwtService jwtService;

  public void createCookieUsingAuthentication(HttpServletResponse response, Authentication authentication) {
    if (jwtService.dataAlreadyExist(authentication.getName())) {
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
    if (jwtService.dataAlreadyExist(user.getEmail())) {
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
    // sameSite 를 명시적으로 Lax 로 지정하기 위해 Cookie 에서 ResponseCookie 로 바꿨다.
    ResponseCookie cookie1 = ResponseCookie.from(JwtProperties.ACCESS_TOKEN.getDescription(), jwtAccessToken)
        .maxAge(JwtProperties.ACCESS_TOKEN.getTime()) // 쿠키의 만료시간 설정
        .secure(true)
        .httpOnly(true)
        .path("/")
        .sameSite("Lax")
        .build();
    ResponseCookie cookie2 = ResponseCookie.from(JwtProperties.REFRESH_TOKEN.getDescription(), jwtRefreshToken)
        .maxAge(JwtProperties.REFRESH_TOKEN.getTime()) // 쿠키의 만료시간 설정
        .secure(true)
        .httpOnly(true)
        .path("/")
        .sameSite("Lax")
        .build();
    response.addHeader("Set-Cookie", cookie1.toString());
    response.addHeader("Set-Cookie", cookie2.toString());
  }

  public void deleteCookie(HttpServletResponse response) {
    ResponseCookie cookie1 = ResponseCookie.from(JwtProperties.ACCESS_TOKEN.getDescription(), null)
        .maxAge(0)
        .path("/")
        .build(); // 모든 경로에서 삭제됐음을 알린다.
    ResponseCookie cookie2 = ResponseCookie.from(JwtProperties.REFRESH_TOKEN.getDescription(), null)
        .maxAge(0)
        .path("/")
        .build(); // 모든 경로에서 삭제됐음을 알린다.
    response.addHeader("Set-Cookie", cookie1.toString());
    response.addHeader("Set-Cookie", cookie2.toString());
  }
}
