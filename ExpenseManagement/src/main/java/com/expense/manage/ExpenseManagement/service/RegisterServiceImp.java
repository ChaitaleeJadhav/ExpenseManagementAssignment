package com.expense.manage.ExpenseManagement.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.manage.ExpenseManagement.dao.UserDao;
import com.expense.manage.ExpenseManagement.dto.UserDto;
import com.expense.manage.ExpenseManagement.exceptions.ExpenseException;
import com.expense.manage.ExpenseManagement.exceptions.ResourceNotFoundException;
import com.expense.manage.ExpenseManagement.model.UserCredentials;

@Service("regService")
public class RegisterServiceImp implements RegisterService {

	@Autowired
	public UserDao userDao;

	private static final Logger LOG = LogManager.getLogger(RegisterServiceImp.class);

	public UserDto registerUser(UserCredentials user) {
		try {
			return userDao.saveUser(user);

		}
		catch (ResourceNotFoundException re) {
			// throw a dao exception if occurs
			throw re;
		}
		catch (Exception e) {
			LOG.error("Excpeton occurs in save user service" + e);
			throw new ExpenseException("some exception occured while saving user");
		}
	}

	// Not in use
	public UserDto getUserById(String userId)

	{
		return null;
		// return userDao.getUserById();
	}

	public UserDto UpdateUser(UserCredentials user) {
		try {

			return userDao.UpdateUserPassword(user);

		}
		catch (ResourceNotFoundException re) {
			// throw a dao exception if occurs
			throw re;
		}
		catch (Exception e) {
			LOG.error("Excpeton occurs in updae user password service" + e);
			throw new ExpenseException("some exception occured while updating password of user");
		}

	}

	public String RemoveUserAcc(String userId) {
		try {
			return userDao.deleteUserAccount(userId);
		}
		catch (ResourceNotFoundException re) {
			// throw a dao exception if occurs
			throw re;
		}
		catch (Exception e) {
			LOG.error("Excpeton occurs in deleting user service" + e);
			throw new ExpenseException("exception occured while deleting user account");
		}
	}
}
