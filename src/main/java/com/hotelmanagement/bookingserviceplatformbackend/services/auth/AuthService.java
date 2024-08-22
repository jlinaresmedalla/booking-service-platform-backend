package com.hotelmanagement.bookingserviceplatformbackend.services.auth;

import com.hotelmanagement.bookingserviceplatformbackend.dto.SignUpRequest;
import com.hotelmanagement.bookingserviceplatformbackend.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignUpRequest signUpRequest);
}
