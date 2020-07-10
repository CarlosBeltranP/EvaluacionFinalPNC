package com.uca.capas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;


import com.uca.capas.domain.Role;
import com.uca.capas.repositories.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{
	@Autowired
	RoleRepository roleRepository;

	@Override
	public List<Role> todos() throws DataAccessException{
		//return  estudianteRepo.findAll();
		return roleRepository.mostrarTodos();
	}

	@Override
	public Role findOne(Long code) throws DataAccessException {
		// TODO Auto-generated method stub
		return roleRepository.getOne(code); 
	}


}
