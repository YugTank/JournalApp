package com.example.journalApp.service;

import com.example.journalApp.scheduler.UserScheduler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserScheduler  userScheduler;

    @Test
    @Disabled
    public void testEmailService() {
        emailService.sendEmail("muquanta36@gmail.com","Test mail","Hello");
    }

    @Test
    @Disabled
    public void testSAEmailService() {
        userScheduler.fetchAndSendEmail();
    }
}
