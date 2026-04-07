package fms.Distopia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fms.Distopia.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
