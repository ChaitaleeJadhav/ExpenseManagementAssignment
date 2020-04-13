package com.expense.manage.ExpenseManagement.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expense.manage.ExpenseManagement.exceptions.ResourceNotFoundException;
import com.expense.manage.ExpenseManagement.model.UserCredentials;

@Repository("userdao")
public class UserDao {

	@Autowired
	public RegisterRepository regrpo;

	public UserCredentials saveUser(UserCredentials user) {
		Boolean existUser = regrpo.existsById(user.getId());
		if (existUser) {
			throw new ResourceNotFoundException("UserId id is already Exists,Want Unique user id");
		}
		else {
			return regrpo.save(user);
		}
	}

	public UserCredentials UpdateUserPassword(UserCredentials user) {
		Boolean existUser = regrpo.existsById(user.getId());
		if (existUser) {
			return regrpo.save(user);
		}
		else {
			throw new ResourceNotFoundException("UserId Account Not found");

		}
	}

	public Optional<UserCredentials> saveUser(String userId) {
		return regrpo.findById(userId);
	}

	public String deleteUserAccount(String userId) {
		Boolean existUser = regrpo.existsById(userId);
		if (existUser) {
			regrpo.deleteById(userId);
			return "User Account deleted succesfully";
		}
		else {
			throw new ResourceNotFoundException("UserId account not found is already Exists");

		}
	}

}
