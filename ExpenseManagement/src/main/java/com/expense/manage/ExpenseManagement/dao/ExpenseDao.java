package com.expense.manage.ExpenseManagement.dao;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expense.manage.ExpenseManagement.exceptions.ResourceNotFoundException;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;

@Repository("expenseDao")
public class ExpenseDao {

	@Autowired
	public ExpenseRepository expenserpo;

	@Autowired
	public RegisterRepository regrpo;

	private static final Logger LOG = LogManager.getLogger(ExpenseDao.class);

	public ExpenseDetails addExpense(ExpenseDetails details) {
		return expenserpo.save(details);
	}

	public List<ExpenseDetails> getAllExenseById(String userId) {

		List<ExpenseDetails> expnseList = expenserpo.getExpenseDetailsById(userId);
		return expnseList;

	}

	public ExpenseDetails updateExpense(ExpenseDetails details) {

		Optional<ExpenseDetails> update = expenserpo.findById(details.getId());

		if (update.isPresent()) {
			ExpenseDetails updateDetails = update.get();
			if (details.getAmount() != null) {
				updateDetails.setAmount(details.getAmount());
			}
			if (details.getCategory() != null) {
				updateDetails.setCategory(details.getCategory());
			}
			if (details.getContact() != null) {
				updateDetails.setContact(details.getContact());
			}
			if (details.getExpenseDate() != null) {
				updateDetails.setExpenseDate(details.getExpenseDate());
			}
			if (details.getTitle() != null) {
				updateDetails.setTitle(details.getTitle());
			}
			return expenserpo.save(details);
		}
		else {
			LOG.error("Expense Id not Found");
			throw new ResourceNotFoundException("Expense Id is not found");
		}

	}
}
