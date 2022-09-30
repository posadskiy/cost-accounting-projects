package com.posadskiy.costaccounting.projects.web.endpoint;

import com.posadskiy.costaccounting.projects.core.controller.UserController;
import com.posadskiy.costaccounting.projects.api.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserEndpoint {
	
	private final UserController userController;

	@Autowired
	public UserEndpoint(UserController userController) {
		this.userController = userController;
	}


	@PostMapping("allInProject")
	public List<User> getAllByProjectId(@RequestBody final User user) {
		return userController.getAllUsersByUserId(user.getId());
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable final String id) {
		return userController.getMappedById(id);
	}
	
	@PostMapping
	public User updateUser(@RequestBody final User user) {
		return userController.updateUser(user);
	}

}
