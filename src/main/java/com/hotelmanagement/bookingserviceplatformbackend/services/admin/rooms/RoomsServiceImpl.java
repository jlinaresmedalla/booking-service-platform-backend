package com.hotelmanagement.bookingserviceplatformbackend.services.admin.rooms;

import com.hotelmanagement.bookingserviceplatformbackend.dto.RoomDto;
import com.hotelmanagement.bookingserviceplatformbackend.dto.RoomsResponseDto;
import com.hotelmanagement.bookingserviceplatformbackend.entity.Room;
import com.hotelmanagement.bookingserviceplatformbackend.repository.RoomsRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

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

    public RoomsResponseDto getAllRooms(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Room> roomPage = roomsRepository.findAll(pageable);

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
        roomsResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
        roomsResponseDto.setTotalPages(roomPage.getTotalPages());
        roomsResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));

        return roomsResponseDto;
    }

    public RoomDto getRoomById(Long id){
        Optional<Room> optionalRoom = roomsRepository.findById(id);
        if (optionalRoom.isPresent()){
            return optionalRoom.get().getRoomDto();
        }else {
            throw new EntityNotFoundException("Room not found");
        }
    }

    public boolean updateRoom(Long id,RoomDto roomDto){
        Optional<Room> optionalRoom = roomsRepository.findById(id);
        if (optionalRoom.isPresent()){
            Room room = optionalRoom.get();
            room.setName(roomDto.getName());
            room.setType(roomDto.getType());
            room.setPrice(roomDto.getPrice());

            roomsRepository.save(room);
            return true;
        }else {
            return false;
        }
    }

    public void deleteRoom(Long id){
        Optional<Room> optionalRoom = roomsRepository.findById(id);
        if (optionalRoom.isPresent()){
            roomsRepository.deleteById(id);
        }else {
            throw new EntityNotFoundException("Room not found");
        }
    }
}
