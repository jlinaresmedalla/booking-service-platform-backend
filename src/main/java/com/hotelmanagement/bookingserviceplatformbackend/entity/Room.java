package com.hotelmanagement.bookingserviceplatformbackend.entity;

import com.hotelmanagement.bookingserviceplatformbackend.dto.RoomDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private Long price;
    private boolean available;

    public RoomDto getRoomDto(){
        RoomDto roomDto = new RoomDto();
        roomDto.setId(this.id);
        roomDto.setName(this.name);
        roomDto.setType(this.type);
        roomDto.setPrice(this.price);
        roomDto.setAvailable(this.available);
        return roomDto;
    }
}
