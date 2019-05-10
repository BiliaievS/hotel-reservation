package com.example.london.business.service;

import com.example.london.business.domain.RoomReservation;
import com.example.london.data.entity.Guest;
import com.example.london.data.entity.Reservation;
import com.example.london.data.entity.Room;
import com.example.london.data.repository.GuestRepository;
import com.example.london.data.repository.ReservationRepository;
import com.example.london.data.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Sergii Biliaiev
 * Created on 04/05/2019.
 */
@Service
public class ReservationService {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyy-MM-dd");

    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationByDate(String dateStr) {
        Date date = createDateFromString(dateStr);
        Iterable<Room> rooms = roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getId());
            roomReservation.setRoomName(room.getName());
            roomReservation.setRoomNumber(room.getNumber());
            roomReservationMap.put(room.getId(), roomReservation);
        });

        Iterable<Reservation> reservations = reservationRepository.findByDate(new java.sql.Date(date.getTime()));
        if (reservations != null) {
            reservations.forEach(reservation -> {
                Optional<Guest> guestRes = guestRepository.findById(reservation.getGuestId());
                if(guestRes.isPresent()){
                    Guest guest = guestRes.get();
                    RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
                    roomReservation.setDate(date);
                    roomReservation.setGuestId(guest.getId());
                    roomReservation.setFirstName(guest.getFirstName());
                    roomReservation.setLastName(guest.getLastName());
                }

            });
        }
        return roomReservationMap.keySet().stream().map(roomReservationMap::get).collect(Collectors.toList());
    }

    private Date createDateFromString(String dateStr) {
        Date date = new Date();
        if (dateStr != null) {
            try {
                date = DATE_FORMAT.parse(dateStr);
            } catch (ParseException ignored) {

            }
        }
        return date;
    }
}
