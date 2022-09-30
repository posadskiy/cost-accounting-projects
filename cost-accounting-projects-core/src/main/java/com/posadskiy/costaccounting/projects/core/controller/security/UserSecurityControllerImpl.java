package com.posadskiy.costaccounting.projects.core.controller.security;

import com.posadskiy.restsecurity.controller.UserSecurityController;
import com.posadskiy.restsecurity.exception.UserDoesNotExistException;
import com.posadskiy.costaccounting.projects.core.db.UserRepository;
import com.posadskiy.costaccounting.projects.core.db.model.DbUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserSecurityControllerImpl implements UserSecurityController {
	
	private final UserRepository userRepository;

	@Autowired
	public UserSecurityControllerImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isUserExist(String userId) {
		return userRepository.findById(userId).isPresent();
	}

	@Override
	public List<String> getUserRoles(String userId) {
		final Optional<DbUser> foundUser = userRepository.findById(userId);
		if (!foundUser.isPresent()) throw new UserDoesNotExistException();
		
		return foundUser.get().getRoles();
	}
}
