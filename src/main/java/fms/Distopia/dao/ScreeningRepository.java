package fms.Distopia.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fms.Distopia.entities.Screening;

public interface ScreeningRepository extends JpaRepository<Screening, Long> {
    public List<Screening> findByCinemaId(Long cinemaId);

}
