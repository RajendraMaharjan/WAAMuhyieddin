package edu.miu.waalab.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.waalab.utilities.JwtUtil;
import io.jsonwebtoken.*;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    public JwtFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            var token = extractTokenFromRequest(request);
            if (token != null && jwtUtil.validateToken(token)) {
                SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(token));
            }

            //if access token is expired
            else {
//                String redirecstURL = "https://" + request.getServerName() + request.getRequestURI();
//                response.sendRedirect(redirecstURL);

                Cookie[] cookies = request.getCookies();
                if (cookies == null) {
                    request.logout();
                }
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("refreshToken")) {
                        String refreshToken = cookie.getValue();
                        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshToken);

                        if (isRefreshTokenValid) {
                            var accessToken = jwtUtil.generateRefreshToken(jwtUtil.getSubject(refreshToken));
                            System.out.println("***************************************");
                            System.out.println("TOKEN REFRESHED as =>" + refreshToken);
                            System.out.println("***************************************");

                            SecurityContextHolder.getContext().setAuthentication(jwtUtil.getAuthentication(accessToken));
                        } else {
                            System.out.println("***************************************");
                            System.out.println("REFRESH TOKEN IS INVALID.");
                            System.out.println("***************************************");
                            request.logout();
                        }
                    } else {
                        request.logout();
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * Helper method
     *
     * @param request
     * @return
     */
    public String extractTokenFromRequest(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            var token = authorizationHeader.substring(7);
            return token;
        }
        return null;
    }
}
