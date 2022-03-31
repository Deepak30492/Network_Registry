package com.uhi.network_registry_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NRAuthException extends RuntimeException {
	   
	public NRAuthException(String message) {
	        super(message);
	    }

}
