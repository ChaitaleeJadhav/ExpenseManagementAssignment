package com.expense.manage.ExpenseManagement.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.expense.manage.ExpenseManagement.dao.ExpenseDao;
import com.expense.manage.ExpenseManagement.dto.ExpenseDto;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.expense.manage.ExpenseManagement.model.UserCredentials;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ExpenseMangServiceImp.class })
public class ExpenseMangServiceImpTest {

	@Autowired
	private ExpenseMangService expnseService;

	private static ExpenseDetails expenseDetails;

	private static ExpenseDto expenseDto;

	private static List<ExpenseDto> listExpenseDto;

	@MockBean
	public ExpenseDao expenseDao;

	@BeforeClass
	public static void craeteExpense() {
		ModelMapper modelMapper = new ModelMapper();
		List<ExpenseDto> list = new ArrayList<ExpenseDto>();
		ExpenseDetails expense = new ExpenseDetails();
		expense.setId(7);
		expense.setAmount(2234.67);
		expense.setCategory("phone");
		expense.setContact("87609835334");
		expense.setDescription("description");
		expense.setTitle("Redmi");
		// Date date = new Date;
		// expense.setExpenseDate(date);
		UserCredentials user_id = new UserCredentials();
		user_id.setId("chaitu16j@gmail.com");
		expense.setUser_id(user_id);

		expenseDetails = expense;
		expenseDto = modelMapper.map(expense, ExpenseDto.class);
		list.add(expenseDto);

	}

	@Test
	public void testAddExpense() {

		Mockito.when(expenseDao.addExpense(expenseDetails)).thenReturn(expenseDto);

		assertThat(expnseService.addExpenseDetails(expenseDetails)).isEqualTo(expenseDto);

	}

	@Test
	public void updateExpense() {

		Mockito.when(expenseDao.addExpense(expenseDetails)).thenReturn(expenseDto);
		expenseDetails.setCategory("Pc");

		Mockito.when(expenseDao.updateExpense(expenseDetails)).thenReturn(expenseDto);

		assertThat(expnseService.updateExpenseDetails(expenseDetails)).isEqualTo(expenseDto);

	}

	@Test
	public void getAllExpenseByUserId() {
		List<ExpenseDetails> list = new ArrayList<ExpenseDetails>();

		ExpenseDetails expense = new ExpenseDetails();
		expense.setId(6);
		expense.setAmount(22.67);
		expense.setCategory("phone");
		expense.setContact("87609835334");
		expense.setDescription("description");
		expense.setTitle("Redmi");
		// Date date = new Date;
		// expense.setExpenseDate(date);
		UserCredentials user_id = new UserCredentials();
		user_id.setId("chaitalee@gmail.com");
		expense.setUser_id(user_id);
		list.add(expense);

		Mockito.when(expenseDao.getAllExenseById(user_id.getId())).thenReturn(listExpenseDto);

		assertThat(expnseService.getAllExpenseByUserId(user_id.getId())).isEqualTo(listExpenseDto);

	}

}
