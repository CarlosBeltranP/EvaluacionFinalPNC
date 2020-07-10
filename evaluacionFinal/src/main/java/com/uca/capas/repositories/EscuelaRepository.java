package com.uca.capas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Departamento;
import com.uca.capas.domain.Escuela;

//JpaRepository

@Repository
public interface EscuelaRepository extends CrudRepository<Escuela, Long> {

}
