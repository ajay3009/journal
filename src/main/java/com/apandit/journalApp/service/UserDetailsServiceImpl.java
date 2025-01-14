package com.apandit.journalApp.service;

import com.apandit.journalApp.entity.User;
import com.apandit.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public abstract class UserDetailsServiceImpl implements UserDetails {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUserName(String userName) throws UsernameNotFoundException {
            User user = userRepository.findByUserName(userName);
            if (user != null) {
                UserDetails userDetails = org.springframework.security.core.userdetails.User.builder().username(user.getUserName())
                        .password(user.getPassword())
                        .roles(user.getRoles().toArray(new String[0]))
                        .build();
                return userDetails;
            }
            return null;
    }
}
