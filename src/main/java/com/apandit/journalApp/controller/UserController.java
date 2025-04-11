package com.apandit.journalApp.controller;

import com.apandit.journalApp.api.response.WeatherResponse;
import com.apandit.journalApp.entity.User;
import com.apandit.journalApp.repository.UserRepository;
import com.apandit.journalApp.service.UserService;
import com.apandit.journalApp.service.WeatherAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherAPIService weatherAPIService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userDb = userService.findByUserName(userName);
        userDb.setPassword(user.getPassword());
        userDb.setUserName(user.getUserName());
        userService.saveEntry(userDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String greetings = "";
        WeatherResponse weather = weatherAPIService.getWeather("New York");
        if (weather != null) {
            greetings = " weather feels like almost at " + weather.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>( "Hi there " + authentication.getName() + greetings , HttpStatus.OK);
    }
}
