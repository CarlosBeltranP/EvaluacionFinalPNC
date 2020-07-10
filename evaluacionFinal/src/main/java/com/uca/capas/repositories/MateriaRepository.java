package com.uca.capas.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uca.capas.domain.Materia;

@Repository
public interface MateriaRepository extends CrudRepository<Materia, Long>{

}


