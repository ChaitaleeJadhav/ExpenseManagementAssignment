package com.expense.manage.ExpenseManagement.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expense.manage.ExpenseManagement.model.UserCredentials;

@Repository("userdao")
public class UserDao {

	@Autowired
	public RegisterRepository regrpo;

	public UserCredentials saveUser(UserCredentials user) {
		return regrpo.save(user);
	}

	public Optional<UserCredentials> saveUser(String userId) {
		return regrpo.findById(userId);
	}

}
