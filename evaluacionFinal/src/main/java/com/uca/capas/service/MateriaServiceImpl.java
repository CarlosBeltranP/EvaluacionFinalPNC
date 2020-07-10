package com.uca.capas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.uca.capas.domain.Materia;
import com.uca.capas.domain.Users;
import com.uca.capas.repositories.MateriaRepository;

@Service
public class MateriaServiceImpl implements MateriaService{

	@Autowired
	MateriaRepository repository;
	
	@Override
	public Iterable<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Materia createMateria(Materia materia) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Materia materia) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Materia getMateriaById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return repository.findById(id).orElseThrow(() -> new Exception("La materia para editar no existe."));
	}

	protected void mapMateria(Materia from,Materia to) {
		to.setMateria(from.getMateria());
		to.setEstado(from.getEstado());
	}

	@Override
	public Materia updateMateria(Materia materia) throws Exception {
		// TODO Auto-generated method stub
		Materia toMateria = getMateriaById(materia.getMateria_id());
		mapMateria(materia, toMateria);
		return repository.save(toMateria);
	}
	

	

}