package com.ssafy.dksl.util;

import com.ssafy.dksl.util.exception.TokenInvalidException;
import com.ssafy.dksl.util.exception.common.CustomException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

@Component
@Slf4j
public class JwtUtil implements InitializingBean {

    private final long accessValidityIn;  // token 유효 기간
    private final long refreshValidityIn;  // token 유효 기간
    private final String secret;
    private Key key;


    public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.token-validity-in-seconds}") long validityInSeconds) {
        this.secret = secret;
        this.accessValidityIn = 2 * 60 * 60 * validityInSeconds;  // 2시간
        // this.accessValidityIn = 3 * validityInSeconds;  // 3초
        this.refreshValidityIn = 14 * 24 * 60 * 60 * validityInSeconds;  // 2주일
    }

    // Bean 주입 받은 후 할당
    @Override
    public void afterPropertiesSet() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // 토큰 정보
    public String generateToken(String clientId, String role, boolean isRefresh) {
        Claims claims = Jwts.claims().setSubject(clientId);  // 사용하는 클레임 세트
        claims.put("role", role);  // 임의 역할 부여
        // key = Keys.hmacShaKeyFor(secret.getBytes());

        return Jwts.builder().setClaims(claims).setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + ((isRefresh)? refreshValidityIn : accessValidityIn))) // 해당 옵션 없으면 expire X
                .signWith(key, SignatureAlgorithm.HS512) // 사용할 암호화 알고리즘과 signature에 들어갈 secret 값
                .compact();
    }

    // 토큰으로부터 받은 정보를 기반으로 Authentication 객체 반환
    public Authentication getAuthentication(String accessToken) {
        return new UsernamePasswordAuthenticationToken(getClientId(accessToken), "", createAuthorityList(getRole(accessToken)));
    }

    // 토큰 검증
    public boolean validateToken(String token) throws TokenInvalidException {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(removeBearer(token));
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다.");
            throw new TokenInvalidException();
        } catch (ExpiredJwtException e) {  // Access 토큰이 만료 되었을 때
            log.error("JWT 토큰이 만료되었습니다.");
            throw new TokenInvalidException();
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다.");
            throw new TokenInvalidException();
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다.");
            throw new TokenInvalidException();
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new TokenInvalidException();
        }
    }

    // Authorization Header를 통해 인증
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰에 담겨있는 clientId 획득
    public String getToken(String token) {
        return removeBearer(token);
    }
    // 토큰에 담겨있는 clientId 획득
    public String getClientId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(removeBearer(token))
                .getBody().getSubject();
    }

    private String getRole(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(removeBearer(accessToken))
                .getBody()
                .get("role", String.class);

    }

    private String removeBearer(String token) {
        return token.replace("Bearer ", "");
    }

}
