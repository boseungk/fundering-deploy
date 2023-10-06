package com.theocean.fundering.global.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theocean.fundering.global.errors.exception.Exception400;
import com.theocean.fundering.global.errors.exception.Exception401;
import com.theocean.fundering.global.utils.ApiUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

/**
 * JWT 로그인 실패 시 처리하는 핸들러
 * SimpleUrlAuthenticationFailureHandler를 상속받아서 구현
 */
@Slf4j
@RequiredArgsConstructor
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private final String FAILURE_MESSAGE = "로그인에 실패했습니다.";
    private final ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        log.info("로그인에 실패했습니다. 메시지 : {}", exception.getMessage());
        String result = objectMapper.writeValueAsString(ApiUtils.error(FAILURE_MESSAGE, new Exception400("로그인에 실패했습니다.").status()));
        response.getWriter().write(result);
    }
}
