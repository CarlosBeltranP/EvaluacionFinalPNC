package com.uca.capas.service;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Escuela;
import com.uca.capas.domain.Users;
import com.uca.capas.dto.ChangePasswordForm;

public interface EscuelaService {
public Iterable<Users> getAllUsers();
	
	public Escuela createUser(Escuela escuela) throws Exception;

	void save(Escuela escuela) throws DataAccessException;
	
	public Escuela getEscuelaById(Long id) throws Exception;
	
	public Escuela updateEscuela(Escuela escuela) throws Exception;
	
}
