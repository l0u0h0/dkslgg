package com.ssafy.dksl.filter;

import com.ssafy.dksl.util.JwtUtil;
import com.ssafy.dksl.util.exception.TokenInvalidException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Component
    public class AuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public AuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtUtil.resolveToken(request);

        try {
            // permitAll일 경우 거치지 X
            if (request.getHeader("Authorization") != null && jwtUtil.validateToken(token)) {
                Authentication authentication = jwtUtil.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (TokenInvalidException e) {
            log.error(e.getMessage());
            errorResponse(response, e);
        }

    }

    private void errorResponse(HttpServletResponse response, TokenInvalidException e) throws IOException {
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        response.setContentType("application/json;charset=UTF-8");
        String errorMessage = e.getMessage();
        byte[] errorMessageBytes = errorMessage.getBytes(StandardCharsets.UTF_8);
        response.getOutputStream().write(errorMessageBytes);
    }
}
