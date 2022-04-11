package com.uhi.network_registry_api.repository;

import com.uhi.network_registry_api.exceptions.NRAuthException;
import com.uhi.network_registry_api.models.User;

public interface IUserRepository {

	Integer addUser(String firstName, String lastName, String email, String password) throws NRAuthException;

	User findUser(String email, String password) throws NRAuthException;

	User findUser(Integer user_id) throws NRAuthException;

}
