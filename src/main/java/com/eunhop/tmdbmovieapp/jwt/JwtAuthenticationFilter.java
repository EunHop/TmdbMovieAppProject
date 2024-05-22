package com.eunhop.tmdbmovieapp.jwt;

import com.eunhop.tmdbmovieapp.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * JWT를 이용한 로그인 인증
 */
@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private final CookieService cookieService;
  private final CustomUserDetailsService customUserDetailsService;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager, CookieService cookieService, CustomUserDetailsService customUserDetailsService) {
    super(authenticationManager);
    this.authenticationManager = authenticationManager;
    this.cookieService = cookieService;
    this.customUserDetailsService = customUserDetailsService;
  }

  /**
   * 로그인 인증 시도
   */
  public Authentication attemptAuthentication(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws AuthenticationException {
    // 로그인할 때 입력한 username과 password를 가지고 authenticationToken를 생성한다.
    UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getParameter("email"));
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
          userDetails,
          request.getParameter("password"),
          userDetails.getAuthorities()
      );
      return authenticationManager.authenticate(authenticationToken);
  }


  /**
   * 인증에 성공했을 때 사용
   * JWT accessToken과 refreshToken을 생성해서 쿠키에 넣는다.
   */
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult
  ) throws IOException {
    cookieService.createCookieUsingAuthentication(response, authResult);

    response.sendRedirect("/");
  }

  protected void unsuccessfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException exception
  ) throws IOException {
    String errorMessage;
    if(exception instanceof BadCredentialsException) {
      errorMessage = "아이디 또는 비밀번호를 확인해주세요.";
    }
    else if(exception instanceof InternalAuthenticationServiceException) {
      errorMessage = "내부적으로 발생한 시스템 문제로 인해 요청을 처리할 수 없습니다. 관리자에게 문의해주세요.";
    }
    else if(exception instanceof UsernameNotFoundException) {
      errorMessage = "아이디 또는 비밀번호를 확인해주세요.";
    }
    else if(exception instanceof AuthenticationCredentialsNotFoundException) {
      errorMessage = "인증 요청이 거부 되었습니다. 관리자에게 문의해주세요.";
    } else {
      errorMessage = "알 수 없는 이유로 로그인에 실패했습니다. 관리자에게 문의해주세요.";
    }
    errorMessage = URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
    response.sendRedirect("/login?exception="+errorMessage);
  }

}