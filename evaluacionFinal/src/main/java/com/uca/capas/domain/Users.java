package com.uca.capas.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Users{


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//@GeneratedValue(generator="cat_libro_c_libro_seq", strategy = GenerationType.AUTO)
	//@SequenceGenerator(name = "cat_libro_c_libro_seq", sequenceName = "public.cat_libro_c_libro_seq", allocationSize = 1)
	private Long id;
	
	@Column (name="first_name")
	@NotBlank
	@Size(min=5,max=8,message="No se cumple las reglas del tamano")
	private String firstName;
	
	@Column (name="last_name")
	@NotBlank
	private String lastName;
	
	@Column (name="direccion")
	@NotBlank
	private String direccion;
	
	@Column (name="edad")
	private Integer edad;
	
	@Column (name="estado")
	private Boolean estado;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column (name="nacimiento")
	private Date nacimiento;
	
	
	@Column
	@NotBlank
	private String username;
	
	@Column
	private String password;
	
	@Transient
	private String confirmPassword;
	
	@Size(min=1)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_roles",
			joinColumns=@JoinColumn(name="user_id"),
			inverseJoinColumns=@JoinColumn(name="role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "d_departamento")
	private Departamento departamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	
	public Date getNacimiento() {
		return nacimiento;
	}

	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}
	
	

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
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
	
	//Delegate para conversion de fecha
	public String getFechaDelegate(){
		if(this.nacimiento == null){
			return "";
		}
		else{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String shortdate = sdf.format(this.nacimiento.getTime());
			return shortdate;
		}
	}

	@Override
	public String toString() {
		return "Users [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", direccion=" + direccion
				+ ", edad=" + edad + ", estado=" + estado + ", nacimiento=" + nacimiento + ", username=" + username
				+ ", password=" + password + ", confirmPassword=" + confirmPassword + ", roles=" + roles
				+ ", departamento=" + departamento + "]";
	}


	


	

	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id")
	private Role roles;*/

	
	
	
}
