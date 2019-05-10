package com.example.london.data.repository;

import com.example.london.data.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Sergii Biliaiev
 * Created on 04/05/2019.
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    Room findByNumber(String number);
}
