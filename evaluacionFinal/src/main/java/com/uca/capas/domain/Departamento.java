package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(schema="public", name="departamento")
public class Departamento {
	@Id
	@Column(name="departamento_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer departamento_id;

	@Column(name="departamento")
	private String nombre;

	public Integer getDepartamento_id() {
		return departamento_id;
	}

	public void setDepartamento_id(Integer departamento_id) {
		this.departamento_id = departamento_id;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public Departamento() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
