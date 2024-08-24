package com.hotelmanagement.bookingserviceplatformbackend.services.customer.room;

import com.hotelmanagement.bookingserviceplatformbackend.dto.RoomsResponseDto;

public interface CustomerRoomService {
    RoomsResponseDto getAvailableRooms(int pageNumber);
}
