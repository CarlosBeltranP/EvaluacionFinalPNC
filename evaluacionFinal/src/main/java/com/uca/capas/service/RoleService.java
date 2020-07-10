package com.uca.capas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;


import com.uca.capas.domain.Role;
import com.uca.capas.domain.Users;

public interface RoleService {

	Role findOne(Long code) throws DataAccessException;
	List<Role> todos() throws DataAccessException;



}
