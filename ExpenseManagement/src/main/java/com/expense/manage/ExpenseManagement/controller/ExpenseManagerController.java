package com.expense.manage.ExpenseManagement.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.expense.manage.ExpenseManagement.service.ExpenseMangService;

@RestController
@RequestMapping("/expense")
public class ExpenseManagerController {

	@Autowired
	public ExpenseMangService expnseService;
	private static final Logger LOG = LogManager.getLogger(ExpenseManagerController.class);

	@RequestMapping(value = "/addExpense", method = RequestMethod.POST)
	public ResponseEntity<ExpenseDetails> addExpenseDetails(@Valid
	@RequestBody
	ExpenseDetails details) {
		LOG.info("In the ExpenseManagerController Controller Of Add Expense details method");
		ExpenseDetails response = expnseService.addExpenseDetails(details);

		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/getAllExpenses", method = RequestMethod.GET)
	public ResponseEntity<Object> addExpenseDetails(@Valid
	@RequestParam(value = "id", required = true)
	String userId) {
		LOG.info("In the ExpenseManagerController Controller Of get All Expense details method");
		List<ExpenseDetails> expenseList = expnseService.getAllExpenseByUserId(userId);
		Object response = expenseList;
		if (expenseList == null) {
			response = "Expense details is not found";
		}

		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/updateExpense", method = RequestMethod.POST)
	public ResponseEntity<ExpenseDetails> updateExpenseDetails(@Valid
	@RequestBody
	ExpenseDetails details) {
		LOG.info("In the ExpenseManagerController Controller Of Update Expense details method");
		ExpenseDetails response = expnseService.updateExpenseDetails(details);
		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/summaryExpense", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> summaryExpenseDetails(@Valid
	@RequestParam(value = "id", required = true)
	String userId) {
		LOG.info("In the ExpenseManagerController Controller Of get Summary of Expense month wise details method");
		Map<String, String> response = expnseService.showSummaryExpense(userId);
		return ResponseEntity.ok().body(response);
	}

}
