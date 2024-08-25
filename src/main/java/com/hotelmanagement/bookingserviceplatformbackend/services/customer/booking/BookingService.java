package com.hotelmanagement.bookingserviceplatformbackend.services.customer.booking;

import com.hotelmanagement.bookingserviceplatformbackend.dto.ReservationDto;

public interface BookingService {
    boolean postReservation(ReservationDto reservationDto);
}
