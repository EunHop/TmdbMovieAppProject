package com.eunhop.tmdbmovieapp.repository;

import com.eunhop.tmdbmovieapp.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
  Page<Notice> findByEnabled(Pageable pageable, boolean enabled);
  Page<Notice> findByEnabledAndTitleContainingIgnoreCase(Pageable pageable, boolean enabled, String query);
}
