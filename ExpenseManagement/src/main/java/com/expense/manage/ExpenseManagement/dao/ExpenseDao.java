package com.expense.manage.ExpenseManagement.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expense.manage.ExpenseManagement.dto.ExpenseDto;
import com.expense.manage.ExpenseManagement.exceptions.ResourceNotFoundException;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.expense.manage.ExpenseManagement.service.ModelMapperService;

@Repository("expenseDao")
public class ExpenseDao {

	@Autowired
	public ModelMapperService modelMapperService;

	@Autowired
	public ExpenseRepository expenserpo;

	@Autowired
	public RegisterRepository regrpo;

	private static final Logger LOG = LogManager.getLogger(ExpenseDao.class);

	public ExpenseDto addExpense(ExpenseDetails details) {
		String userId = details.getUser_id().getId();
		if (regrpo.existsById(userId)) {
			ExpenseDetails resultExpense = expenserpo.save(details);
			return modelMapperService.mappedToExpenseDto(resultExpense);
		}
		else {
			LOG.error("User Account not exists");
			throw new ResourceNotFoundException(
					"User account is not found,Please first create user account to add expenses");

		}
	}

	public List<ExpenseDto> getAllExenseById(String userId) {

		if (regrpo.existsById(userId)) {
			List<ExpenseDetails> expnseList = expenserpo.getExpenseDetailsById(userId);
			List<ExpenseDto> resultList = expnseList.stream().map(modelMapperService::mappedToExpenseDto)
					.collect(Collectors.toList());
			return resultList;

		}
		else {
			LOG.error("User Account not exists ");
			throw new ResourceNotFoundException("User account is not found");

		}

	}

	public ExpenseDto updateExpense(ExpenseDetails details) {

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
			ExpenseDetails resultExpense = expenserpo.save(updateDetails);
			return modelMapperService.mappedToExpenseDto(resultExpense);
		}
		else {
			LOG.error("Expense Id not Found");
			throw new ResourceNotFoundException("Expense Id is not found");
		}

	}

	public List<Object[]> getSummaryOfExpenseById(String userId) {

		if (regrpo.existsById(userId)) {
			List<Object[]> expnseList = expenserpo.getSummaryExpenseById(userId);

			return expnseList;
		}
		else {
			LOG.error("User Account not exists ");
			throw new ResourceNotFoundException("User account is not found");

		}
	}
}
