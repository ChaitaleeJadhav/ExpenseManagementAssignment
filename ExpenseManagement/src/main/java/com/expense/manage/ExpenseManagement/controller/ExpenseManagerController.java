package com.expense.manage.ExpenseManagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.expense.manage.ExpenseManagement.service.ExpenseMangService;

@RestController
@RequestMapping("/expense")
public class ExpenseManagerController {

	@Autowired
	public ExpenseMangService expnseService;
	private static final Logger LOG = LogManager.getLogger(ExpenseManagerController.class);

	@RequestMapping(value = "/summaryExpense", method = RequestMethod.POST)
	public ResponseEntity<Object> addExpenseDetails(@Valid
	@RequestBody
	ExpenseDetails details) {
		System.out.println("In Expense Conroller");
		LOG.info("In the ExpenseManagerController Controller Of Add Expense details method");
		ExpenseDetails expenseDetails = expnseService.addExpenseDetails(details);
		Object response = expenseDetails;
		if (expenseDetails == null) {
			response = "Expense details is not added";
		}

		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/getAllExpenses", method = RequestMethod.GET)
	public ResponseEntity<Object> addExpenseDetails(@Valid
	@RequestBody
	String userId) {
		System.out.println("In Expense Conroller");
		LOG.info("In the ExpenseManagerController Controller Of get All Expense details method");
		List<ExpenseDetails> expenseList = expnseService.getAllExpenseByUserId(userId);
		Object response = expenseList;
		if (expenseList == null) {
			response = "Expense details is not found";
		}

		return ResponseEntity.ok().body(response);
	}

}
