package fms.Distopia.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fms.Distopia.entities.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    public Page<Cinema> findByCityName(String cityName, Pageable pageable);
}
