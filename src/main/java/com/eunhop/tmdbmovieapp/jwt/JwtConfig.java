package com.eunhop.tmdbmovieapp.jwt;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * `@Value` 애노테이션을 사용하여 주입된 값을 final 필드로 만들어 static 메소드에서 사용하려면,
 * 이를 관리하는 별도의 클래스를 만들고 싱글톤 패턴을 활용하여 필드 값을 접근하도록 합니다.
 */
@Component
public class JwtConfig {

  // static 메소드에서 사용하기 위한 접근자 메소드
  @Getter
  private static String jwtKeyOne;
  @Getter
  private static String jwtKeyTwo;
  @Getter
  private static String jwtKeyThree;

  public JwtConfig(@Value("${jwt.key.one}") String jwtKeyOne,
                   @Value("${jwt.key.two}") String jwtKeyTwo,
                   @Value("${jwt.key.three}") String jwtKeyThree) {
    JwtConfig.jwtKeyOne = jwtKeyOne;
    JwtConfig.jwtKeyTwo = jwtKeyTwo;
    JwtConfig.jwtKeyThree = jwtKeyThree;
  }

}
