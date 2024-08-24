package com.hotelmanagement.bookingserviceplatformbackend.services.admin.rooms;

import com.hotelmanagement.bookingserviceplatformbackend.dto.RoomDto;
import com.hotelmanagement.bookingserviceplatformbackend.dto.RoomsResponseDto;

public interface RoomsService {
    boolean postRoom(RoomDto roomDto);
    RoomsResponseDto getAllRooms(int pageNumber);
    RoomDto getRoomById(Long id);
    boolean updateRoom(Long id, RoomDto roomDto);
    void deleteRoom(Long id);
}
