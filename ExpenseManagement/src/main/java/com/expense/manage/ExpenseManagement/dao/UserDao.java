package com.expense.manage.ExpenseManagement.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expense.manage.ExpenseManagement.dto.UserDto;
import com.expense.manage.ExpenseManagement.exceptions.ResourceNotFoundException;
import com.expense.manage.ExpenseManagement.model.UserCredentials;
import com.expense.manage.ExpenseManagement.service.ModelMapperService;

@Repository("userdao")
public class UserDao {

	@Autowired
	public ModelMapperService modelMapperService;

	@Autowired
	public RegisterRepository regrpo;

	public UserDto saveUser(UserCredentials user) {
		Boolean existUser = regrpo.existsById(user.getId());
		if (existUser) {
			throw new ResourceNotFoundException("UserId id is already Exists,Want Unique user id");
		}
		else {
			UserCredentials resultUser = regrpo.save(user);
			return modelMapperService.mappedToUsetDto(resultUser);
		}
	}

	public UserDto UpdateUserPassword(UserCredentials user) {
		Boolean existUser = regrpo.existsById(user.getId());
		if (existUser) {
			UserCredentials resultUser = regrpo.save(user);
			return modelMapperService.mappedToUsetDto(resultUser);
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
