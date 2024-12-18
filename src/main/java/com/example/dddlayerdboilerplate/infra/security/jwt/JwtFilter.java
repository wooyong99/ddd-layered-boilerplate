package com.example.dddlayerdboilerplate.infra.security.jwt;

import com.example.dddlayerdboilerplate.infra.security.CustomAuthentication;
import com.example.dddlayerdboilerplate.infra.security.SecurityService;
import com.example.dddlayerdboilerplate.infra.utils.HttpServletUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final JwtProperties jwtProperties;
    private final HttpServletUtils servletUtils;
    private final SecurityService securityService;

    public JwtFilter(TokenProvider tokenProvider, JwtProperties jwtProperties, HttpServletUtils servletUtils, SecurityService securityService) {
        this.tokenProvider = tokenProvider;
        this.jwtProperties = jwtProperties;
        this.servletUtils = servletUtils;
        this.securityService = securityService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = getAccessToken(request);
        String refreshToken = getRefreshToken(request);

        // 액세스 토큰 존재
        if ( accessToken != null ) {
            //  유효한 액세스 토큰
            if ( tokenProvider.validateToken(accessToken) ) {
                setAuthentication(accessToken);
            }
            // 리프레쉬 토큰은 존재하면서 유효한 리프레쉬 토큰
            else if ( refreshToken != null && tokenProvider.validateToken(refreshToken) ) {
                String subject = tokenProvider.getSubject(refreshToken);

                CustomAuthentication authentication = (CustomAuthentication) securityService.getAuthentication(subject);

                // 데이터베이스의 리프레쉬와 동일한 경우
                if( authentication.getMember().getRefreshToken().equals(refreshToken) ) {
                    String newAccessToken = tokenProvider.createAccessToken(subject);
                    String newRefreshToken = tokenProvider.createRefreshToken(subject);

                    servletUtils.addCookie(response,"AccessToken", newAccessToken, (int) jwtProperties.getRefreshTokenExpiration());
                    servletUtils.addCookie(response,"RefreshToken", newRefreshToken, (int) jwtProperties.getRefreshTokenExpiration());

                    setAuthentication(newAccessToken);
                }
            }
        }
        // 리프레쉬 토큰 존재
        else if (refreshToken != null) {
            if( tokenProvider.validateToken(refreshToken) ) {
                String subject = tokenProvider.getSubject(accessToken);

                CustomAuthentication authentication = (CustomAuthentication) securityService.getAuthentication(subject);

                // 데이터베이스의 리프레쉬와 동일한 경우
                if( authentication.getMember().getRefreshToken().equals(refreshToken) ) {
                    String newAccessToken = tokenProvider.createAccessToken(subject);
                    String newRefreshToken = tokenProvider.createRefreshToken(subject);

                    servletUtils.addCookie(response,"AccessToken", newAccessToken, (int) jwtProperties.getRefreshTokenExpiration());
                    servletUtils.addCookie(response,"RefreshToken", newRefreshToken, (int) jwtProperties.getRefreshTokenExpiration());

                    setAuthentication(newAccessToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(String accessToken) {
        Authentication authentication = securityService.getAuthentication(tokenProvider.getSubject(accessToken));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String getAccessToken(HttpServletRequest request) {
        return servletUtils.getCookie(request, "AccessToken")
                .map(Cookie::getValue)
                .orElse(null);
    }

    private String getRefreshToken(HttpServletRequest request) {
        return servletUtils.getCookie(request, "RefreshToken")
                .map(Cookie::getValue)
                .orElse(null);
    }
}
