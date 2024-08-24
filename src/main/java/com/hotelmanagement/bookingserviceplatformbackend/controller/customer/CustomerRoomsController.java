package com.hotelmanagement.bookingserviceplatformbackend.controller.customer;

import com.hotelmanagement.bookingserviceplatformbackend.services.admin.rooms.RoomsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerRoomsController {

    private final RoomsService  roomsService;

    @GetMapping("/rooms/{pageNumber}")
    public ResponseEntity<?> getAvailableRooms(@PathVariable int pageNumber){
        return ResponseEntity.ok(roomsService.getAllRooms(pageNumber));
    }
}
