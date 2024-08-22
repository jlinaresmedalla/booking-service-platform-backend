package com.hotelmanagement.bookingserviceplatformbackend.controller.auth;

import com.hotelmanagement.bookingserviceplatformbackend.dto.SignUpRequest;
import com.hotelmanagement.bookingserviceplatformbackend.dto.UserDto;
import com.hotelmanagement.bookingserviceplatformbackend.services.auth.AuthService;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUpUser(@RequestBody SignUpRequest signUpRequest) {
        try {
            UserDto createdUser = authService.createUser(signUpRequest);
            return new ResponseEntity <>(createdUser, HttpStatus.OK);
        } catch (EntityExistsException e) {
            return new ResponseEntity <>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        }catch (Exception e) {
            return new ResponseEntity <>("Something went wrong", HttpStatus.BAD_REQUEST);
        }
    }
}
