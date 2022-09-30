package com.posadskiy.costaccounting.projects.core.exception;

public class UserValidationException extends RuntimeException {
	private String reason;

	private UserValidationException() {}

	public UserValidationException(String reason) {
		this.reason = reason;
	}

	public String getReason() {
		return reason;
	}
}
