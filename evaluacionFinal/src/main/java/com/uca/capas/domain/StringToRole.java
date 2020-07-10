package com.uca.capas.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.uca.capas.service.RoleService;

@Component
public class StringToRole implements Converter<String, Role>{

	@Autowired
	private RoleService roleService;
	
	@Override
	public Role convert(String source) {
		// TODO Auto-generated method stub
		Long id = new Long(source);
	    return roleService.findOne(id);
	}

}

