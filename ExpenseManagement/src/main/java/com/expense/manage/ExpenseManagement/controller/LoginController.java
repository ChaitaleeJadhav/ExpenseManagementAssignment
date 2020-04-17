package com.expense.manage.ExpenseManagement.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.expense.manage.ExpenseManagement.dto.UserDto;
import com.expense.manage.ExpenseManagement.exceptions.ResourceNotFoundException;
import com.expense.manage.ExpenseManagement.exceptions.UserException;
import com.expense.manage.ExpenseManagement.model.UserCredentials;
import com.expense.manage.ExpenseManagement.service.RegisterService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	public RegisterService regService;

	private static final Logger LOG = LogManager.getLogger(LoginController.class);

	// Not in use
	@GetMapping("/login_form.html")
	public ModelAndView getLoginForm() {

		ModelAndView mv = new ModelAndView("jsp/login_page.jsp");
		return mv;

	}

	// Register user
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ResponseEntity<UserDto> registerUserDetails(@Valid
	@RequestBody
	UserCredentials user) {
		// ResponseEntity<UserCredentials> response = null;
		try {
			LOG.info("In the Login Controller Of register user method");

			UserDto response = regService.registerUser(user);

			return ResponseEntity.ok().body(response);
		}
		catch (Exception e) {
			LOG.error("Excpeton occurs in User Login controller while registration process" + e);
			throw new UserException("some exception occured while updating password of user");
		}
	}

	// Update user
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public ResponseEntity<UserDto> updateUserDetails(@Valid
	@RequestBody
	UserCredentials user) {
		try {
			LOG.info("In the Login Controller Of update user method");
			if (user.getId() != null) {
				UserDto response = regService.UpdateUser(user);
				return ResponseEntity.ok().body(response);
			}
			else {
				throw new ResourceNotFoundException("User Id can not be null,Please provide User Id to Update");
			}
		}
		catch (Exception e) {
			LOG.error("Excpeton occurs in User Login controller while updating password of process" + e);
			throw new UserException("some exception occured while updating password of user");
		}
	}

	// Delete account
	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	public ResponseEntity<String> removeUserAccount(@Valid
	@RequestParam(value = "id", required = true)
	String userId) {
		try {
			LOG.info("In the Login Controller Of Delete user account method");

			String response = regService.RemoveUserAcc(userId);

			return ResponseEntity.ok().body(response);

		}
		catch (Exception e) {
			LOG.error("Excpeton occurs in User Login controller while updating password of process" + e);
			throw new UserException("some exception occured while deleting user" + userId);
		}
	}
}
