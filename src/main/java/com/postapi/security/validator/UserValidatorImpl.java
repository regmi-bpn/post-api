package com.postapi.security.validator;


import com.postapi.security.exception.RestException;
import com.postapi.constant.ErrorMessage;
import com.postapi.user.constants.RegistrationStatus;
import com.postapi.user.constants.UserType;
import com.postapi.user.entity.Users;
import com.postapi.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserValidatorImpl implements UserValidator {

    @Autowired
    UserRepository userRepository;

    @Override
    public Users validateUserByUserNameForRegistration(String email) {
        Optional<Users> validUser = userRepository.findByEmail(email);
        if (validUser.isPresent()) {
            Users users = validUser.get();
            if (!users.getRegistrationStatus().equalsIgnoreCase(RegistrationStatus.REGISTERED.name())) {
                return users;
            }
            throw new RestException(ErrorMessage.CLIENT_WITH_GIVEN_EMAIL_PRESENT);
        }
        return null;
    }

    @Override
    public Users validateUser(Long id, String userType) {
        if (!userType.equals(UserType.USER.name())) {
            throw new RestException(ErrorMessage.USER_TYPE_NOT_CLIENT);
        }
        return userRepository.findByUserId(id).orElseThrow(()-> new RestException(ErrorMessage.NOT_VALID_ID));
    }
}
