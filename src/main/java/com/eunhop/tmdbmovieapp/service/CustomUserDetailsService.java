package com.eunhop.tmdbmovieapp.service;

import com.eunhop.tmdbmovieapp.domain.User;
import com.eunhop.tmdbmovieapp.dto.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("loadUserByUsername");
    Optional<User> user = userService.findByEmail(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }
    return new PrincipalUser(user.get());
  }
}
