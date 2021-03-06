package com.spl.splserver.service;

import com.spl.splserver.POJO.UserRole;
import java.util.HashMap;

/*
    User

    All available operations to modify users
 */
public interface UserService {
    boolean updateProfile(String userId, HashMap<String, String> updatedFields);
    String updatePassword(String userId, String password);
    String updateRole(String userId, UserRole updatedRole);
}
