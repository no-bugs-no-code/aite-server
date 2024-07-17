package com.example.aiet_server.global.interceptor.jwt;

import com.example.aiet_server.domain.user.entity.UserEntity;
import com.example.aiet_server.domain.user.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    private final UserRepository userRepository;

    @Value("${key.jwt.secret-key}")
    private String SECRET_KEY;

    private static final Long ACCESS_TOKEN_EXPIRE_TIME = 1000L * 3600 * 24;

    private Key getSignKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private String generateToken(String id, Long time, TokenType type) {
        Claims claims = Jwts.claims();
        claims.put("id", id);
        claims.put("type", type);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + time))
                .signWith(getSignKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateAccessToken(String id) {
        return generateToken(id, ACCESS_TOKEN_EXPIRE_TIME, TokenType.ACCESSTOKEN);
    }
    public String generateRefreshToken(String id) {
        return  generateToken(id, ACCESS_TOKEN_EXPIRE_TIME, TokenType.REFRESHTOKEN);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey(SECRET_KEY))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public TokenType checkTokenType(String token) {
        if ("REFRESHTOKEN".equals(extractAllClaims(token).get("type"))) {
            return TokenType.REFRESHTOKEN;
        } else {
            return TokenType.ACCESSTOKEN;
        }
    }

    public UserEntity getUserByToken(String token) {
        Object a = extractAllClaims(token).get("id");
        return userRepository.findById(a.toString());
    }
}
