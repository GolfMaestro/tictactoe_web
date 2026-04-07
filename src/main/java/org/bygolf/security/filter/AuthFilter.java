package org.bygolf.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bygolf.domain.service.UserServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class AuthFilter extends GenericFilterBean {

    private final UserServiceImpl userServiceImpl;

    public AuthFilter(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI();

        if (path.equals("/signup") || path.equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String base64UserInfo = authHeader.substring(6);

        byte[] decodedBytes = Base64.getDecoder().decode(base64UserInfo);
        String userInfo = new String(decodedBytes, StandardCharsets.UTF_8);

        String[] parts = userInfo.split(":", 2);

        if (parts.length != 2) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String login = parts[0];
        String password = parts[1];

        try {
            UUID userId = userServiceImpl.login(login, password);
            if (userId != null) {
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        login,
                        null,
                        List.of(new SimpleGrantedAuthority("Player"))
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
