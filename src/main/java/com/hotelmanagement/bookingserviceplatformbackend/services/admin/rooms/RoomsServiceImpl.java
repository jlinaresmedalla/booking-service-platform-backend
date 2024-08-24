package com.hotelmanagement.bookingserviceplatformbackend.services.admin.rooms;

import com.hotelmanagement.bookingserviceplatformbackend.dto.RoomDto;
import com.hotelmanagement.bookingserviceplatformbackend.entity.Room;
import com.hotelmanagement.bookingserviceplatformbackend.repository.RoomsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomsServiceImpl implements RoomsService {
    private final RoomsRepository roomsRepository;

    public boolean postRoom(RoomDto roomDto) {
        try {
            Room room = new Room();
            room.setName(roomDto.getName());
            room.setType(roomDto.getType());
            room.setPrice(roomDto.getPrice());
            room.setAvailable(true);

            roomsRepository.save(room);
            return true;
        }catch (Exception e){
            return false;
        }
    }

}
