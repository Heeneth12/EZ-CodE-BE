package com.ezcode.auth.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(JwtAuthenticationFilter.class);

        String header = request.getHeader("Authorization");
        String token = null;
        String email = null;

        // Extract token from header
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
            log.debug("JWT Token found in header: {}", token);
        }

        // Validate and extract email
        if (token != null && jwtTokenUtil.validateToken(token)) {
            email = jwtTokenUtil.getUsernameFromToken(token);
            log.debug("Token is valid. Extracted email: {}", email);

            // Check if already authenticated
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(email, null, null);
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
               log.debug("Security context updated with authenticated user: {}", email);
            }
        } else {
            if (token != null) {
                log.warn("Invalid JWT token.");
            }
        }

        filterChain.doFilter(request, response);
    }
}
