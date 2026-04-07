package fms.Distopia.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fms.Distopia.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
