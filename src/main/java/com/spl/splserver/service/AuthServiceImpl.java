package com.spl.splserver.service;

import com.spl.splserver.entity.User;
import com.spl.splserver.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{
    final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User signIn(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    @Override
    public User signUp(String username, String password) {
        if(userRepository.findUserByUsername(username) == null) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            return userRepository.save(user);
        }
        return null;
    }
}
