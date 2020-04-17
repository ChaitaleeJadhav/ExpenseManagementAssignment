package com.expense.manage.ExpenseManagement.service;

import com.expense.manage.ExpenseManagement.dto.UserDto;
import com.expense.manage.ExpenseManagement.model.UserCredentials;

public interface RegisterService {

	// To Register User
	public UserDto registerUser(UserCredentials user);

	// To update a existeduser details
	// Now we are upadting password
	public UserDto UpdateUser(UserCredentials user);

	// remove User account
	public String RemoveUserAcc(String userId);

	// Fetch user details
	public UserDto getUserById(String userId);

}
