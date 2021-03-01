package com.spl.splserver.service;

import com.spl.splserver.POJO.UserRole;
import com.spl.splserver.entity.QuestionSet;
import com.spl.splserver.repository.QuestionSetRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    final QuestionSetRepository questionSetRepository;

    public UserServiceImpl(QuestionSetRepository questionSetRepository) {
        this.questionSetRepository = questionSetRepository;
    }

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

    @Override
    public List<QuestionSet> getAllQuestionSetId(String userId) {
        return questionSetRepository.findByOwnerId(userId);
    }
}
