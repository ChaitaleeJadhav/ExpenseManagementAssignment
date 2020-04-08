package com.expense.manage.ExpenseManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.manage.ExpenseManagement.dao.UserDao;
import com.expense.manage.ExpenseManagement.model.UserCredentials;

@Service("regService")
public class RegisterServiceImp implements RegisterService {

	@Autowired
	public UserDao userDao;

	public UserCredentials registerUser(UserCredentials user) {
		return userDao.saveUser(user);
	}

	public UserCredentials getUserById(String userId)

	{
		return null;
		// return userDao.getUserById();
	}
}
