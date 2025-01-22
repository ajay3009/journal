package com.apandit.journalApp.service;

import com.apandit.journalApp.entity.User;
import com.apandit.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
            User user = userRepository.findByUserName(userName);
            if (user != null) {
                UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(user.getUserName())
                        .password(user.getPassword())
                        .roles(user.getRoles().toArray(new String[0]))
                        .build();
                return userDetails;
            }
            throw new UsernameNotFoundException("User not found with username: " + userName);
    }


}
