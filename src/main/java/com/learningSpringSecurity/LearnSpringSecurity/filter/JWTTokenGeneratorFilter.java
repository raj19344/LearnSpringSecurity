package com.learningSpringSecurity.LearnSpringSecurity.filter;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

    public static String secretKey="fjdsghjsfhgduyfvbfdhgakdfbdyladsdjkfdsbkfjsh";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(Objects.nonNull(authentication)){

                    SecretKey secretKey1 = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
                    String jwt = Jwts.builder()
                            .issuer("Preetish")
                            .subject("JWT Token")
                            .claim("username", authentication.getName())
                            .claim("authorities", authentication.getAuthorities().stream().map(
                            GrantedAuthority::getAuthority).collect(Collectors.joining("-1")))
                            .issuedAt(new Date())
                            .expiration(new Date((new Date().getTime()) + 30000000))
                            .signWith(secretKey1).compact();

                    response.setHeader("Authorization",jwt);
                }
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/apiLogin");
    }
}
