package com.thiagobs.commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagobs.commerce.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
