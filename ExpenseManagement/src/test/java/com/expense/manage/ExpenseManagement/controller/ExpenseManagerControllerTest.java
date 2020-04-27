package com.expense.manage.ExpenseManagement.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.BeforeClass;
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

import com.expense.manage.ExpenseManagement.mail.MailService;
import com.expense.manage.ExpenseManagement.mail.ThreadService;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.expense.manage.ExpenseManagement.service.CommonOper;
import com.expense.manage.ExpenseManagement.service.ExpenseMangService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = ExpenseManagerController.class)
public class ExpenseManagerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	public ExpenseMangService expnseService;

	@MockBean
	private MailService mailService;

	@MockBean
	private ThreadService threadService;

	@BeforeClass
	public static void callToMethods() {
		CommonOper.craeteExpense();
		CommonOper.updateExpense();
		CommonOper.updateExpenseIdNull();

	}

	@Test
	public void testAddExpenseDetails() throws Exception {

		String inputInJson = CommonOper.mapToJson(CommonOper.expenseDetails);
		String responeInJson = CommonOper.mapToJson(CommonOper.expenseDto);

		String URI = "/expense/addExpense";

		Mockito.when(expnseService.addExpenseDetails(Mockito.any(ExpenseDetails.class)))
				.thenReturn(CommonOper.expenseDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(responeInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	// test case to update expense
	@Test
	public void testUpdateExpenseDetails() throws Exception {
		String inputInJson = CommonOper.mapToJson(CommonOper.updateExpense);
		CommonOper.expenseDto.setCategory("Updated license OS");
		CommonOper.expenseDto.setDescription("updated description");
		String responeInJson = CommonOper.mapToJson(CommonOper.expenseDto);

		String URI = "/expense/updateExpense";

		Mockito.when(expnseService.updateExpenseDetails(Mockito.any(ExpenseDetails.class)))
				.thenReturn(CommonOper.expenseDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(responeInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	// test case to get summary expense
	@Test
	public void testSummaryExpense() throws Exception {

		Map<String, String> resObj = new LinkedHashMap<String, String>();
		String responeInJson = CommonOper.mapToJson(resObj);

		String URI = "/expense/summaryExpense?id=chaitu16j@gmail.com";

		Mockito.when(expnseService.showSummaryExpense(Mockito.any(String.class))).thenReturn(resObj);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(responeInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	// test case to all expenses by UserId
	@Test
	public void testGetAllExpenses() throws Exception {

		String responeInJson = CommonOper.mapToJson(CommonOper.listExpenseDto);

		String URI = "/expense/getAllExpenses?id=chaitu16j@gmail.com";

		Mockito.when(expnseService.getAllExpenseByUserId(Mockito.any(String.class)))
				.thenReturn(CommonOper.listExpenseDto);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(responeInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	// -negative test case body is not passed to add expense
	@Test
	public void negativeTestAddExpenseDetails() throws Exception {

		String URI = "/expense/addExpense";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

	}

	// -negative test case for update when expense Id is not provided
	@Test
	public void negativeUpdateExpenseIdNull() throws Exception {

		String inputInJson = CommonOper.mapToJson(CommonOper.updateExpenseIdNull);

		String URI = "/expense/updateExpense";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getErrorMessage();
		assertEquals("outputInJson", "Expense Id can not be null,Please provide Expense Id to Update");

		assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());

	}

}
