package com.uhi.network_registry_api.services;

import com.uhi.network_registry_api.exceptions.NRAuthException;
import com.uhi.network_registry_api.models.User;

public interface IUserService {

	User validateUser (String email, String password) throws NRAuthException;
	
	User registerUser (String firstName, String lastName, String email, String password) throws NRAuthException;
	
}
