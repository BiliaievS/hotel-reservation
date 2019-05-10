package com.example.london.data.repository;

import com.example.london.data.entity.Guest;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Sergii Biliaiev
 * Created on 04/05/2019.
 */
public interface GuestRepository extends PagingAndSortingRepository<Guest, Long> {
}
