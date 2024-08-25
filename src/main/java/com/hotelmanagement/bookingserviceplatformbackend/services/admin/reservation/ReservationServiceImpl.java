package com.hotelmanagement.bookingserviceplatformbackend.services.admin.reservation;

import com.hotelmanagement.bookingserviceplatformbackend.dto.ReservationResponseDto;
import com.hotelmanagement.bookingserviceplatformbackend.entity.Reservation;
import com.hotelmanagement.bookingserviceplatformbackend.entity.Room;
import com.hotelmanagement.bookingserviceplatformbackend.enums.ReservationStatus;
import com.hotelmanagement.bookingserviceplatformbackend.repository.ReservationRepository;
import com.hotelmanagement.bookingserviceplatformbackend.repository.RoomsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomsRepository roomsRepository;

    public static final int SEARCH_RESULT_PER_PAGE = 4;

    public ReservationResponseDto getAllReservations(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);
        Page<Reservation> reservationPage = reservationRepository.findAll(pageable);
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
        reservationResponseDto.setReservationDtoList(reservationPage.stream().map(Reservation::getReservationDto).collect(Collectors.toList()));
        reservationResponseDto.setPageNumber(reservationPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPages(reservationPage.getTotalPages());

        return reservationResponseDto;
    }

    public boolean changeReservationStatus(Long id, String status) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            if (Objects.equals(status,"Approve")) {
                reservation.setReservationStatus(ReservationStatus.APPROVED);
            } else if (Objects.equals(status,"Rejected")) {
                reservation.setReservationStatus(ReservationStatus.REJECTED);
            }

            reservationRepository.save(reservation);

            Room existingRoom = reservation.getRoom();
            existingRoom.setAvailable(false);

            roomsRepository.save(existingRoom);

            return true;
        }
        return false;
    }
}
