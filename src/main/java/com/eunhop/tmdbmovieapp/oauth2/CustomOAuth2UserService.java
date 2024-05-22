package com.eunhop.tmdbmovieapp.oauth2;

import com.eunhop.tmdbmovieapp.dto.security.PrincipalUser;
import com.eunhop.tmdbmovieapp.service.OAuth2Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

  private final OAuth2Service oAuth2Service;

  @Override
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    final DefaultOAuth2UserService userService = new DefaultOAuth2UserService();
    final OAuth2User oAuth2User = userService.loadUser(userRequest);
    String provider = userRequest.getClientRegistration().getRegistrationId();

    // Unchecked cast: 'java.lang.Object' to 'java.util.Map<java.lang.String,java.lang.Object>' 문제
    // Java 에서는 Serializable object 에 실제 담겨져있는 Object type 을 유추할 방법이 없기 때문에
    //소스 리뷰 상으로만 type mismatch 가능성을 한번 더 확인하고 '@SuppressWarnings("unchecked")' annotation 으로 처리
    @SuppressWarnings("unchecked")
    Map<String, Object> attributesResponse = (Map<String, Object>) oAuth2User.getAttributes().get("kakao_account");
    @SuppressWarnings("unchecked")
    Map<String, Object> profileResponse = (Map<String, Object>) attributesResponse.get("profile");
    String resNickname = profileResponse.get("nickname").toString();
    String resEmail = attributesResponse.get("email").toString();

    return new PrincipalUser(
        oAuth2Service.userAndOAuth2DBSave(resEmail, resNickname, provider),
        attributesResponse
    );
  }

}
