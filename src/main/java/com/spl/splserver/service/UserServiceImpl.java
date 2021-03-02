package com.spl.splserver.service;

import com.spl.splserver.POJO.UserRole;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public boolean updateProfile(String userId, HashMap<String, String> updatedFields) {
        return false;
    }

    @Override
    public String updatePassword(String userId, String password) {
        return null;
    }

    @Override
    public String updateRole(String userId, UserRole updatedRole) {
        return null;
    }
}
