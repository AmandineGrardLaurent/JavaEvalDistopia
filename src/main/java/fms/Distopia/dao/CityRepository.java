package fms.Distopia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fms.Distopia.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
