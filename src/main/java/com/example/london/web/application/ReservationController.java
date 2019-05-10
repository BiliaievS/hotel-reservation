package com.example.london.web.application;

import com.example.london.business.domain.RoomReservation;
import com.example.london.business.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Sergii Biliaiev
 * Created on 05/05/2019.
 */
@Controller
@RequestMapping(value = "/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(@RequestParam(value = "date", required = false) String dateStr, Model model) {
        List<RoomReservation> roomReservationByDate = reservationService.getRoomReservationByDate(dateStr);
        model.addAttribute("roomReservations", roomReservationByDate);
        return "reservations";
    }
}
