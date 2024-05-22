package com.eunhop.tmdbmovieapp.service;

import com.eunhop.tmdbmovieapp.domain.Notice;
import com.eunhop.tmdbmovieapp.dto.security.PrincipalUser;
import com.eunhop.tmdbmovieapp.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {
  private final NoticeRepository noticeRepository;

  public Page<Notice> findEnabledAll(int pageNo, String sort) {
    Pageable pageable = PageRequest.of(pageNo, 8, Sort.by(Sort.Direction.DESC, sort));
    return noticeRepository.findByEnabled(pageable, true);
  }

  public Page<Notice> findQueryEnabledAll(int pageNo, String sort, String query, boolean enabled) {
    Pageable pageable = PageRequest.of(pageNo, 8, Sort.by(Sort.Direction.DESC, sort));
    return noticeRepository.findByEnabledAndTitleContainingIgnoreCase(pageable, enabled, query);
  }

  public void modifyContent(PrincipalUser principalUser, String content, long id) {
    String replaceContents = content.replace("\r\n", "<br>");
    Notice find = noticeRepository.findById(id).get();
    find.setModifiedBy(principalUser.getName());
    find.setContent(replaceContents);
    noticeRepository.save(find);
  }

  public void noticeDelete(PrincipalUser principalUser, long id) {
    Notice find = noticeRepository.findById(id).get();
    find.setModifiedBy(principalUser.getName());
    find.setEnabled(false);
    noticeRepository.save(find);
  }

  public Page<Notice> findDisabledAll(int pageNo, String sort) {
    Pageable pageable = PageRequest.of(pageNo, 8, Sort.by(Sort.Direction.DESC, sort));
    return noticeRepository.findByEnabled(pageable, false);
  }

  public void noticeRestore(long id) {
    Notice find = noticeRepository.findById(id).get();
    find.setEnabled(true);
    noticeRepository.save(find);
  }

  public void noticeCreate(PrincipalUser principalUser, String title, String content) {
    String replaceContents = content.replace("\r\n", "<br>");
    Notice newNotice = Notice.builder().title(title)
        .content(replaceContents).createBy(principalUser.getName())
        .modifiedBy(principalUser.getName()).enabled(true).user(principalUser.getUser()).build();
    noticeRepository.save(newNotice);
  }
}
