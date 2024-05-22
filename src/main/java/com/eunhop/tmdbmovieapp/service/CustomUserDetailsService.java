package com.eunhop.tmdbmovieapp.service;

import com.eunhop.tmdbmovieapp.domain.User;
import com.eunhop.tmdbmovieapp.dto.security.PrincipalUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userService.findByEmail(username);
    if (user.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }
    return new PrincipalUser(user.get());
  }
}
