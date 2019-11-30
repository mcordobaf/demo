package com.example.demo_rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
    Logger logger = LoggerFactory.getLogger(UserController.class);
    
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder =  new BCryptPasswordEncoder();
		this.mapper = new ObjectMapper();
	}
	
	@RequestMapping(name = "/registro", method = RequestMethod.POST)
	public ResponseEntity<String> saveUser(@RequestBody UserModel user) {
		ObjectNode resp = mapper.createObjectNode();
		HttpStatus status = HttpStatus.OK;
		try {
			
			if (userRepository.findByEmail(user.getEmail()) != null) {
				logger.info("mensaje", "El correo ya se encuentra registrado");

				status = HttpStatus.ALREADY_REPORTED;
				resp.put("mensaje", "El correo ya se encuentra registrado");
			}
			else if(validate(resp, user)) {
				user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));	
				userRepository.save(user);
				
				resp = mapper.valueToTree(user);
				logger.info("mensaje", "Usuario registrado correctamente.");
			}
			else {
				status = HttpStatus.BAD_REQUEST;
				logger.info("mensaje", "Formato de correo o password incorrectos.");
				
			}
		}
		catch (Exception e) 
		{
			logger.info("mensaje", e.toString());
			
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
