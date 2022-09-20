package com.postapi.user.controller;


import com.postapi.user.service.UserService;
import com.postapi.user.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.postapi.constant.Route.*;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = USER_RESISTER_EMAIL)
    public RegisterResponse registerEmail(@Valid @RequestBody RegisterEmailRequest request) {
        log.info("User Register request:: {}", request);
        return userService.registerEmail(request);
    }

    @PostMapping(value = RESEND_OTP)
    public RegisterResponse resendOtp(@Valid @RequestBody ResendOtpRequest request) {
        log.info("resend otp:: {}", request);
        return userService.resendOtp(request);
    }

    @PostMapping(value = VALIDATE_OTP)
    public ValidateOtpResponse validateOtp(@Valid @RequestBody ValidateOtpRequest request) {
        log.info("Validate user otp:: {}", request);
        return userService.validateOtp(request);
    }

    @PatchMapping(value = SET_ADMIN)
    public UpdateAdminRequest addAsAdmin(@PathVariable("userId") Long userId) {
        return userService.addAsAdmin(userId);
    }

    @PatchMapping(value = SET_USER)
    public UpdateAdminRequest addAsUser(@PathVariable("userId") Long userId) {
        return userService.addAsUser(userId);
    }

    @PostMapping(value = ADD_INFORMATION)
    public AddInformationResponse<Long> addInformation(@Valid @RequestBody AddInformationRequest request) {
        System.out.println(request);
        log.info("Add user information :: {}", request);
        return userService.addInformation(request);
    }

    @PostMapping(value = LOGIN)
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        log.info("user login :: {}", request);
        return userService.login(request);
    }

    @PutMapping(value = INFORMATION)
    public UserInformationResponse updateUserInformation(@Valid @RequestBody UpdateInformationRequest request) {
        log.info("Edit user information :: {}", request);
        return userService.updateUserInformation(request);
    }

    @GetMapping(value = INFORMATION)
    public UserInformationResponse getUserInformation() {
        log.info("Get user information");
        return userService.getUserInformation();

    }

}
