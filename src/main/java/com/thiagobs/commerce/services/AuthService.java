package com.thiagobs.commerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagobs.commerce.entities.User;
import com.thiagobs.commerce.services.exceptions.ForbiddenException;

@Service
public class AuthService {

	@Autowired
	UserService userService;

	public void verififyRoleAndClient(Long id) {

		User user = userService.findUserAutent();

		if (!user.getId().equals(id) && !user.hasRole("ROLE_ADMIN")) {
			throw new ForbiddenException("Acesso negado!");

		}

	}

}
