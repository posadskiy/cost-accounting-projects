package com.posadskiy.costaccounting.projects.core.validation;

import com.posadskiy.costaccounting.projects.api.dto.User;
import org.springframework.stereotype.Component;

@Component
public class UserValidation {
	
	public boolean userValidate(User user) {
		return nameValidate(user.getName()) &&
			defaultCurrencyValidate(user.getDefaultCurrency());
	}
	
	public boolean nameValidate(String username) {
		return true;
	}
	
	public boolean defaultCurrencyValidate(String defaultCurrency) {
		return true;
	}
}
