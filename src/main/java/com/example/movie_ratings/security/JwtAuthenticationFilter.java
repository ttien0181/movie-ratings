package com.example.movie_ratings.security;

import com.example.movie_ratings.service.impl.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        final String path = request.getServletPath();
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        log.debug("🔹 Incoming request: {} {}", request.getMethod(), path);
        log.debug("🔹 Authorization header: {}", authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.debug("❌ No JWT token found in header, skipping filter.");
            chain.doFilter(request, response);
            return;
        }

        final String jwt = authHeader.substring(7);
        String email = null;

        try {
            email = jwtUtil.extractUsername(jwt);
            log.debug("✅ Extracted email from JWT: {}", email);
        } catch (Exception e) {
            log.error("❌ Failed to extract email from token: {}", e.getMessage());
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            try {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                log.debug("✅ Loaded user details: {}, roles: {}", userDetails.getUsername(), userDetails.getAuthorities());

                if (jwtUtil.isTokenValid(jwt, userDetails)) {
                    log.debug("✅ JWT is valid for user: {}", email);
                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                } else {
                    log.warn("⚠️ JWT token invalid for user: {}", email);
                }
            } catch (Exception ex) {
                log.error("❌ Authentication failed: {}", ex.getMessage());
            }
        } else {
            log.debug("ℹ️ Email is null or SecurityContext already has authentication.");
        }

        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        boolean skip = path.startsWith("/api/auth/");
        if (skip) {
            log.debug("⏩ Skipping JWT filter for path: {}", path);
        }
        return skip;
    }
}
