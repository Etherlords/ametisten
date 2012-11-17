package org.etherlords.ametisten.stat.application.webservice.controller;

public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7222577751602357001L;
	
	private final String accountName;

	public AccountNotFoundException(String message, Throwable cause, String accountName) {
		super(message, cause);
		this.accountName = accountName;
	}

	public AccountNotFoundException(String message, String accountName) {
		this(message, null, accountName);
	}

	public String getAccountName() {
		return accountName;
	}
	
}
