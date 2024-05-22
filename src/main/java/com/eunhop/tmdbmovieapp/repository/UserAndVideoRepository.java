package com.eunhop.tmdbmovieapp.repository;

import com.eunhop.tmdbmovieapp.domain.UserAndVideo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserAndVideoRepository extends JpaRepository<UserAndVideo, Long> {
  UserAndVideo findByUserIdAndVideoId(Long userId, int videoId);
  List<UserAndVideo> findByVideoIdAndReviewIsNotNull(int videoId);
  Page<UserAndVideo> findByUserIdAndReviewIsNotNull(Long userId, Pageable pageable);
  List<UserAndVideo> findByUserIdAndReviewIsNotNull(Long userId);
  List<UserAndVideo> findByVideoIdAndUserIdNotInAndReviewIsNotNull(int video_id, List<Long> user_id);
  List<UserAndVideo> findByUserIdOrderByCreatedAt(Long userId);
  Page<UserAndVideo> findByReviewIsNotNull(Pageable pageable);
}
