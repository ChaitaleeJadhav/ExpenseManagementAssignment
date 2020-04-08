package com.expense.manage.ExpenseManagement.service;

import com.expense.manage.ExpenseManagement.model.UserCredentials;

public interface RegisterService {

	// To Register User
	public UserCredentials registerUser(UserCredentials user);

	// To update a existeduser details
	// public UserCredentials UpdateUser(UserCredentials user);

	// remove User account
	// public UserCredentials RemoveUserAcc(UserCredentials user);

	// Fetch user details
	public UserCredentials getUserById(String userId);

}
