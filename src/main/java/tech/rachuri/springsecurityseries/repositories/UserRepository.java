package tech.rachuri.springsecurityseries.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tech.rachuri.springsecurityseries.models.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);
}
