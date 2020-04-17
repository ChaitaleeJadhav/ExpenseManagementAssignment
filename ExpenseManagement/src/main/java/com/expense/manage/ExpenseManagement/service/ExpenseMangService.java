package com.expense.manage.ExpenseManagement.service;

import java.util.List;
import java.util.Map;

import com.expense.manage.ExpenseManagement.dto.ExpenseDto;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;

public interface ExpenseMangService {

	// Method to add expense details

	public ExpenseDto addExpenseDetails(ExpenseDetails details);

	// get all expnse by userId
	public List<ExpenseDto> getAllExpenseByUserId(String userId);

	// get Summary of Expnse month wise
	public Map<String, String> showSummaryExpense(String userId);

	// Update Expense details by Id
	public ExpenseDto updateExpenseDetails(ExpenseDetails details);
}
