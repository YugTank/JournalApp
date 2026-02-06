package com.example.journalApp.controller;

import com.example.journalApp.entity.User;
import com.example.journalApp.service.UserEntryService;
import com.example.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
    private UserEntryService userEntryService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<User> updateUserEntry(@RequestBody User user){
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        User userInDb=userEntryService.getUserByUsername(username);
        if(userInDb!=null && user.getPassword()!=null){
            userInDb.setPassword(passwordEncoder.encode(user.getPassword()));
            userEntryService.saveUserEntry(userInDb);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUserEntry(@RequestBody User user){
        String username=SecurityContextHolder.getContext().getAuthentication().getName();
        User userInDb=userEntryService.getUserByUsername(username);
        if(userInDb!=null){
            userEntryService.deleteUserbyName(username);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();

        String weather=" Weather feels like: "+weatherService.getWeather("Ahmedabad").getCurrent().getFeelslike()+" 'c";
        return new ResponseEntity<>("Hello, "+username+weather,HttpStatus.OK);
    }
}
