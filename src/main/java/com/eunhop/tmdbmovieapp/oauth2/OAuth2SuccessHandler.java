package com.eunhop.tmdbmovieapp.oauth2;

import com.eunhop.tmdbmovieapp.domain.Roles;
import com.eunhop.tmdbmovieapp.jwt.CookieService;
import com.eunhop.tmdbmovieapp.service.CustomUserDetailsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class OAuth2SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
  private final CookieService cookieService;
  private final CustomUserDetailsService customUserDetailsService;

  /**
   * 인증에 성공했을 때 사용
   * JWT accessToken과 refreshToken을 생성해서 쿠키에 넣는다.
   */
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
    cookieService.createCookieUsingAuthentication(response, authentication);
    UserDetails userDetails = customUserDetailsService.loadUserByUsername(authentication.getName());

    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        userDetails,
        authentication.getCredentials(),
        List.of(new SimpleGrantedAuthority(Roles.USER.getValue()))
    );
    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    response.sendRedirect("/");
  }
}
