package com.postapi.security.validator;


import com.postapi.user.entity.Users;

public interface UserValidator {
    Users validateUserByUserNameForRegistration(String email);

    Users validateUser(Long id, String userType);
}
