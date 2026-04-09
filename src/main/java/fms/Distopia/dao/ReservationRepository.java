package fms.Distopia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fms.Distopia.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    public List<Reservation> findByUserId(Long userId);
}
