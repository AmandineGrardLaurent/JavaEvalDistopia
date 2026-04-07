package fms.Distopia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fms.Distopia.entities.Cinema;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {

}
