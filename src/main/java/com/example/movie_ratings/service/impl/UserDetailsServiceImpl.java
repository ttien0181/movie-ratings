package com.example.movie_ratings.service.impl;

import com.example.movie_ratings.entity.impl.UserDetailsImpl;
import com.example.movie_ratings.repository.UserRepository;
import org.hibernate.dialect.function.array.OracleArrayGetFunction;
import org.hibernate.grammars.ordering.OrderingParser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Load user by email: " + email);
        return userRepository.findByEmail(email)
                .map(user -> {
                    System.out.println("Found user: " + user.getUsername() + ", role: " + user.getRole());
                    var authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
                    return new org.springframework.security.core.userdetails.User(
                            user.getEmail(),
                            user.getPassword(),
                            authorities
                    );
                })
                .orElseThrow(
                        () -> new UsernameNotFoundException("Not found: " + email)
                );
    }
}
