package com.uca.capas.service;

import org.springframework.dao.DataAccessException;

import com.uca.capas.domain.Users;
import com.uca.capas.dto.ChangePasswordForm;


public interface UserService {

	public Iterable<Users> getAllUsers();
	
	public Users createUser(Users user) throws Exception;

	void save(Users user) throws DataAccessException;
	
	public Users getUserById(Long id) throws Exception;
	
	public Users updateUser(Users user) throws Exception;
	

	public void deleteUser(Long id) throws Exception;
	
	//para cambiar contraseña
	public Users changePassword(ChangePasswordForm form) throws Exception;
	

	
	
}
