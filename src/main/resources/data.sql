insert into user (`email`, `password`, `name`, `role`, `enabled`, `created_at`, `modified_at`)
values ('choo@test.com', '{noop}1234', 'choo', 'USER', true, now(), now());
insert into user (`email`, `password`, `name`, `role`, `enabled`, `created_at`, `modified_at`)
values ('choo2@test.com', '{noop}1234', 'choo2', 'USER', true, now(), now());
insert into user (`email`, `password`, `name`, `role`, `enabled`, `created_at`, `modified_at`)
values ('choo3@test.com', '{noop}1234', 'choo3', 'USER', true, now(), now());
insert into user (`email`, `password`, `name`, `role`, `enabled`, `created_at`, `modified_at`)
values ('choo4@test.com', '{noop}1234', 'choo4', 'USER', true, now(), now());
insert into user (`email`, `password`, `name`, `role`, `enabled`, `created_at`, `modified_at`)
values ('choo5@test.com', '{noop}1234', 'choo5', 'USER', true, now(), now());
insert into user (`email`, `password`, `name`, `role`, `enabled`, `created_at`, `modified_at`)
values ('choo6@test.com', '{noop}1234', 'choo6', 'USER', true, now(), now());
insert into user (`email`, `password`, `name`, `role`, `enabled`, `created_at`, `modified_at`)
values ('choo7@test.com', '{noop}1234', 'choo7', 'USER', true, now(), now());
insert into user (`email`, `password`, `name`, `role`, `enabled`, `created_at`, `modified_at`)
values ('choo8@test.com', '{noop}1234', 'choo8', 'USER', true, now(), now());
insert into user (`email`, `password`, `name`, `role`, `enabled`, `created_at`, `modified_at`)
values ('choo9@test.com', '{noop}1234', 'choo9', 'USER', true, now(), now());
insert into user (`email`, `password`, `name`, `role`, `enabled`, `created_at`, `modified_at`)
values ('choo10@test.com', '{noop}1234', 'choo10', 'USER', true, now(), now());
insert into user (`email`, `password`, `name`, `role`, `enabled`, `created_at`, `modified_at`)
values ('admin@test.com', '{noop}1234', 'chooAdmin', 'ADMIN', true, now(), now());

insert into video (`id`, `score`, `release_date`, `title`, `poster_path`, `media_type`, `tagline`, `created_at`, `modified_at`)
values (575264, 77, '2023-07-08', '미션 임파서블: 데드 레코닝 PART ONE', '/nQsWPG020kSWdOl3EhFXRNE2s0n.jpg', 'movie', '가장 위험한 작전, 그의 마지막 선택', now(), now());
insert into video (`id`, `score`, `release_date`, `title`, `poster_path`, `media_type`, `tagline`, `created_at`, `modified_at`)
values (239770, 77, '', '닥터 후', '/zHu1etq7NIZiTV8jzEVNLBerqLA.jpg', 'tv', '', now(), now());
insert into video (`id`, `score`, `release_date`, `title`, `poster_path`, `media_type`, `tagline`, `created_at`, `modified_at`)
values (96648, 84, '2020-12-18', '스위트홈', '/eNfNu9sJ2eVmMcbrKpgEovPoyB8.jpg', 'tv', '죽어버리거나, 괴물로 살아남거나', now(), now());
insert into video (`id`, `score`, `release_date`, `title`, `poster_path`, `media_type`, `tagline`, `created_at`, `modified_at`)
values (872585, 82, '2023-07-19', '오펜하이머', '/kAYtsVpE7q6NhLz6vKYAxOM6LmN.jpg', 'movie', '나는 이제 죽음이요, 세상의 파괴자가 되었다.', now(), now());

insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (true, 575264, 1, '한줄평1 엄청 긴 한줄평 남기기 엄청 긴 한줄평 남기기 엄청 긴 한줄평 남기기 엄청 긴 한줄평 남기기 엄청 긴 한줄평 남기기 엄청 긴 한줄평 남기기', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 575264, 2, '한줄평2', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 575264, 3, '한줄평3', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 575264, 4, '한줄평4', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 575264, 5, '한줄평5', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 575264, 6, '한줄평6', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 575264, 7, '한줄평7', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 575264, 8, '한줄평8', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 575264, 9, '한줄평9', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 575264, 10, '한줄평10', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (true, 239770, 1, '한줄평1', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 239770, 2, '한줄평2', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 239770, 3, '한줄평3', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 239770, 4, '한줄평4', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 239770, 5, '한줄평5', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 239770, 6, '한줄평6', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 239770, 7, '한줄평7', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 239770, 8, '한줄평8', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 239770, 9, '한줄평9', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 239770, 10, '한줄평10', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (true, 96648, 1, '한줄평1', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 96648, 2, '한줄평2', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 96648, 3, '한줄평3', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 96648, 4, '한줄평4', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 96648, 5, '한줄평5', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 96648, 6, '한줄평6', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 96648, 7, '한줄평7', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 96648, 8, '한줄평8', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 96648, 9, '한줄평9', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 96648, 10, '한줄평10', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (true, 872585, 1, '한줄평1', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 872585, 2, '한줄평2', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 872585, 3, '한줄평3', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 872585, 4, '한줄평4', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 872585, 5, '한줄평5', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 872585, 6, '한줄평6', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 872585, 7, '한줄평7', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 872585, 8, '한줄평8', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 872585, 9, '한줄평9', now(), now());
insert into user_and_video (`wish`, `video_id`, `user_id`, `review`, `created_at`, `modified_at`)
values (false, 872585, 10, '한줄평10', now(), now());

insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('Movie App의 사용법', '로그인 안한 상태로 영화나 TV프로그램 검색 및 상세정보, 공지사항을 볼 수 있습니다.<br>로그인 한 상태로 상세정보 페이지에서 즐겨찾기 추가 및 제거, 한줄평 남기기가 가능합니다.<br>또 즐겨찾기 페이지에서 즐겨찾기를 볼 수 있고 아이콘을 클릭해 제거할 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('Movie App의 사용법', '로그인 안한 상태로 영화나 TV프로그램 검색 및 상세정보, 공지사항을 볼 수 있습니다.<br>로그인 한 상태로 상세정보 페이지에서 즐겨찾기 추가 및 제거, 한줄평 남기기가 가능합니다.<br>또 즐겨찾기 페이지에서 즐겨찾기를 볼 수 있고 아이콘을 클릭해 제거할 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('Movie App의 사용법', '로그인 안한 상태로 영화나 TV프로그램 검색 및 상세정보, 공지사항을 볼 수 있습니다.<br>로그인 한 상태로 상세정보 페이지에서 즐겨찾기 추가 및 제거, 한줄평 남기기가 가능합니다.<br>또 즐겨찾기 페이지에서 즐겨찾기를 볼 수 있고 아이콘을 클릭해 제거할 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('Movie App의 사용법', '로그인 안한 상태로 영화나 TV프로그램 검색 및 상세정보, 공지사항을 볼 수 있습니다.<br>로그인 한 상태로 상세정보 페이지에서 즐겨찾기 추가 및 제거, 한줄평 남기기가 가능합니다.<br>또 즐겨찾기 페이지에서 즐겨찾기를 볼 수 있고 아이콘을 클릭해 제거할 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('Movie App의 사용법', '로그인 안한 상태로 영화나 TV프로그램 검색 및 상세정보, 공지사항을 볼 수 있습니다.<br>로그인 한 상태로 상세정보 페이지에서 즐겨찾기 추가 및 제거, 한줄평 남기기가 가능합니다.<br>또 즐겨찾기 페이지에서 즐겨찾기를 볼 수 있고 아이콘을 클릭해 제거할 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('Movie App의 사용법', '로그인 안한 상태로 영화나 TV프로그램 검색 및 상세정보, 공지사항을 볼 수 있습니다.<br>로그인 한 상태로 상세정보 페이지에서 즐겨찾기 추가 및 제거, 한줄평 남기기가 가능합니다.<br>또 즐겨찾기 페이지에서 즐겨찾기를 볼 수 있고 아이콘을 클릭해 제거할 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('Movie App의 사용법', '로그인 안한 상태로 영화나 TV프로그램 검색 및 상세정보, 공지사항을 볼 수 있습니다.<br>로그인 한 상태로 상세정보 페이지에서 즐겨찾기 추가 및 제거, 한줄평 남기기가 가능합니다.<br>또 즐겨찾기 페이지에서 즐겨찾기를 볼 수 있고 아이콘을 클릭해 제거할 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('Movie App의 사용법', '로그인 안한 상태로 영화나 TV프로그램 검색 및 상세정보, 공지사항을 볼 수 있습니다.<br>로그인 한 상태로 상세정보 페이지에서 즐겨찾기 추가 및 제거, 한줄평 남기기가 가능합니다.<br>또 즐겨찾기 페이지에서 즐겨찾기를 볼 수 있고 아이콘을 클릭해 제거할 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('Movie App의 사용법', '로그인 안한 상태로 영화나 TV프로그램 검색 및 상세정보, 공지사항을 볼 수 있습니다.<br>로그인 한 상태로 상세정보 페이지에서 즐겨찾기 추가 및 제거, 한줄평 남기기가 가능합니다.<br>또 즐겨찾기 페이지에서 즐겨찾기를 볼 수 있고 아이콘을 클릭해 제거할 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('Movie App의 사용법', '로그인 안한 상태로 영화나 TV프로그램 검색 및 상세정보, 공지사항을 볼 수 있습니다.<br>로그인 한 상태로 상세정보 페이지에서 즐겨찾기 추가 및 제거, 한줄평 남기기가 가능합니다.<br>또 즐겨찾기 페이지에서 즐겨찾기를 볼 수 있고 아이콘을 클릭해 제거할 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('Movie App의 사용법', '로그인 안한 상태로 영화나 TV프로그램 검색 및 상세정보, 공지사항을 볼 수 있습니다.<br>로그인 한 상태로 상세정보 페이지에서 즐겨찾기 추가 및 제거, 한줄평 남기기가 가능합니다.<br>또 즐겨찾기 페이지에서 즐겨찾기를 볼 수 있고 아이콘을 클릭해 제거할 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('Movie App의 사용법', '로그인 안한 상태로 영화나 TV프로그램 검색 및 상세정보, 공지사항을 볼 수 있습니다.<br>로그인 한 상태로 상세정보 페이지에서 즐겨찾기 추가 및 제거, 한줄평 남기기가 가능합니다.<br>또 즐겨찾기 페이지에서 즐겨찾기를 볼 수 있고 아이콘을 클릭해 제거할 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('악성 유저의 대한 대응', '안녕하세요.<br>악성 한줄평을 남기는 유저는 적발시 한줄평 쓰기 권한을 빼고 해당 유저의 모든 한줄평이 제거됩니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
insert into notice (`title`, `content`, `create_by`, `modified_by`, `enabled`, `user_id`, `created_at`, `modified_at`)
values ('저희 사이트를 찾아주셔서 감사합니다.', '안녕하세요.<br>저희 사이트를 찾아주셔서 감사합니다.<br>해당 프로젝트의 모든 코드들은 우측 하단에 github를 클릭하시면 확인 하실 수 있습니다.', 'chooAdmin', 'chooAdmin', true, 11, now(), now());
