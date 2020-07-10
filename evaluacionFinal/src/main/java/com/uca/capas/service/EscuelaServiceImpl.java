package com.uca.capas.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uca.capas.Exception.CustomeFieldValidationException;
import com.uca.capas.domain.Escuela;
import com.uca.capas.domain.Users;
import com.uca.capas.dto.ChangePasswordForm;
import com.uca.capas.repositories.EscuelaRepository;
import com.uca.capas.repositories.UserRepository;

@Service
public class EscuelaServiceImpl implements EscuelaService{

	@Autowired
	EscuelaRepository repository;
	
	@Override
	public Iterable<Users> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Escuela createUser(Escuela escuela) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Escuela escuela) throws DataAccessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Escuela getEscuelaById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return repository.findById(id).orElseThrow(() -> new Exception("La escuela para editar no existe."));
	}

	protected void mapEscuela(Escuela from,Escuela to) {
		to.setEscuela(from.getEscuela());
		to.setEstado(from.getEstado());
	}

	@Override
	public Escuela updateEscuela(Escuela escuela) throws Exception {
		// TODO Auto-generated method stub
		Escuela toEscuela = getEscuelaById(escuela.getEscuela_id());
		mapEscuela(escuela, toEscuela);
		return repository.save(toEscuela);
	}
	

	

}