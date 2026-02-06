package com.example.journalApp.repository;

import com.example.journalApp.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserRepositoryImplTest {

    @Autowired
    private UserRepositoryImpl userRepositoryImp;

    @Test
    public void getUserForSATest() {
        List<User> users=userRepositoryImp.getUsersForSA();
        Assertions.assertNotNull(users);
    }
}
