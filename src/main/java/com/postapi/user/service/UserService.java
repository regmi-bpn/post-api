package com.postapi.user.service;


import com.postapi.user.dto.*;

public interface UserService {
    RegisterResponse registerEmail(RegisterEmailRequest request);

    RegisterResponse resendOtp(ResendOtpRequest request);

    ValidateOtpResponse validateOtp(ValidateOtpRequest request);

    AddInformationResponse<Long> addInformation(AddInformationRequest request);

    LoginResponse login(LoginRequest request);

    UserInformationResponse updateUserInformation(UpdateInformationRequest request);

    UserInformationResponse getUserInformation();

    UpdateAdminRequest addAsAdmin(Long userId);
    UpdateAdminRequest addAsUser(Long userId);
}
