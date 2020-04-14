package com.expense.manage.ExpenseManagement.service;

import java.util.List;
import java.util.Map;

import com.expense.manage.ExpenseManagement.model.ExpenseDetails;

public interface ExpenseMangService {

	// Method to add expense details

	public ExpenseDetails addExpenseDetails(ExpenseDetails details);

	// get all expnse by userId
	public List<ExpenseDetails> getAllExpenseByUserId(String userId);

	// get Summary of Expnse month wise
	public Map<String, String> showSummaryExpense(String userId);

	// Update Expense details by Id
	public ExpenseDetails updateExpenseDetails(ExpenseDetails details);
}
