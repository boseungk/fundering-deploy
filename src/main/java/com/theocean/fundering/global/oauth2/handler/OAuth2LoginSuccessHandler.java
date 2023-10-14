package com.theocean.fundering.global.oauth2.handler;

import com.theocean.fundering.global.jwt.JwtProvider;
import com.theocean.fundering.global.oauth2.CustomOAuth2User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2Login Success");
        try {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            loginSuccess(response, oAuth2User);
        } catch (Exception e) {
            throw e;
        }
    }

    private void loginSuccess(HttpServletResponse response, CustomOAuth2User oAuth2User) throws IOException {
        String accessToken = jwtProvider.createAccessToken(oAuth2User.getEmail());
        String refreshToken = jwtProvider.createRefreshToken(oAuth2User.getEmail());
        response.setHeader(JwtProvider.ACCESS_HEADER, accessToken);
        response.setHeader(JwtProvider.REFRESH_HEADER, refreshToken);

        jwtProvider.sendAccessAndRefreshToken(response, accessToken, refreshToken);
    }
}
