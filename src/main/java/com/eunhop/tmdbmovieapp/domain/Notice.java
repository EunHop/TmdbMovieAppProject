package com.eunhop.tmdbmovieapp.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "notice")
@Entity
public class Notice extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column(length = 100)
  private String title;

  @NotNull
  @Column(length = 500)
  private String content;

  @NotNull
  @Column(length = 100)
  private String createBy;

  @NotNull
  @Column(length = 100)
  private String modifiedBy;

  @NotNull
  private boolean enabled;

  // 유저 권한(Admin)에 따라 공지사항 쓰기, 삭제, 수정이 가능하다. 그럼 Admin일때만 뷰 단에서 처리하면 되지 굳이 관계를 지정해 주어야 하나 싶었지만,
  // 어드민이 여러명일 경우 user_id를 통해 누가 썼는지 누가 수정했는지 알려면 notice.getUser()가 필요한거 같아 단방향 통신으로 관계를 지정했다. 2023/09/28
  @ManyToOne
  private User user;
}
