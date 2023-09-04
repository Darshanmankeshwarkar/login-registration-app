package com.logisignup.loginsignupapplication.service;

import com.logisignup.loginsignupapplication.model.User;

public interface UserService {
    void save(User user);
    User findByUsername(String username);
}