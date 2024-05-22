package com.eunhop.tmdbmovieapp.repository;

import com.eunhop.tmdbmovieapp.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
