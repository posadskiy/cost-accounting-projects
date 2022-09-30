package com.posadskiy.costaccounting.projects.core.exception;

public class MonthStatisticDoesNotExistException extends RuntimeException implements Exceptionable {

	String detailedMessage;
	int code;

	public MonthStatisticDoesNotExistException() {
		super("Month statistic does not exist");
		this.detailedMessage = "Month statistic is not created yet. Try later";
		this.code = 7;
	}


	@Override
	public String getDetailedMessage() {
		return detailedMessage;
	}

	@Override
	public int getCode() {
		return code;
	}

}
