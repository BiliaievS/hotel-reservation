package com.example.london.data.webservice;

import com.example.london.data.entity.Room;
import com.example.london.data.repository.RoomRepository;
import com.example.reservation.aop.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergii Biliaiev
 * Created on 04/05/2019.
 */
@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    @Timer
    public List<Room> findAll(@RequestParam(required = false) String roomNumber) {
        List<Room> rooms = new ArrayList<>();
        if (roomNumber == null) {
            roomRepository.findAll().forEach(rooms::add);
        } else {
            Room room = roomRepository.findByNumber(roomNumber);
            if (room != null) {
                rooms.add(room);
            }
        }
        return rooms;
    }
}
