package com.uca.capas.controller;


import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.uca.capas.Exception.CustomeFieldValidationException;
import com.uca.capas.domain.Departamento;
import com.uca.capas.domain.Escuela;
import com.uca.capas.domain.Materia;
import com.uca.capas.domain.Role;
import com.uca.capas.domain.Users;
import com.uca.capas.dto.ChangePasswordForm;
import com.uca.capas.repositories.DepartamentoRepository;
import com.uca.capas.repositories.EscuelaRepository;
import com.uca.capas.repositories.MateriaRepository;
import com.uca.capas.repositories.RoleRepository;
import com.uca.capas.service.EscuelaService;
import com.uca.capas.service.MateriaService;
import com.uca.capas.service.UserService;

@Controller
public class MainController {
	
	Logger log = Logger.getLogger(MainController.class.getName());

	private final String TAB_FORM = "formTab";
	private final String TAB_FORM2 = "formTab2";
	private final String TAB_LIST = "listTab";
	private final String TAB_LIST2 = "listTab2";
	
	@Autowired
	UserService userService;
	
	@Autowired
	EscuelaService escuelaService;
	
	@Autowired
	MateriaService materiaService;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	EscuelaRepository escuelaRepository;
	
	@Autowired
	MateriaRepository materiaRepository;
	
	@Autowired
	DepartamentoRepository departamentoRepository;

	@GetMapping({"/", "/login"})
	public String index() {
		return "index";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		List<Role> roles = roleRepository.findAll();
		List<Departamento> departamentos= null;
		departamentos = departamentoRepository.findAll();
		model.addAttribute("signup",true);
		model.addAttribute("userForm", new Users());
		model.addAttribute("departamentos", departamentos);
		model.addAttribute("roles",roles);
		return "user-form/user-signup";
	}
	
	@PostMapping("/signup")
	public String signupAction(@Valid @ModelAttribute("userForm")Users user, BindingResult result, ModelMap model) {
		//Role userRole = roleRepository.findByName("USER");
		//List<Role> roles = Arrays.asList(userRole);
		List<Role> roles = roleRepository.findAll();
	 	List<Departamento> departamentos= departamentoRepository.findAll();
		
		Date date = user.getNacimiento();
		Instant instant = date.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate fecha = zdt.toLocalDate();
		LocalDate ahora = LocalDate.now();
		Period periodo = Period.between(fecha, ahora );
		user.setEdad(periodo.getYears());
		
		model.addAttribute("userForm", user);

		model.addAttribute("departamentos", departamentos);
		model.addAttribute("roles",roles);
		model.addAttribute("signup",true);
		
		if(result.hasErrors()) {
			return "user-form/user-signup";
		}else {
			try {
				
				userService.createUser(user);
			} catch (CustomeFieldValidationException cfve) {
				result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
				log.info("Error encontrado");
			}catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				log.info("Error encontrado");
			}
		}
		return index();
	}
	
	private void baseAttributerForUserForm(Model model, Users user,String activeTab) {
		model.addAttribute("userForm", user);
		model.addAttribute("userList", userService.getAllUsers());
	    model.addAttribute("departamentos", departamentoRepository.findAll());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute(activeTab,"active");
	}
	
	private void baseAttributerForEscuelaForm(Model model, Escuela escuela,String activeTab) {
		model.addAttribute("userForm", escuela);
		model.addAttribute("escuelaList", escuelaRepository.findAll());
		model.addAttribute(activeTab,"active");
	}
	
	private void baseAttributerForMateriaForm(Model model, Materia materia,String activeTab) {
		model.addAttribute("userForm", materia);
		model.addAttribute("materiaList", materiaRepository.findAll());
		model.addAttribute(activeTab,"active");
	}

	//***************************************** USUARIOS *********************************************
	@GetMapping("/userForm")
	public String userForm(Model model) {
		log.info("Estoy aqui boton");
		baseAttributerForUserForm(model, new Users(), TAB_LIST );
		return "user-form/user-view";
	}
	
	@PostMapping("/userForm")
	public String createUser(@Valid @ModelAttribute("userForm")Users user, BindingResult result, Model model) {
		if(result.hasErrors()) {
			baseAttributerForUserForm(model, user, TAB_FORM);
		}else {
			try {
				List<Departamento> departamentos= null;
				departamentos = departamentoRepository.findAll();
				Date date = user.getNacimiento();
				Instant instant = date.toInstant();
				ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
				LocalDate fecha = zdt.toLocalDate();	
				LocalDate ahora = LocalDate.now();
				Period periodo = Period.between(fecha, ahora );
				user.setEdad(periodo.getYears());
				model.addAttribute("departamentos", departamentos);
				userService.createUser(user);
				baseAttributerForUserForm(model, new Users(), TAB_LIST );
				
			} catch (CustomeFieldValidationException cfve) {
				result.rejectValue(cfve.getFieldName(), null, cfve.getMessage());
				baseAttributerForUserForm(model, user, TAB_FORM );
			}catch (Exception e) {
				log.info("Error encontrado");
				model.addAttribute("formErrorMessage",e.getMessage());
				baseAttributerForUserForm(model, user, TAB_FORM );
			}
		}
		return "user-form/user-view";
	}
	
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model, @PathVariable(name = "id") Long id)throws Exception{
		Users userToEdit = userService.getUserById(id);
		model.addAttribute("userForm", userToEdit);
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("departamentos",departamentoRepository.findAll());
		model.addAttribute("roles",roleRepository.findAll());
		model.addAttribute("formTab","active");
		model.addAttribute("editMode","true");
		//Para cambiar contraseña
		model.addAttribute("passwordForm",new ChangePasswordForm(id));
		return "user-form/user-view";
	}
	
	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm")Users user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab","active");
			model.addAttribute("editMode","true");
			//Para cambiar contraseña
			model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
		}else {
			try {
				userService.updateUser(user);
				model.addAttribute("userForm", new Users());
				model.addAttribute("listTab","active");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
				model.addAttribute("userList", userService.getAllUsers());
				model.addAttribute("departamentos",departamentoRepository.findAll());
				model.addAttribute("roles",roleRepository.findAll());
				model.addAttribute("editMode","true");
				//Para cambiar password
				model.addAttribute("passwordForm",new ChangePasswordForm(user.getId()));
			}
		}
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("departamentos",departamentoRepository.findAll());
		model.addAttribute("roles",roleRepository.findAll());
		return "user-form/user-view";
	}
	
	@GetMapping("/userForm/cancel")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/userForm";
	}
	
	
	
	
	//******************************************* ESCUELAS ***********************************************
	
	@GetMapping("/escuelaForm")
	public String userForm2(Model model) {
		log.info("Estoy aqui boton");
		baseAttributerForEscuelaForm(model, new Escuela(), TAB_LIST );
		return "user-form/escuela-view";
	}
	
	@PostMapping("/escuelaForm")
	public String createUser2(@Valid @ModelAttribute("userForm")Escuela escuela, BindingResult result, Model model) throws CustomeFieldValidationException {
		if(result.hasErrors()) {
			baseAttributerForEscuelaForm(model, escuela, TAB_FORM);
		}else {
			try {
	
				escuelaRepository.save(escuela);
				baseAttributerForEscuelaForm(model, new Escuela(), TAB_LIST );
				
			} catch (Exception e) {
				log.info("Error encontrado");
				model.addAttribute("formErrorMessage",e.getMessage());
				baseAttributerForEscuelaForm(model, escuela, TAB_FORM );
			}
		}
		return "user-form/escuela-view";
	}
	
	@GetMapping("/editEscuela/{id}")
	public String getEditEscuelaForm(Model model, @PathVariable(name = "id") Long id)throws Exception{
		Escuela escuelaToEdit = escuelaService.getEscuelaById(id);
		model.addAttribute("userForm", escuelaToEdit);
		model.addAttribute("escuelaList", escuelaRepository.findAll());
		model.addAttribute("formTab","active");
		model.addAttribute("editMode","true");
		return "user-form/escuela-view";
	}
	
	@PostMapping("/editEscuela")
	public String postEditEscuelaForm(@Valid @ModelAttribute("userForm")Escuela escuela, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", escuela);
			model.addAttribute("formTab","active");
			model.addAttribute("editMode","true");
	
		}else {
			try {
				
				escuelaService.updateEscuela(escuela);
				model.addAttribute("userForm", new Escuela());
				model.addAttribute("listTab","active");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", escuela);
				model.addAttribute("formTab","active");
				model.addAttribute("escuelaList", escuelaRepository.findAll());
				model.addAttribute("editMode","true");

			}
		}
		model.addAttribute("escuelaList", escuelaRepository.findAll());
		return "user-form/escuela-view";

	}
	
	
	@GetMapping("/escuelaForm/cancel")
	public String cancelEditEscuela(ModelMap model) {
		return "redirect:/escuelaForm";
	}
	
	
	//************************************************ MATERIAS ***************************************************
	@GetMapping("/materiaForm")
	public String userForm3(Model model) {
		log.info("Estoy aqui boton");
		baseAttributerForMateriaForm(model, new Materia(), TAB_LIST );
		return "user-form/materia-view";
	}
	
	@PostMapping("/materiaForm")
	public String createUser3(@Valid @ModelAttribute("userForm")Materia materia, BindingResult result, Model model) throws CustomeFieldValidationException {
		if(result.hasErrors()) {
			baseAttributerForMateriaForm(model, materia, TAB_FORM);
		}else {
			try {
	
				materiaRepository.save(materia);
				baseAttributerForMateriaForm(model, new Materia(), TAB_LIST );
				
			} catch (Exception e) {
				log.info("Error encontrado");
				model.addAttribute("formErrorMessage",e.getMessage());
				baseAttributerForMateriaForm(model, materia, TAB_FORM );
			}
		}
		return "user-form/materia-view";
	}
	
	@GetMapping("/editMateria/{id}")
	public String getEditMateriaForm(Model model, @PathVariable(name = "id") Long id)throws Exception{
		Materia materiaToEdit = materiaService.getMateriaById(id);
		model.addAttribute("userForm", materiaToEdit);
		model.addAttribute("materiaList", materiaRepository.findAll());
		model.addAttribute("formTab","active");
		model.addAttribute("editMode","true");
		return "user-form/materia-view";
	}
	
	@PostMapping("/editMateria")
	public String postEditMateriaForm(@Valid @ModelAttribute("userForm")Materia materia, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", materia);
			model.addAttribute("formTab","active");
			model.addAttribute("editMode","true");
	
		}else {
			try {
				
				materiaService.updateMateria(materia);
				model.addAttribute("userForm", new Materia());
				model.addAttribute("listTab","active");
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", materia);
				model.addAttribute("formTab","active");
				model.addAttribute("materiaList", materiaRepository.findAll());
				model.addAttribute("editMode","true");

			}
		}
		model.addAttribute("materiaList", materiaRepository.findAll());
		return "user-form/materia-view";

	}
	
	
	@GetMapping("/materiaForm/cancel")
	public String cancelEditMateria(ModelMap model) {
		return "redirect:/materiaForm";
	}
	
	
	
	
	
	@GetMapping("/deleteUser/{id}")
	public String deleteUser(Model model, @PathVariable(name="id") Long id) {
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			model.addAttribute("deleteError","The user could not be deleted.");

			
		}
		return userForm(model);
	}
	
	//Para cambiar password
	@PostMapping("/editUser/changePassword")
	public ResponseEntity postEditUseChangePassword(@Valid @RequestBody ChangePasswordForm form, Errors errors) {
		try {
	        if (errors.hasErrors()) {
	            String result = errors.getAllErrors()
	                        .stream().map(x -> x.getDefaultMessage())
	                        .collect(Collectors.joining(""));
	            throw new Exception(result);
	        }
			userService.changePassword(form);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok("success");
	}
	

}