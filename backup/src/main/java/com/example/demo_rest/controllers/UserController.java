package com.example.demo_rest.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_rest.utils.Validations;
import com.example.demo_data.model.UserModel;
import com.example.demo_data.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
public class UserController {

	private final UserRepository userRepository;
	private final ObjectMapper mapper;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserController(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.mapper = new ObjectMapper();
	}
	
	@PostMapping(name = "/registro", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> saveUser(@RequestBody UserModel user) {
		ObjectNode resp = mapper.createObjectNode();
		HttpStatus status = HttpStatus.OK;
		try {
			
			if (userRepository.findByEmail(user.getEmail()) != null) {
				status = HttpStatus.ALREADY_REPORTED;
				resp.put("mensaje", "El correo ya se encuentra registrado");
			}
			else if(validate(resp, user)) {
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));	
				userRepository.save(user);
				
				resp = mapper.valueToTree(user);
			}
			else {
				status = HttpStatus.BAD_REQUEST;
			}
		}
		catch (Exception e) 
		{
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			resp.put("mensaje", e.getMessage());
		}
		
		return new ResponseEntity<String>(resp.toString(), status);
	}
	
	private boolean validate(ObjectNode resp, UserModel user) 
	{
		boolean validation = true;
		if (!Validations.validateEmail(user.getEmail())) {
			resp.put("mensaje", "Correo formato incorrecto");
			validation = false;
		}
			
		if (!Validations.validatePassword(user.getPassword())) {
			resp.put("mensaje", "Password formato incorrecta");
			validation = false;
		}
		
		return validation;
	}
}
