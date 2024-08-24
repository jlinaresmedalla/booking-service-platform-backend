package com.hotelmanagement.bookingserviceplatformbackend.services.admin.rooms;

import com.hotelmanagement.bookingserviceplatformbackend.dto.RoomDto;

public interface RoomsService {
    boolean postRoom(RoomDto roomDto);
}
