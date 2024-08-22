package com.hotelmanagement.bookingserviceplatformbackend.services.auth;

import com.hotelmanagement.bookingserviceplatformbackend.dto.SignUpRequest;
import com.hotelmanagement.bookingserviceplatformbackend.dto.UserDto;
import com.hotelmanagement.bookingserviceplatformbackend.entity.User;
import com.hotelmanagement.bookingserviceplatformbackend.enums.UserRole;
import com.hotelmanagement.bookingserviceplatformbackend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    @PostConstruct
    public void createAnAdminAccount() {
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if (adminAccount.isEmpty()) {
            User admin = new User();
            admin.setEmail("admin@test.com");
            admin.setName("admin");
            admin.setUserRole(UserRole.ADMIN);
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(admin);
        }else {
            System.out.println("Admin account already exists");
        }
    }

    public UserDto createUser(SignUpRequest signUpRequest) {
        if (userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists" + signUpRequest.getEmail());
        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        User createdUser =userRepository.save(user);
        return createdUser.getUserDto();
    }
}
