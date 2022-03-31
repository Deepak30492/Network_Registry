package com.uhi.network_registry_api.controllers;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@PostMapping("/")
	public String registerUser(@RequestBody Map<String, Object> userMap)
	{
		
			String firstName = (String) userMap.get("firstName");
	        String lastName = (String) userMap.get("lastName");
	        String email = (String) userMap.get("email");
	        String password = (String) userMap.get("password");
	        return firstName + " " + lastName;
	        //User user = userService.registerUser(firstName, lastName, email, password);
	        //return new ResponseEntity<>(generateJWTToken(user), HttpStatus.OK);
	}
	
}
