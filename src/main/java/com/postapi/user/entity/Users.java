package com.postapi.user.entity;


import com.postapi.user.constants.RegistrationStatus;
import com.postapi.user.constants.UserType;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class  Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_name", length = 1000)
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "confirm_password")
    private String confirmPassword;

    @Column(name = "otp", length = 6)
    private String otp;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "registration_status")
    private String registrationStatus= RegistrationStatus.OTP_PENDING.name();

    @Enumerated(value = EnumType.STRING)
    private UserType userType;
}
