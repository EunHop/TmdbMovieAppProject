package com.eunhop.tmdbmovieapp.service;

import com.eunhop.tmdbmovieapp.domain.OAuth2;
import com.eunhop.tmdbmovieapp.domain.Roles;
import com.eunhop.tmdbmovieapp.domain.User;
import com.eunhop.tmdbmovieapp.repository.OAuth2Repository;
import com.eunhop.tmdbmovieapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class OAuth2Service {

  private final UserRepository userRepository;
  private final OAuth2Repository oAuth2Repository;
  private final PasswordEncoder passwordEncoder;

  public User userAndOAuth2DBSave(
      String resEmail,
      String resName,
      String provider
  ) {
    log.info("userAndOAuth2DBSave");
    Optional<User> user = userRepository.findByEmail(resEmail);
    Optional<OAuth2> existOauth2 = oAuth2Repository.findByProviderAndEmail(provider, resEmail);
    OAuth2 oauth2 = OAuth2.builder()
        .name(resName)
        .email(resEmail)
        .provider(provider).build();
    if (existOauth2.isPresent() && user.isPresent()) {
      existOauth2.get().setName(resName);
      oAuth2Repository.save(existOauth2.get());
      user.get().setName(resName);
      userRepository.save(user.get());
      return existOauth2.get().getUser();
    } else if (user.isPresent()) {
      oauth2.setUser(user.get());
      oAuth2Repository.save(oauth2);
      return user.get();
    } else {
      User newUser = User.builder()
          .name(resName)
          .email(resEmail)
          .role(Roles.USER)
          .password(passwordEncoder.encode("{bcrypt}" + UUID.randomUUID()))
          .enabled(true)
          .build();
      userRepository.save(newUser);
      oauth2.setUser(newUser);
      oAuth2Repository.save(oauth2);
      return newUser;
    }
  }

}
