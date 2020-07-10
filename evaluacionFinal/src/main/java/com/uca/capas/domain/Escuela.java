package com.uca.capas.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="public", name="escuela")
public class Escuela {
	
	@Id
	@Column(name="escuela_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(generator="escuela_escuela_id_seq", strategy = GenerationType.AUTO)
	//@SequenceGenerator(name = "escuela_escuela_id_seq", sequenceName = "public.escuela_escuela_id_seq", allocationSize = 1)
	private Long escuela_id;

	@Column(name="escuela")
	private String escuela;
	
	@Column (name="estado")
	private Boolean estado;

	public Long getEscuela_id() {
		return escuela_id;
	}

	public void setEscuela_id(Long escuela_id) {
		this.escuela_id = escuela_id;
	}

	public String getEscuela() {
		return escuela;
	}

	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Escuela() {
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
