package com.postapi.user.service.impl;


import com.postapi.context.ContextHolderService;
import com.postapi.security.exception.RestException;
import com.postapi.user.constants.UserType;
import com.postapi.user.entity.Users;
import com.postapi.user.repository.UserRepository;
import com.postapi.user.service.UserService;
import com.postapi.security.validator.UserValidator;
import com.postapi.user.dto.*;
import com.postapi.security.util.HelperUtil;
import com.postapi.security.util.JwtTokenUtil;
import com.postapi.security.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.postapi.constant.ErrorMessage.*;
import static com.postapi.user.constants.RegistrationStatus.*;


@Slf4j
@Service
public class UserImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private ContextHolderService contextHolderService;

    @Override
    public RegisterResponse registerEmail(RegisterEmailRequest request) {
        Users validUsers = userValidator.validateUserByUserNameForRegistration(request.getEmail());
        validateConfirmNewPasswordMismatch(request.getPassword(), request.getConfirmPassword());
        if (validUsers != null) {
            return sendEmailOTPAndGetResponse(validUsers);
        }
        Users users = prepareToAddUser(request);
        return sendEmailOTPAndGetResponse(users);
    }

    @Override
    public RegisterResponse resendOtp(ResendOtpRequest request) {
        Optional<Users> valUser = userRepository.findByEmailForOtp(request.getUsername());
        Users userDetails = valUser.orElseThrow(() -> new RestException(NOT_VALID_USER_NAME));
        return sendEmailOTPAndGetResponse(userDetails);

    }

    @Override
    public ValidateOtpResponse validateOtp(ValidateOtpRequest request) {
        Optional<Users> valUsers = userRepository.findByUserId(request.getId());
        if (valUsers.isPresent()) {
            Users users = valUsers.get();
            return validateUserOtp(request, users);
        }
        throw new RestException(NOT_VALID_ID);
    }

    @Override
    public AddInformationResponse<Long> addInformation(AddInformationRequest request) {
        Users users = userValidator.validateUser(contextHolderService.getContext().getUserId(), contextHolderService.getContext().getUserType());
        validateClientCanAddInformation(users);
        userRepository.save(prepareToAddClientInformation(request, users));
        return new AddInformationResponse<>("Information added successfully", users.getId());
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Optional<Users> valUser = userRepository.loginRequest(request.getUsername(), SecurityUtil.encode(request.getPassword()));
        if (valUser.isEmpty()) throw new RestException(INVALID_LOGIN_CREDENTIAL);
        return loginResponse(valUser.get());
    }

    @Override
    public UserInformationResponse updateUserInformation(UpdateInformationRequest request) {
        Users users = userValidator.validateUser(contextHolderService.getContext().getUserId(), contextHolderService.getContext().getUserType());
        users.setFirstName(request.getFirstName());
        users.setLastName(request.getLastName());
        users.setAddress(request.getAddress());
        users.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(users);
        return UserInformationResponse.builder().id(users.getId()).username(users.getUsername()).address(users.getAddress()).phoneNumber(users.getPhoneNumber()).build();
    }

    @Override
    public UserInformationResponse getUserInformation() {
        Users users = userValidator.validateUser(contextHolderService.getContext().getUserId(), contextHolderService.getContext().getUserType());
        return UserInformationResponse.builder().id(users.getId()).username(users.getUsername()).address(users.getAddress()).phoneNumber(users.getPhoneNumber()).build();
    }

    private LoginResponse loginResponse(Users users) {
        return LoginResponse.builder().token(getToken(users)).userInformation(prepareClientInformationResponse(users)).build();
    }

    public static UserInformationResponse prepareClientInformationResponse(Users users) {
        return UserInformationResponse.builder().id(users.getId()).username(users.getUsername()).address(users.getAddress()).phoneNumber(users.getPhoneNumber()).build();
    }

    @Override
    public UpdateAdminRequest addAsAdmin(Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!!"));
        users.setId(userId);
        users.setUserType(UserType.ADMIN);
        userRepository.save(users);
        return UpdateAdminRequest.builder().id(userId).roles("ADMIN").build();
    }

    @Override
    public UpdateAdminRequest addAsUser(Long userId) {
        Users users = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found!!"));
        users.setId(userId);
        users.setUserType(UserType.USER);
        userRepository.save(users);
        return UpdateAdminRequest.builder().id(userId).roles("USER").build();
    }


    private Users prepareToAddClientInformation(AddInformationRequest request, Users users) {
        users.setFirstName(request.getFirstName());
        users.setLastName(request.getLastName());
        users.setAddress(request.getAddress());
        users.setPhoneNumber(request.getPhoneNumber());
        users.setRegistrationStatus(REGISTERED.name());
        return users;
    }

    private void validateClientCanAddInformation(Users users) {
        if (!users.getRegistrationStatus().equalsIgnoreCase(ADD_INFO_PENDING.name())) {
            throw new RestException(USER_ALREADY_ADDED_INFORMATION);
        }
    }

    private void validateConfirmNewPasswordMismatch(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            throw new RestException(PASSWORD_CONFIRM_PASSWORD_DOES_NOT_MATCH);
        }
    }

    private ValidateOtpResponse validateUserOtp(ValidateOtpRequest request, Users users) {
        if (users.getOtp().equals(request.getOtp())) {
            users.setOtp("");
            if (users.getRegistrationStatus().equalsIgnoreCase(OTP_PENDING.name())) {
                users.setRegistrationStatus(ADD_INFO_PENDING.name());
            }

            userRepository.save(users);
            final String token = getTokenForValidation(users);
            return ValidateOtpResponse.builder().id(users.getId()).accessToken(token).build();
        } else {
            throw new RestException(OTP_INCORRECT, "Incorrect Otp");
        }
    }

    private String getToken(Users users) {
        if (users.getUserType().name().equals("ADMIN")){
            return jwtTokenUtil.generateToken(prepareClaims(users.getUsername(), users.getId(), UserType.ADMIN.name()));
        }
        return jwtTokenUtil.generateToken(prepareClaims(users.getUsername(), users.getId(), UserType.USER.name()));

    }

    private String getTokenForValidation(Users users){
        return jwtTokenUtil.generateToken(prepareClaims(users.getUsername(), users.getId(), UserType.USER.name()));

    }

    private Map<String, Object> prepareClaims(String username, Long id, String userType) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("username", username);
        claims.put("userType", userType);
        return claims;
    }

    private Users prepareToAddUser(RegisterEmailRequest request) {
        Users users = new Users();
        users.setEmail(request.getEmail());
        users.setUsername(request.getEmail());
        users.setPassword(SecurityUtil.encode(request.getPassword()));
        users.setConfirmPassword(SecurityUtil.encode(request.getConfirmPassword()));
        users.setUserType(UserType.USER);
        return users;
    }

    private RegisterResponse sendEmailOTPAndGetResponse(Users users) {
        users.setOtp(HelperUtil.generateNumeric(6));
        String message = "Dear sir/ma'am, \n\tYour Facebook's conformation code is ".concat(users.getOtp()).concat(". \nRegards,\nFacebook It team.");
        Users response = userRepository.save(users);
        return getRegisterResponse(response);
    }

    private RegisterResponse getRegisterResponse(Users users) {
        return RegisterResponse.builder().id(users.getId()).otp(users.getOtp()).username(users.getUsername()).message("Otp has been sent.").build();
    }
}
