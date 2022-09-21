package com.postapi.security.service;

import com.postapi.user.entity.Users;
import com.postapi.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmail(email);
        if (user == null){
            throw new UsernameNotFoundException("Email" +email + "not found.");
        }
        return new User(user.get().getEmail(), user.get().getPassword(), getGrantedAuthority(user));

    }


    private Collection<GrantedAuthority> getGrantedAuthority(Optional<Users> users){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (users.get().getUserType().name().equalsIgnoreCase("ADMIN")){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
}
