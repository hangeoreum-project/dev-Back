package com.hangeoreum.hanback.auth.jwt;

public class JwtProperties {
    public static final String SECRET = "hangeoreum-secret-key"; // 환경변수로 교체 권장
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 72; // 3일
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
