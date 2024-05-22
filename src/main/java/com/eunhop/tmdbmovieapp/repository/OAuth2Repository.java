package com.eunhop.tmdbmovieapp.repository;

import com.eunhop.tmdbmovieapp.domain.OAuth2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OAuth2Repository extends JpaRepository<OAuth2, Long> {
  Optional<OAuth2> findByProviderAndEmail(String provider, String email);
  List<OAuth2> findByEmail(String email);
}
