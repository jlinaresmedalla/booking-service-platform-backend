package com.hotelmanagement.bookingserviceplatformbackend.repository;

import com.hotelmanagement.bookingserviceplatformbackend.entity.User;
import com.hotelmanagement.bookingserviceplatformbackend.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUserRole(UserRole userRole);
}
