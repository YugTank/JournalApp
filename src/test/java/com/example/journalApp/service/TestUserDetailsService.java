package com.example.journalApp.service;

import com.example.journalApp.entity.User;
import com.example.journalApp.repository.UserEntryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestUserDetailsService {

    @InjectMocks
    private UserDetailsServiceImp userDetailsServiceImp;

    @Mock
    private UserEntryRepository userEntryRepository;

    @Disabled
    @Test
    void loadUserByUsernameTest(){
        User mockUser=new User("ram","pass");
        mockUser.setRoles(List.of("USER"));

        when(userEntryRepository.findByUsername("ram")).thenReturn(mockUser);

        UserDetails userDetails = userDetailsServiceImp.loadUserByUsername("ram");
        Assertions.assertNotNull(userDetails);
    }
}
