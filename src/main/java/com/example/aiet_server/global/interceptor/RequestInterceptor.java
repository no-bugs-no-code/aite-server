package com.example.aiet_server.global.interceptor;

import com.example.aiet_server.domain.user.entity.AccessTokenDto;
import com.example.aiet_server.domain.user.entity.UserEntity;
import com.example.aiet_server.global.annotation.NeedAccess;
import com.example.aiet_server.global.interceptor.jwt.JwtUtil;
import com.example.aiet_server.global.interceptor.jwt.TokenType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;

@Component
@RequiredArgsConstructor
public class RequestInterceptor implements HandlerInterceptor {
    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        if (!(handlerMethod.getMethod().isAnnotationPresent(NeedAccess.class))) {
            return true;
        }

        String token = getTokenOfRequest(request).split(" ")[1];
        TokenType tokenType = jwtUtil.checkTokenType(token);
        UserEntity user = jwtUtil.getUserByToken(token);

        if (checkTokenType(request, tokenType, user) == true) {
            return true;
        }
        request.setAttribute("user", user.getId());

        return true;
    }

    private String getTokenOfRequest(HttpServletRequest request) {
        Enumeration<String> headers = request.getHeaders("Authorization");

        while (headers.hasMoreElements()){
            String value = headers.nextElement();
            if (value != null) {
                return value;
            }
        }

        return Strings.EMPTY;
    }

    private boolean checkTokenType(HttpServletRequest request, TokenType tokenType, UserEntity user) {
        if (tokenType.equals(TokenType.REFRESHTOKEN)) {
            String accessToken = jwtUtil.generateAccessToken(user.getId());
            AccessTokenDto accessTokenDto = new AccessTokenDto(accessToken);
            request.setAttribute("accessToken", accessTokenDto);

            return true;
        } else return false;
    }
}
