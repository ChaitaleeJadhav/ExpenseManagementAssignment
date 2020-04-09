package com.expense.manage.ExpenseManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.manage.ExpenseManagement.dao.ExpenseDao;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;

@Service("expnseService")
public class ExpenseMangServiceImp implements ExpenseMangService {

	@Autowired
	public ExpenseDao expenseDao;

	@Override
	public ExpenseDetails addExpenseDetails(ExpenseDetails details) {
		// TODO Auto-generated method stub
		return expenseDao.addExpense(details);
	}

	@Override
	public List<ExpenseDetails> getAllExpenseByUserId(String userId) {
		// TODO Auto-generated method stub
		// return expenseDao.getAllExenseById(userId);
		return null;
	}

}
