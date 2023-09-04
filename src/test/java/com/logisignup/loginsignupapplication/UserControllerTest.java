package com.logisignup.loginsignupapplication;

import com.logisignup.loginsignupapplication.model.User;
import com.logisignup.loginsignupapplication.repository.RoleRepository;
import com.logisignup.loginsignupapplication.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void testRegistration() throws Exception {

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");

        mockMvc.perform(post("/registration")
                        .param("username", user.getUsername())
                        .param("password", user.getPassword()))
                .andExpect(status().is3xxRedirection());


        User registeredUser = userRepository.findByUsername("testuser");
        Assert.assertNotNull(registeredUser);
        Assert.assertEquals("testuser", registeredUser.getUsername());
    }

    @Test
    public void testLogin() throws Exception {

        User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        userRepository.save(user);

        mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin("/login")
                        .user("testuser")
                        .password("password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));


    }
}
