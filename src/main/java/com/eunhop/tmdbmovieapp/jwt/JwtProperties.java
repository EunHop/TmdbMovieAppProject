package com.eunhop.tmdbmovieapp.jwt;

import lombok.Getter;

public enum JwtProperties {
  // cookie.setMaxage()는 초 단위 이고 jwts.builder().setExpiration()은 밀리초 단위 이다
  // 밀리초 = 1초 * 1000, 초단위를 기준으로 해서 Expriation에는 * 1000 해줌
  ACCESS_TOKEN("ACCESS-TOKEN", 21600), // 6시간 60(초)*60(분)*6(시) 21600
  REFRESH_TOKEN("REFRESH-TOKEN", 1209600); // 2주 60(초)*60(분)*24(시)*14(일)

  @Getter
  private final String description;
  @Getter
  private final Integer time;

  JwtProperties(String description, int time) {
    this.description = description;
    this.time = time;
  }
}
