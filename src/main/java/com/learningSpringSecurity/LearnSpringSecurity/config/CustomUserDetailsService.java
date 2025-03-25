package com.learningSpringSecurity.LearnSpringSecurity.config;

import com.learningSpringSecurity.LearnSpringSecurity.model.Authority;
import com.learningSpringSecurity.LearnSpringSecurity.model.Customer;
import com.learningSpringSecurity.LearnSpringSecurity.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.task.TaskExecutionProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found with the given mail id"));



//        List<GrantedAuthority>authorities = new ArrayList<>();
//
//        for(Authority auth: customer.getAuthorities()){
//            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(auth.getName());
//            authorities.add(simpleGrantedAuthority);
//        }

        List<GrantedAuthority> authorities =customer.getAuthorities().stream().map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());

        return new User(customer.getEmail(), customer.getPassword(), authorities);
    }
}
