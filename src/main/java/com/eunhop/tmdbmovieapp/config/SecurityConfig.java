package com.eunhop.tmdbmovieapp.config;

import com.eunhop.tmdbmovieapp.domain.Roles;
import com.eunhop.tmdbmovieapp.jwt.CookieService;
import com.eunhop.tmdbmovieapp.jwt.JwtAuthenticationFilter;
import com.eunhop.tmdbmovieapp.jwt.JwtAuthorizationFilter;
import com.eunhop.tmdbmovieapp.jwt.JwtProperties;
import com.eunhop.tmdbmovieapp.oauth2.CustomOAuth2UserService;
import com.eunhop.tmdbmovieapp.oauth2.GoogleOAuth2UserService;
import com.eunhop.tmdbmovieapp.oauth2.OAuth2ClientRegistration;
import com.eunhop.tmdbmovieapp.oauth2.OAuth2SuccessHandler;
import com.eunhop.tmdbmovieapp.service.CustomUserDetailsService;
import com.eunhop.tmdbmovieapp.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final JwtService jwtService;
  private final CookieService cookieService;
  private final OAuth2ClientRegistration oAuth2ClientRegistration;
  private final CustomUserDetailsService customUserDetailsService;


  @Bean
  public SecurityFilterChain filterChain(
      HttpSecurity http,
      CustomOAuth2UserService customOAuth2UserService,
      GoogleOAuth2UserService googleOAuth2UserService
  ) throws Exception {
    http.
        // basic authentication
            httpBasic(AbstractHttpConfigurer::disable) // basic authentication filter 비활성화
        // csrf
        .csrf(AbstractHttpConfigurer::disable)
        // remember-me
        .rememberMe(AbstractHttpConfigurer::disable)
        // stateless
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        // jwt filter
        .apply(new MyCustomDsl());
    // authorization
    http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
            // 정적 리소스들을 spring security 대상에서 제외
            .requestMatchers("/images/**", "/css/**", "/js/**").permitAll()
            .requestMatchers("/", "/login", "/signup").permitAll()
            .requestMatchers(HttpMethod.GET, "/details/**", "/notice/**", "/search/**").permitAll()
            .requestMatchers(HttpMethod.POST, "/details/**").authenticated()
            .requestMatchers("/my_wishlist/**").authenticated()
            .requestMatchers("/notice/disabled").hasRole(Roles.ADMIN.name())
            .requestMatchers(HttpMethod.POST, "/notice").hasRole(Roles.ADMIN.name())
            .requestMatchers("/user/management/**").hasRole(Roles.ADMIN.name())
            // 허용한 범위 외의 요청 다 막기
            .anyRequest().hasRole(Roles.ADMIN.name())
        )
        /*
      WARNING 문제 You are asking Spring Security to ignore org.springframework.boot.autoconfigure.security.servlet.StaticResourceRequest
      처음엔 이 코드로 작성해서 필터를 거치지않고 정적 리소스들을 spring security 대상에서 제외시켰는데 서버 실행시 warning 이 발생하였다.
      permitAll() 을 더 권장했다.
      @Bean
      public WebSecurityCustomizer configure() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
      }
      */
        // login
        .formLogin(login -> login
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .permitAll() //모두 허용
        )
        .oauth2Login(oauth2 -> oauth2
            .loginPage("/login")
            .defaultSuccessUrl("/")
            .successHandler(new OAuth2SuccessHandler(cookieService, customUserDetailsService))
            .clientRegistrationRepository(clientRegistrationRepository())
            .authorizedClientService(authorizedClientService())
            .userInfoEndpoint(user -> user
                .oidcUserService(googleOAuth2UserService)  //google 인증, OpenID connect 1.0
                .userService(customOAuth2UserService)  //  kakao, naver 인증, OAuth2 통신
            )
            .permitAll()
        )
        // logout
        .logout(logout -> logout
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true)
            .deleteCookies(JwtProperties.ACCESS_TOKEN.getDescription())
            .deleteCookies(JwtProperties.REFRESH_TOKEN.getDescription())
        )
    ;
    return http.build();
  }

  @Bean
  public OAuth2AuthorizedClientService authorizedClientService() {
    return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
  }

  @Bean
  public ClientRegistrationRepository clientRegistrationRepository() {
    List<ClientRegistration> clientRegistrations = Arrays.asList(
        oAuth2ClientRegistration.googleClientRegistration(),
        oAuth2ClientRegistration.kakaoClientRegistration()
    );
    return new InMemoryClientRegistrationRepository(clientRegistrations);
  }

  public class MyCustomDsl extends AbstractHttpConfigurer<MyCustomDsl, HttpSecurity> {
    @Override
    public void configure(HttpSecurity http) {
      AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
      http
          .addFilterBefore(new JwtAuthenticationFilter(authenticationManager, cookieService, customUserDetailsService), UsernamePasswordAuthenticationFilter.class)
          .addFilterBefore(new JwtAuthorizationFilter(customUserDetailsService, cookieService, jwtService), BasicAuthenticationFilter.class);
    }
  }
}


