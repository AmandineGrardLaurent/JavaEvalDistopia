package fms.Distopia.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fms.Distopia.entities.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

    public Page<Cinema> findByCityName(String cityName, Pageable pageable);

    public Page<Cinema> findByCityNameAndNameContainsIgnoreCase(String cityName, String search, Pageable pageable);

    public Page<Cinema> findByNameContainsIgnoreCase(String search, Pageable pageable);
}
