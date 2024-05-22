package com.eunhop.tmdbmovieapp.jwt;

import com.eunhop.tmdbmovieapp.domain.Jwt;
import com.eunhop.tmdbmovieapp.service.CustomUserDetailsService;
import com.eunhop.tmdbmovieapp.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * JWT를 이용한 Authorization(헤더)
 */
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

  private final CustomUserDetailsService customUserDetailsService;
  private final CookieService cookieService;
  private final JwtService jwtService;


  public JwtAuthorizationFilter(CustomUserDetailsService customUserDetailsService, CookieService cookieService, JwtService jwtService) {
    this.customUserDetailsService = customUserDetailsService;
    this.cookieService = cookieService;
    this.jwtService = jwtService;
  }

  protected void doFilterInternal(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain
  ) throws IOException, ServletException {
      // cookie 에서 JWT token을 가져옵니다.
    String accessToken = null;
    String refreshToken = null;
    try {
      accessToken = Arrays.stream(request.getCookies())
          .filter(cookie -> cookie.getName().equals(JwtProperties.ACCESS_TOKEN.getDescription())).findFirst()
          .map(Cookie::getValue)
          .orElse(null);
      refreshToken = Arrays.stream(request.getCookies())
          .filter(cookie -> cookie.getName().equals(JwtProperties.REFRESH_TOKEN.getDescription())).findFirst()
          .map(Cookie::getValue)
          .orElse(null);
    } catch (Exception ignored) {}
    if (accessToken != null & refreshToken != null) {
      Optional<Jwt> access =jwtService.findByAccessToken(accessToken);
      if(access.isPresent()) {
        if (refreshToken.equals(access.get().getRefreshToken())) {
          Authentication authentication = getUsernamePasswordAuthenticationToken(accessToken);
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // DB에 accessToken 이 같은게 없으므로 쿠키를 삭제시키고 재로그인 시킨다.
      } else {
        cookieService.deleteCookie(response);
      }
    } else if (accessToken == null & refreshToken != null) {
      Optional<Jwt> refresh = jwtService.findByRefreshToken(refreshToken);
      if(refresh.isPresent()) {
        if (JwtUtils.accessTokenNotExpired(refresh.get().getAccessToken())) {
          jwtService.deleteByRefreshToken(refreshToken);
          cookieService.deleteCookie(response);
        }
        else {
          String newAccessToken = JwtUtils.createAccessToken(refresh.get().getUser().getEmail());
          jwtService.updateNewAccessToken(newAccessToken, refreshToken);
          Cookie cookie1 = new Cookie(JwtProperties.ACCESS_TOKEN.getDescription(), newAccessToken);
          cookie1.setMaxAge(JwtProperties.ACCESS_TOKEN.getTime()); // 쿠키의 만료시간 설정
          cookie1.setSecure(true);
          cookie1.setHttpOnly(true);
          cookie1.setPath("/");
          response.addCookie(cookie1);
          Authentication authentication = getUsernamePasswordAuthenticationToken(newAccessToken);
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
      // DB에 refreshToken 이 같은게 없으므로 쿠키를 삭제시키고 재로그인 시킨다.
      else {
        cookieService.deleteCookie(response);
      }
    }
    // accessToken != null & refreshToken == null
    // RefreshToken 이 없으므로 탈취된 것으로 간주, 쿠키를 삭제시키고 재로그인 시킨다.
    else if (accessToken != null) {
      jwtService.deleteByAccessToken(accessToken);
      cookieService.deleteCookie(response);
    }
    chain.doFilter(request, response);
  }

  /**
   * JWT 토큰으로 User를 찾아서 UsernamePasswordAuthenticationToken를 만들어서 반환한다.
   */
  private Authentication getUsernamePasswordAuthenticationToken(String accessToken) {
    String email = JwtUtils.getUserEmail(accessToken);
      UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        return new UsernamePasswordAuthenticationToken(
            userDetails, // principal
            userDetails.getPassword(),
            userDetails.getAuthorities()
        );
  }
}