package br.com.api;

import br.com.api.service.JwtTokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JwtTokenProviderTest {


    private String jwtSecret = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ98eyJpc3MiOiJPbmxpbmUgSldUIEJ1aWxkZXIiLCJpYXQiOjE3MTQ5NTkwMDcsImV4cCI6MTc0NjQ5NTAwNywiYXVkIjoid3d3LmV4YW1wbGUuY29tIiwic3ViIjoianJvY2tldEBleGFtcGxlLmNvbSIsIkdpdmVuTmFtZSI6IkpvaG5ueSIsIlN1cm5hbWUiOiJSb2NrZXQiLCJFbWFpbCI6Impyb2NrZXRAZXhhbXBsZS5jb20iLCJSb2xlIjpbIk1hbmFnZXIiLCJQcm9qZWN0IEFkbWluaXN0cmF0b3IiXX0ofHVPY3qrOvxCyv8SkNeBMoQZQ0rWMhiKakT3aT1rIwSM67pmV1LTFEBKkaWewkjQyyo7MVsqgQV49ZOFHg";

    @InjectMocks
    private JwtTokenService jwtTokenProvider;


    @Test
    void parseTokenInvalidToken() {
        String invalidToken = "invalidToken";

        Claims claims = jwtTokenProvider.parseToken(invalidToken);

        assertNull(claims);

    }
}
