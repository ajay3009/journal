package com.apandit.journalApp.service;

import com.apandit.journalApp.repository.UserRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTests {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    void loadUserByUsernameTest() {
        when(userRepository.findByUserName()).thenReturn()
        UserDetails user = userDetailsService.loadUserByUsername("ram");
    }
}
