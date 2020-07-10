package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="public", name="materia")
public class Materia {
	@Id
	@Column(name="materia_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long materia_id;

	@Column(name="materia")
	private String materia;
	
	@Column (name="estado")
	private Boolean estado;

	public Long getMateria_id() {
		return materia_id;
	}

	public void setMateria_id(Long materia_id) {
		this.materia_id = materia_id;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Materia [materia_id=" + materia_id + ", materia=" + materia + ", estado=" + estado + "]";
	}

	public Materia() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	//Delegate para activo o inactivo
	public String getBactivoDelegate(){
		if(this.estado == null){
			return "";
		}
		else{
			if(this.estado) return "ACTIVO";
			else return "INACTIVO";
		}
	}
	


}
