package com.apandit.journalApp.service;

import com.apandit.journalApp.entity.User;
import com.apandit.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Configuration
@Disabled
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @ParameterizedTest
//    @EnumSource
    @ArgumentsSource(UserArgumentsProvider.class)
//    @ValueSource(strings = {
//            "ajay",
//            "vijay",
//            "ajay1"
//    })
    public  void  testFindByUserName(User user) {


//        assertEquals(4, 2+2);
//        assertNotNull(userRepository.findByUserName(userName), "Message failed for" );
            assertTrue(userService.saveNewEntry(user));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2, 10, 12",
            "3,3,7"
    })
    public void test(int a, int b, int expected) {
        assertEquals(expected, a+b);
    }
}
