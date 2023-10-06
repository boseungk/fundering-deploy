package com.theocean.fundering.global.config.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Component
public class JwtProvider {
    public static final Long ACCESS_EXP = 1000L * 60 * 60; // 1시간
    public static final Long REFRESH_EXP = 1000L * 60 * 60 * 24 * 14; // 2주

    private static final String EMAIL_CLAIM = "email";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String ACCESS_HEADER = "Authorization";
    public static final String REFRESH_HEADER = "Authorization-refresh";

    public static final String ACCESS_SECRET = "MyAccessSecretKey1234";
    public static final String REFRESH_SECRET = "MyRefreshSecretKey1234";


    public static String createAccessToken(String email) {
        String jwt = JWT.create()
                .withSubject("AccessToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_EXP))
                .withClaim(EMAIL_CLAIM, email)
                .sign(Algorithm.HMAC512(ACCESS_SECRET));
        return TOKEN_PREFIX + jwt;
    }

    public static String createRefreshToken(String email) {
        String jwt = JWT.create()
                .withSubject("AccessToken")
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_EXP))
                .withClaim(EMAIL_CLAIM, email)
                .sign(Algorithm.HMAC512(REFRESH_SECRET));
        return TOKEN_PREFIX + jwt;
    }

    public static DecodedJWT verifyAccessToken(String jwt) throws SignatureVerificationException, TokenExpiredException {
        jwt = jwt.replace(JwtProvider.TOKEN_PREFIX, "");
        return JWT.require(Algorithm.HMAC512(ACCESS_SECRET))
                .build().verify(jwt);
    }

    public static DecodedJWT verifyRefreshToken(String jwt) throws SignatureVerificationException, TokenExpiredException {
        jwt = jwt.replace(JwtProvider.TOKEN_PREFIX, "");
        return JWT.require(Algorithm.HMAC512(REFRESH_SECRET))
                .build().verify(jwt);
    }

    public static Optional<String> extractAccessToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(ACCESS_HEADER))
                .filter(refreshToken -> refreshToken.startsWith(TOKEN_PREFIX))
                .map(refreshToken -> refreshToken.replace(TOKEN_PREFIX, ""));
    }

    public static Optional<String> extractRefreshToken(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(REFRESH_HEADER))
                .filter(refreshToken -> refreshToken.startsWith(TOKEN_PREFIX))
                .map(refreshToken -> refreshToken.replace(TOKEN_PREFIX, ""));
    }

    public static void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
        response.setStatus(HttpServletResponse.SC_OK);

        setAccessTokenHeader(response, accessToken);
        setRefreshTokenHeader(response, refreshToken);
    }

    public static void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
        response.setHeader(ACCESS_HEADER, accessToken);
    }

    public static void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
        response.setHeader(REFRESH_HEADER, refreshToken);
    }

    public static boolean isTokenValid(String token) {
        try {
            JWT.require(Algorithm.HMAC512(ACCESS_SECRET)).build().verify(token);
            return true;
        } catch (Exception e) {
            log.error("유효하지 않은 토큰입니다. {}", new String[]{e.getMessage()});
            return false;
        }
    }

    public static Optional<String> extractEmail(String accessToken) {
        try {
            return Optional.ofNullable(JWT.require(Algorithm.HMAC512(ACCESS_SECRET))
                    .build()
                    .verify(accessToken) // accessToken 검증
                    .getClaim(EMAIL_CLAIM) // claim(Email) 가져오기
                    .asString());
        } catch (Exception e) {
            log.error("액세스 토큰이 유효하지 않습니다.");
            return Optional.empty();
        }
    }
}
