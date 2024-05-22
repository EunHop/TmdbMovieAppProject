package com.eunhop.tmdbmovieapp.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;

@RequiredArgsConstructor
@Configuration
public class OAuth2ClientRegistration {

  private final Environment environment;
  private final String registration = "spring.security.oauth2.client.registration.";

  public ClientRegistration googleClientRegistration() {
    final String clientId = environment.getProperty(registration + "google.client-id");
    final String clientSecret = environment.getProperty(registration + "google.client-secret");

    return CommonOAuth2Provider
        .GOOGLE
        .getBuilder("google")
        .clientId(clientId)
        .clientSecret(clientSecret)
        .build();
  }
  public ClientRegistration kakaoClientRegistration() {
    final String clientId = environment.getProperty(registration + "kakao.client-id");
    final String clientSecret = environment.getProperty(registration + "kakao.client-secret");

    return CustomOAuth2Provider
        .KAKAO
        .getBuilder("kakao")
        .clientId(clientId)
        .clientSecret(clientSecret)
        .build();
  }
}
