package com.hangeoreum.hanback;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")  // application-test.yml 사용할 수도 있음
@TestPropertySource(properties = {
    "JWT_SECRET=test-secret-123456789012345678901234567890",
    "JWT_EXPIRATION_MS=3600000",
    "spring.datasource.url=jdbc:mysql://localhost:3306/tour2025_test?serverTimezone=Asia/Seoul&characterEncoding=UTF-8",
    "spring.datasource.username=test_user",
    "spring.datasource.password=test_pwd",
    "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver"
})
class HanbackApplicationTests {

    @Test
    void contextLoads() {
        // context 정상 로드 확인
    }
}
