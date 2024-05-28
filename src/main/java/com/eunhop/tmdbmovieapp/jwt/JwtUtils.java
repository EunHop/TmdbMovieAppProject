package com.eunhop.tmdbmovieapp.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;

import java.security.Key;
import java.util.Date;

@Slf4j
public class JwtUtils {
    /**
     * 토큰에서 userEmail 찾기
     * 제대로된 토큰인지 검증과정
     *
     * @param token 토큰
     * @return userEmail
     */
    public static String getUserEmail(String token) {
        // jwtToken 에서 userEmail 을 찾습니다.
        try {
            return Jwts.parserBuilder()
                // 서명을 검증하기 위해 SigningKeyResolver 를 설정합니다.
                // SigningKeyResolver.instance 는 싱글톤 패턴으로 구현된 SigningKeyResolver의 인스턴스입니다.
                .setSigningKeyResolver(SigningKeyResolver.instance)
                // 설정된 빌더를 사용하여 JWT 파서를 빌드합니다.
                .build()
                // 주어진 JWT 토큰을 파싱하고 서명을 검증합니다.
                // 유효한 서명의 경우 JWT 의 클레임을 포함하는 Jws<Claims> 객체를 반환합니다.
                .parseClaimsJws(token)
                // 반환된 Jws<Claims> 객체에서 JWT 의 페이로드 부분을 가져옵니다.
                .getBody()
                .getSubject(); // username
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * access 토큰에서 만료날이 지났는지 확인하기 (안 지나면 true)
     *
     * @param token 토큰
     * @return boolean
     */
    public static boolean accessTokenNotExpired(String token) {
        try {
            Date expiration = Jwts.parserBuilder()
                .setSigningKeyResolver(SigningKeyResolver.instance)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
            Date now = new Date();
            return now.before(expiration);
        } catch (ExpiredJwtException e) {
            return false;
        } catch (Exception ge) {
            log.info("please login again!");
            return false;
        }
    }

    /**
     * Principal 로 Access 토큰 생성
     * HEADER : alg, kid
     * PAYLOAD : sub, iat, exp
     * SIGNATURE : JwtKey.getRandomKey로 구한 Secret Key로 HS512 해시
     *
     * @param value email
     * @return jwtToken
     */
    public static String createAccessToken(String value) {
        Claims claims = Jwts.claims().setSubject(value); // subject
        Date now = new Date(); // 현재 시간
        Pair<String, Key> key = JwtKey.getRandomKey();
        // JWT Token 생성
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + (JwtProperties.ACCESS_TOKEN.getTime() * 1000))) // 토큰 만료 시간 설정
                .setHeaderParam(JwsHeader.KEY_ID, key.getFirst()) // kid
                .signWith(key.getSecond()) // signature
                .compact();
    }
    /**
     * Principal 로 Refresh 토큰 생성
     * HEADER : alg, kid
     * PAYLOAD : sub, iat, exp
     * SIGNATURE : JwtKey.getRandomKey로 구한 Secret Key로 HS512 해시
     *
     * @param value email
     * @return jwtToken
     */
    public static String createRefreshToken(String value) {
        Claims claims = Jwts.claims().setSubject(value); // subject
        Date now = new Date(); // 현재 시간
        Pair<String, Key> key = JwtKey.getRandomKey();
        // JWT Token 생성
        return Jwts.builder()
            .setClaims(claims) // 정보 저장
            .setIssuedAt(now) // 토큰 발행 시간 정보
            .setExpiration(new Date(now.getTime() + (JwtProperties.REFRESH_TOKEN.getTime() * 1000))) // 토큰 만료 시간 설정
            .setHeaderParam(JwsHeader.KEY_ID, key.getFirst()) // kid
            .signWith(key.getSecond()) // signature
            .compact();
    }
}
