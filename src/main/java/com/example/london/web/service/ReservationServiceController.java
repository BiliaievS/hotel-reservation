package com.example.london.web.service;

import com.example.london.business.domain.RoomReservation;
import com.example.london.business.service.ReservationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Sergii Biliaiev
 * Created on 05/05/2019.
 */
@RestController
@RequestMapping(value = "/api")
public class ReservationServiceController {

    private ReservationService reservationService;

    public ReservationServiceController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping(value = "/reservations/{date}")
    public List<RoomReservation> getAllReservations(@PathVariable(value = "date") String date) {
        return reservationService.getRoomReservationByDate(date);
    }
}
