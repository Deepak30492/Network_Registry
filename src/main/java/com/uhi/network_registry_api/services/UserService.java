package com.uhi.network_registry_api.services;

import com.uhi.network_registry_api.exceptions.NRAuthException;
import com.uhi.network_registry_api.models.User;

public class UserService implements IUserService {

	@Override
	public User validateUser(String email, String password) throws NRAuthException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User registerUser(String firstName, String lastName, String email, String password) throws NRAuthException {
		// TODO Auto-generated method stub
		return null;
	}

}
