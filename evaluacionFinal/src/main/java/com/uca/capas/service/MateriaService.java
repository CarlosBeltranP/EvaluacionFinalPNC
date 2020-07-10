package com.uca.capas.service;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Materia;
import com.uca.capas.domain.Users;

public interface MateriaService {
public Iterable<Users> getAllUsers();
	
	public Materia createMateria(Materia materia) throws Exception;

	void save(Materia materia) throws DataAccessException;
	
	public Materia getMateriaById(Long id) throws Exception;
	
	public Materia updateMateria(Materia materia) throws Exception;

}
