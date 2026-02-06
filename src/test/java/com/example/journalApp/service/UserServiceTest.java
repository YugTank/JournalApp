package com.example.journalApp.service;

import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Test
    @Disabled
    public void testFindByUsername() {
        User user = userEntryRepository.findByUsername("yug");
        assertNotNull(user);
        assertTrue(user.getRoles().contains("ADMIN"));
    }

    @Disabled
    @ParameterizedTest
    @CsvSource({
            "5,2,3",
            "10,9,1"
    })
    public void test(int expected,int a,int b){
        assertEquals(expected,a+b);
    }
}
