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

import com.expense.manage.ExpenseManagement.model.UserCredentials;
import com.expense.manage.ExpenseManagement.service.RegisterService;

@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	public RegisterService regService;

	private static final Logger LOG = LogManager.getLogger(LoginController.class);

	@GetMapping("/login_form.html")
	public ModelAndView getLoginForm() {

		ModelAndView mv = new ModelAndView("jsp/login_page.jsp");
		return mv;
	}
	// Register user

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public ResponseEntity<UserCredentials> registerUserDetails(@Valid
	@RequestBody
	UserCredentials user) {
		// ResponseEntity<UserCredentials> response = null;
		LOG.info("In the Login Controller Of register user method");

		UserCredentials response = regService.registerUser(user);

		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public ResponseEntity<UserCredentials> updateUserDetails(@Valid
	@RequestBody
	UserCredentials user) {

		LOG.info("In the Login Controller Of update user method");

		UserCredentials response = regService.UpdateUser(user);

		return ResponseEntity.ok().body(response);
	}

	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	public ResponseEntity<String> removeUserAccount(@Valid
	@RequestParam(value = "id", required = true)
	String userId) {

		LOG.info("In the Login Controller Of Delete user account method");

		String response = regService.RemoveUserAcc(userId);

		return ResponseEntity.ok().body(response);
	}
}
