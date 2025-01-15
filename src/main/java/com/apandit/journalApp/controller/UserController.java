package com.apandit.journalApp.controller;

import com.apandit.journalApp.entity.User;
import com.apandit.journalApp.service.JournalEntryService;
import com.apandit.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveNewEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName) {
        User userDb = userService.findByUserName(userName);
        if (userDb != null) {
            userDb.setUserName(user.getUserName());
            userDb.setPassword(user.getPassword());
            userService.saveEntry(userDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
