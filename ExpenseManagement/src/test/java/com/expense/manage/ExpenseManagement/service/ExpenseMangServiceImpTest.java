package com.expense.manage.ExpenseManagement.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.expense.manage.ExpenseManagement.dao.ExpenseDao;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.expense.manage.ExpenseManagement.model.UserCredentials;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ExpenseMangServiceImp.class })
public class ExpenseMangServiceImpTest {

	@Autowired
	private ExpenseMangService expnseService;

	@MockBean
	public ExpenseDao expenseDao;

	@Test
	public void testAddExpense() {
		CommonOper.craeteExpense();

		Mockito.when(expenseDao.addExpense(CommonOper.expenseDetails)).thenReturn(CommonOper.expenseDto);

		assertThat(expnseService.addExpenseDetails(CommonOper.expenseDetails)).isEqualTo(CommonOper.expenseDto);

	}

	@Test
	public void updateExpense() {

		Mockito.when(expenseDao.addExpense(CommonOper.expenseDetails)).thenReturn(CommonOper.expenseDto);
		CommonOper.expenseDetails.setCategory("Pc");

		Mockito.when(expenseDao.updateExpense(CommonOper.expenseDetails)).thenReturn(CommonOper.expenseDto);

		assertThat(expnseService.updateExpenseDetails(CommonOper.expenseDetails)).isEqualTo(CommonOper.expenseDto);

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

		Mockito.when(expenseDao.getAllExenseById(user_id.getId())).thenReturn(CommonOper.listExpenseDto);

		assertThat(expnseService.getAllExpenseByUserId(user_id.getId())).isEqualTo(CommonOper.listExpenseDto);

	}

}
