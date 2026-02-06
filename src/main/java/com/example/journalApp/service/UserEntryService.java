package com.example.journalApp.service;

import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserEntryService {

    @Autowired
    private UserEntryRepository userEntryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        List<User> all=userEntryRepository.findAll();
        return all;
    }
    public void saveUserEntry(User user) {
        userEntryRepository.save(user);
    }
    public boolean registerUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userEntryRepository.save(user);
            return true;
        }
        catch (Exception e) {
            log.info("Error registering user for username: {}: {}",user.getUsername(),e.getMessage());
            return false;
        }
    }

    public Optional<User> getUserById(ObjectId id) {
        return userEntryRepository.findById(id);
    }

    public void deleteUserbyName(String username) {
        userEntryRepository.deleteByUsername(username);
    }

    public User  getUserByUsername(String username) {
        return userEntryRepository.findByUsername(username);
    }

    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("ADMIN","USER"));
        userEntryRepository.save(user);
    }
}
