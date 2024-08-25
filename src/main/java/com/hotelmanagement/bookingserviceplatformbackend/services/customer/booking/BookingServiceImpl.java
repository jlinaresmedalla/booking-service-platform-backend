package com.hotelmanagement.bookingserviceplatformbackend.services.customer.booking;

import com.hotelmanagement.bookingserviceplatformbackend.dto.ReservationDto;
import com.hotelmanagement.bookingserviceplatformbackend.entity.Reservation;
import com.hotelmanagement.bookingserviceplatformbackend.entity.Room;
import com.hotelmanagement.bookingserviceplatformbackend.entity.User;
import com.hotelmanagement.bookingserviceplatformbackend.enums.ReservationStatus;
import com.hotelmanagement.bookingserviceplatformbackend.repository.ReservationRepository;
import com.hotelmanagement.bookingserviceplatformbackend.repository.RoomsRepository;
import com.hotelmanagement.bookingserviceplatformbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {
    private final UserRepository userRepository;
    private final RoomsRepository roomsRepository;
    private final ReservationRepository reservationRepository;

    public boolean postReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Optional<Room> optionalRoom = roomsRepository.findById(reservationDto.getRoomId());
        if (optionalUser.isPresent() && optionalRoom.isPresent()) {
            Reservation reservation = new Reservation();

            reservation.setRoom(optionalRoom.get());
            reservation.setUser(optionalUser.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate());
            reservation.setPrice(days * optionalRoom.get().getPrice());

            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

}
