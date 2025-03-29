package com.learningSpringSecurity.LearnSpringSecurity.controller;

import com.learningSpringSecurity.LearnSpringSecurity.model.Customer;
import com.learningSpringSecurity.LearnSpringSecurity.model.JwtRequestDTO;
import com.learningSpringSecurity.LearnSpringSecurity.model.JwtResponseDTO;
import com.learningSpringSecurity.LearnSpringSecurity.repository.CustomerRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.learningSpringSecurity.LearnSpringSecurity.filter.JWTTokenGeneratorFilter.secretKey;

@RestController
public class LoginController {


    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomerRepository customerRepository;


    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(authentication.getName());
        return optionalCustomer.orElse(null);
    }

    @PostMapping("/apiLogin")
    public ResponseEntity<JwtResponseDTO> apiLogin(@RequestBody JwtRequestDTO jwtRequestDTO) {
        System.out.println("LoginAttempt: "+jwtRequestDTO.getUsername()+" "+jwtRequestDTO.getPassword());


        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(jwtRequestDTO.getUsername(),
                jwtRequestDTO.getPassword());

        Authentication authenticationResponse = authenticationManager.authenticate(authentication);

        String Jwt = "";
        if (authenticationResponse != null && authenticationResponse.isAuthenticated()) {
            SecretKey secretKey3 = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
            if (secretKey3 != null) {
                Jwt = Jwts.builder()
                        .issuer("Raj")
                        .subject("JWTToken")
                        .claim("username", authenticationResponse.getName())
                        .claim("authorities", authenticationResponse.getAuthorities().stream().
                                map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new Date())
                        .expiration(new Date((new Date().getTime()) + 30000000))
                        .signWith(secretKey3)
                        .compact();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).header("Authorization", Jwt).
                body(new JwtResponseDTO(HttpStatus.OK.getReasonPhrase(),Jwt));
    }
}