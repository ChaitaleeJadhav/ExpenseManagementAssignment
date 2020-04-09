package com.expense.manage.ExpenseManagement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expense.manage.ExpenseManagement.model.ExpenseDetails;

@Repository("expenseDao")
public class ExpenseDao {

	// @Autowired
	// public UserCredentials userCredentials;

	@Autowired
	public ExpenseRepository expenserpo;

	public ExpenseDetails addExpense(ExpenseDetails details) {
		return expenserpo.save(details);
	}

	public List<ExpenseDetails> getAllExenseById(String userId) {
		// userCredentials.setId(userId);
		// return expenserpo.findExpensesUserId(userId);
		return null;
	}

}
