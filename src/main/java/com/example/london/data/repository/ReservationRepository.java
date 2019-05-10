package com.example.london.data.repository;

import com.example.london.data.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

/**
 * @author Sergii Biliaiev
 * Created on 04/05/2019.
 */
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findByDate(Date date);
}
