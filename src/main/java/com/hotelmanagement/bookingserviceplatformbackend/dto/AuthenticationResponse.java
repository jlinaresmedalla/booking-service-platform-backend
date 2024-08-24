package com.hotelmanagement.bookingserviceplatformbackend.dto;

import com.hotelmanagement.bookingserviceplatformbackend.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;
    private Long userId;
    private UserRole userRole;
}
