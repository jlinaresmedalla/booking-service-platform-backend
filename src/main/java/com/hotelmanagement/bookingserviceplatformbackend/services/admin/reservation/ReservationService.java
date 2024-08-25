package com.hotelmanagement.bookingserviceplatformbackend.services.admin.reservation;

import com.hotelmanagement.bookingserviceplatformbackend.dto.ReservationResponseDto;

public interface ReservationService {
    ReservationResponseDto getAllReservations(int pageNumber);
    boolean changeReservationStatus(Long id, String status);
}
