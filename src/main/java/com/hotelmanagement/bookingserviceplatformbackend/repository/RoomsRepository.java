package com.hotelmanagement.bookingserviceplatformbackend.repository;

import com.hotelmanagement.bookingserviceplatformbackend.entity.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomsRepository extends JpaRepository<Room, Long> {
    Page<Room> findByAvailable(boolean available, Pageable pageable);
}
