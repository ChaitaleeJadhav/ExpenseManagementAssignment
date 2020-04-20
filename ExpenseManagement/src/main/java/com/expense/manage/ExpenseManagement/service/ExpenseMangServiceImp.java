package com.expense.manage.ExpenseManagement.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.manage.ExpenseManagement.dao.ExpenseDao;
import com.expense.manage.ExpenseManagement.dto.ExpenseDto;
import com.expense.manage.ExpenseManagement.exceptions.ExpenseException;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;

@Service("expnseService")
public class ExpenseMangServiceImp implements ExpenseMangService {

	@Autowired
	public ExpenseDao expenseDao;

	private static final Logger LOG = LogManager.getLogger(ExpenseMangServiceImp.class);

	@Override
	public ExpenseDto addExpenseDetails(ExpenseDetails details) {
		// TODO Auto-generated method stub
		try {
			return expenseDao.addExpense(details);
		}
		catch (Exception e) {
			LOG.error("Excpeton occurs while to  addExpenseDetails service" + e);
			throw new ExpenseException("something went wrong when adding a expense");
		}
	}

	@Override
	public List<ExpenseDto> getAllExpenseByUserId(String userId) {
		try {
			// TODO Auto-generated method stub
			return expenseDao.getAllExenseById(userId);

		}
		catch (Exception e) {
			LOG.error("Excpeton occurs in getAllExpenseByUserId service" + e);
			throw new ExpenseException("something went wrong when getting a expenses");
		}
	}

	@Override
	public ExpenseDto updateExpenseDetails(ExpenseDetails details) {
		// TODO Auto-generated method stub
		try {
			return expenseDao.updateExpense(details);
		}
		catch (Exception e) {
			LOG.error("Excpeton occurs while in  updateExpenseDetails service" + e);
			throw new ExpenseException("something went wrong when getting a expenses");
		}
	}

	@Override
	public Map<String, String> showSummaryExpense(String userId) {
		// TODO Auto-generated method stub
		try {
			double total = 0;
			Map<String, String> listOfExpense = new LinkedHashMap<String, String>();
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
		catch (ArithmeticException ar) {
			LOG.error("Excpeton occurs while in  summaryExpense service" + ar);
			throw new ExpenseException("something went wrong when getting a expenses");
		}
		catch (Exception e) {
			LOG.error("Excpeton occurs while in  summaryExpense service" + e);
			e.printStackTrace();
			throw new ExpenseException("something went wrong calculating summary of expenses");
		}
	}

}
