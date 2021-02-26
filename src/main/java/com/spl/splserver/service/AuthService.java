package com.spl.splserver.service;

import com.spl.splserver.entity.User;

public interface AuthService {
    User signIn(String username, String password);
    User signUp(String username, String password);
}
