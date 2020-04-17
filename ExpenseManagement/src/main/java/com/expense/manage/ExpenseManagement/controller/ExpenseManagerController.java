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

import com.expense.manage.ExpenseManagement.dto.ExpenseDto;
import com.expense.manage.ExpenseManagement.exceptions.ExpenseException;
import com.expense.manage.ExpenseManagement.exceptions.ResourceNotFoundException;
import com.expense.manage.ExpenseManagement.mail.ThreadService;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.expense.manage.ExpenseManagement.service.ExpenseMangService;

@RestController
@RequestMapping("/expense")
public class ExpenseManagerController {

	@Autowired
	private ThreadService threadService;

	@Autowired
	public ExpenseMangService expnseService;
	private static final Logger LOG = LogManager.getLogger(ExpenseManagerController.class);

	@RequestMapping(value = "/addExpense", method = RequestMethod.POST)
	public ResponseEntity<ExpenseDto> addExpenseDetails(@Valid
	@RequestBody
	ExpenseDetails details) {
		try {
			LOG.info("In the ExpenseManagerController Controller Of Add Expense details method");
			ExpenseDto response = expnseService.addExpenseDetails(details);
			return ResponseEntity.ok().body(response);
		}
		catch (Exception e) {
			LOG.error("Excpeton occurs in ExpenseManagerController adding expense to user" + e);
			throw new ExpenseException("some exception occured while adding expense to user");
		}

	}

	@RequestMapping(value = "/getAllExpenses", method = RequestMethod.GET)
	public ResponseEntity<List<ExpenseDto>> getExpenseDetailsById(@Valid
	@RequestParam(value = "id", required = true)
	String userId) {
		LOG.info("In the ExpenseManagerController Controller Of get All Expense details method");
		List<ExpenseDto> response = expnseService.getAllExpenseByUserId(userId);

		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/updateExpense", method = RequestMethod.POST)
	public ResponseEntity<ExpenseDto> updateExpenseDetails(@Valid
	@RequestBody
	ExpenseDetails details) {
		LOG.info("In the ExpenseManagerController Controller Of Update Expense details method");
		if (details.getId() != null) {
			ExpenseDto response = expnseService.updateExpenseDetails(details);

			return ResponseEntity.ok().body(response);
		}
		else {
			throw new ResourceNotFoundException("Expense Id can not be null,Please provide Expense Id to Update");
		}
	}

	@RequestMapping(value = "/summaryExpense", method = RequestMethod.GET)
	public ResponseEntity<Map<String, String>> summaryExpenseDetails(@Valid
	@RequestParam(value = "id", required = true)
	String userId) {
		try {
			LOG.info("In the ExpenseManagerController Controller Of get Summary of Expense month wise details method");
			Map<String, String> response = expnseService.showSummaryExpense(userId);
			return ResponseEntity.ok().body(response);
		}
		catch (Exception e) {
			LOG.error("Excpeton occurs in ExpenseManagerController whilegetting summary of expense" + e);
			throw new ExpenseException("some exception occured while getting summary of expense" + userId);
		}

	}

	@RequestMapping(value = "/mailSummary", method = RequestMethod.POST)
	public void senMail(@RequestParam(value = "id", required = true)
	String userId) {

		try {
			System.out.println("In the controller of mail");
			threadService.callToMail(userId);
		}
		catch (Exception e) {
			System.out.println(e);
		}
	}

}
