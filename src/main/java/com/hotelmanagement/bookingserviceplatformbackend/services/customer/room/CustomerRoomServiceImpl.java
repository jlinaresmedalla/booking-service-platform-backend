package com.hotelmanagement.bookingserviceplatformbackend.services.customer.room;

import com.hotelmanagement.bookingserviceplatformbackend.dto.RoomsResponseDto;
import com.hotelmanagement.bookingserviceplatformbackend.entity.Room;
import com.hotelmanagement.bookingserviceplatformbackend.repository.RoomsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerRoomServiceImpl {
    private final RoomsRepository roomsRepository;

    public RoomsResponseDto getAvailableRooms(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 6);
        Page<Room> roomPage = roomsRepository.findByAvailable(true, pageable);

        RoomsResponseDto roomsResponseDto = new RoomsResponseDto();
        roomsResponseDto.setPageNumber(roomPage.getPageable().getPageNumber());
        roomsResponseDto.setTotalPages(roomPage.getTotalPages());
        roomsResponseDto.setRoomDtoList(roomPage.stream().map(Room::getRoomDto).collect(Collectors.toList()));

        return roomsResponseDto;
    }


}
