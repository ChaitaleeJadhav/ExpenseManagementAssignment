package com.expense.manage.ExpenseManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return expenseDao.getAllExenseById(userId);
	}

	@Override
	public ExpenseDetails updateExpenseDetails(ExpenseDetails details) {
		// TODO Auto-generated method stub
		return expenseDao.updateExpense(details);
	}

	@Override
	public Map<String, String> showSummaryExpense(String userId) {
		// TODO Auto-generated method stub
		double total = 0;
		Map<String, String> listOfExpense = new HashMap<String, String>();
		List<Object[]> list = expenseDao.getSummaryOfExpenseById(userId);
		for (Object[] array : list) {
			// here at 0 index of Object Array we have maonth and Year and
			// at first index we have amount
			// This sequence fetch in select query
			listOfExpense.put(array[0].toString(), array[1].toString());
			total = total + Double.valueOf(array[1].toString());

		}
		listOfExpense.put("Total :", Double.toString(total));
		return listOfExpense;
	}

}
