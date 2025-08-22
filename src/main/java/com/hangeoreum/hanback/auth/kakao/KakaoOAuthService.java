package com.hangeoreum.hanback.auth.kakao;

import com.hangeoreum.hanback.auth.model.KakaoUser;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.json.JSONObject;

@Service
public class KakaoOAuthService {

    private final WebClient webClient = WebClient.create();

    public KakaoUser getUserInfo(String accessToken) {
        String response = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JSONObject json = new JSONObject(response);
        Long id = json.getLong("id");
        JSONObject properties = json.getJSONObject("properties");
        JSONObject kakaoAccount = json.getJSONObject("kakao_account");

        return KakaoUser.builder()
                .kakaoId(id)
                .nickname(properties.optString("nickname"))
                .email(kakaoAccount.optString("email"))
                .profileImageUrl(properties.optString("profile_image"))
                .build();
    }
}
