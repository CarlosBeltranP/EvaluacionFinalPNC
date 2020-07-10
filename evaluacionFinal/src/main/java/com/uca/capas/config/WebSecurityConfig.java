package com.uca.capas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.uca.capas.service.UserDetailsServiceImpl;

//Indica que esta clase es de configuracion y necesita ser cargada durante el inicio del server
@Configuration

//Indica que esta clase sobreescribira la implmentacion de seguridad web
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

  String[] resources = new String[]{
          "/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"
  };
  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
  	http
      .authorizeRequests()
      .antMatchers(resources).permitAll()   //permitir acceso a todos a los resources (lo que está arriba)
      .antMatchers("/","/index", "/signup").permitAll() //Cualquiera puede ingresar a / o a /index
          .anyRequest().authenticated() //cualquier otro request necesitara una autentificación 
          .and()
      .formLogin()
          .loginPage("/login")
          .permitAll() //todos pueden acceder a /login
          .defaultSuccessUrl("/userForm") // si el logueo fue exitoso redirigirá a userForm
          .failureUrl("/login?error=true") //sino fue exitoso, regresará a /login con un atributo para un mensaje de error
          .usernameParameter("username") // name=username en user-form
          .passwordParameter("password") //name = password en user-form
          .and()
          .csrf().disable()
      .logout()
          .permitAll() //todos se pueden desloguear
          .logoutSuccessUrl("/login?logout"); //logout exitoso redirige a /login con mensaje 
  }
  
  BCryptPasswordEncoder bCryptPasswordEncoder;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
      return bCryptPasswordEncoder;
  }
  
  @Autowired
  UserDetailsService userDetailsService;
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception { 
  	//Especificar el encargado del login y encriptacion del password
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }
}
