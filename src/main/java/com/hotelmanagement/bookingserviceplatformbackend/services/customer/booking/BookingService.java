package com.hotelmanagement.bookingserviceplatformbackend.services.customer.booking;

import com.hotelmanagement.bookingserviceplatformbackend.dto.ReservationDto;
import com.hotelmanagement.bookingserviceplatformbackend.dto.ReservationResponseDto;

public interface BookingService {
    boolean postReservation(ReservationDto reservationDto);
    ReservationResponseDto getAllReservationsByUserId(Long userId, int pageNumber);
}
