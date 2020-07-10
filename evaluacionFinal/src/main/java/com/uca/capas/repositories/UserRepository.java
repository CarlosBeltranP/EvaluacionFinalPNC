package com.uca.capas.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Users;

@Repository
public interface UserRepository extends CrudRepository<Users, Long> {
	public Optional<Users> findByUsername(String username);
 }
