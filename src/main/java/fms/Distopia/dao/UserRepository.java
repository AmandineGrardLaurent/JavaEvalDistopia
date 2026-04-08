package fms.Distopia.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fms.Distopia.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
