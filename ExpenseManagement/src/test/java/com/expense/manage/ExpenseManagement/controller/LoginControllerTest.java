package com.expense.manage.ExpenseManagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.expense.manage.ExpenseManagement.model.UserCredentials;
import com.expense.manage.ExpenseManagement.service.CommonOper;
import com.expense.manage.ExpenseManagement.service.RegisterService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LoginController.class)
public class LoginControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	public RegisterService regService;

	@Test
	public void testCreateTicket() throws Exception {

		CommonOper.craeteUser();
		String inputInJson = CommonOper.mapToJson(CommonOper.userDetail);
		String responeInJson = CommonOper.mapToJson(CommonOper.userDto);

		String URI = "/login/registerUser";

		Mockito.when(regService.registerUser(Mockito.any(UserCredentials.class))).thenReturn(CommonOper.userDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(responeInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	// Test for Update
	@Test
	public void updatePasswordTest() throws Exception {

		String inputInJson = CommonOper.mapToJson(CommonOper.userDetail);
		String responeInJson = CommonOper.mapToJson(CommonOper.userDto);

		String URI = "/login/updatePassword";

		Mockito.when(regService.UpdateUser(Mockito.any(UserCredentials.class))).thenReturn(CommonOper.userDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(responeInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	// Test case for Delete
	@Test
	public void removeUserAccountTest() throws Exception {

		String URI = "/login/deleteAccount?id=subi19990j@gmail.com";

		Mockito.when(regService.RemoveUserAcc(Mockito.any(String.class)))
				.thenReturn("User Account deleted succesfully");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo("User Account deleted succesfully");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	// -ve TestCase to check url for bad Request
	public void ToCheckBadRequestTest() throws Exception {

		String URI = "/login/loginUser";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

	}
}