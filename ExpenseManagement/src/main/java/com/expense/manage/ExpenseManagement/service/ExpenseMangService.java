package com.expense.manage.ExpenseManagement.service;

import java.util.List;

import com.expense.manage.ExpenseManagement.model.ExpenseDetails;

public interface ExpenseMangService {

	// Method to add expense details

	public ExpenseDetails addExpenseDetails(ExpenseDetails details);

	// get all expnse by userId
	public List<ExpenseDetails> getAllExpenseByUserId(String userId);

	// get Summary of Expnse month wise
}
