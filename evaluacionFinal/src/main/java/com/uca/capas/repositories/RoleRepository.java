package com.uca.capas.repositories;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	@Query(nativeQuery=true, value="select * from public.estudiante")
	public List<Role> mostrarTodos() throws DataAccessException;

	public Role findByName(String role);
	
}