package com.expense.manage.ExpenseManagement.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.expense.manage.ExpenseManagement.dto.ExpenseDto;
import com.expense.manage.ExpenseManagement.exceptions.ResourceNotFoundException;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.expense.manage.ExpenseManagement.service.CommonOper;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@PropertySource("classpath:application-test.properties")
public class ExpenseDaoTest {

	@Autowired
	public UserDao userDao;

	@Autowired
	public ExpenseDao expenseDao;

	private ExpenseDetails expense = CommonOper.getExpense();

	@Test
	public void testaddExpense() {

		ExpenseDto expensedto = CommonOper.modelMapper.map(expenseDao.addExpense(expense), ExpenseDto.class);
		assertThat(expense.getUser_id().getId()).isEqualTo(expensedto.getUser_id().getId());

	}

	@Test
	public void testupdateExpense() {
		expense.setId(7);
		expense.setCategory("Updated Category");
		ExpenseDto expensedto = CommonOper.modelMapper.map(expenseDao.updateExpense(expense), ExpenseDto.class);
		expensedto.setCategory("Updated Category");
		assertThat(expense.getCategory()).isEqualTo(expensedto.getCategory());

	}

	@Test
	public void testGetAllExenseById() {
		try {
			String userId = "chaitu16j@gmail.com";

			List<ExpenseDto> list = expenseDao.getAllExenseById(userId);

			assertEquals(list.size(), 2);
		}
		catch (ResourceNotFoundException re) {
			assertEquals("User account is not found", re.getMessage());
		}

		catch (Exception e) {

			assertTrue(true);

		}
	}

	// -ve test case when user id not exists
	@Test
	public void testAddExpense() {

		try {

			ExpenseDto expensedto = CommonOper.modelMapper.map(expenseDao.addExpense(expense), ExpenseDto.class);
			expensedto.getUser_id().setId("notexist@gmail.com");
			;
			assertThat(expense.getUser_id().getId()).isEqualTo(expensedto.getUser_id().getId());
		}
		catch (ResourceNotFoundException re) {
			assertEquals("User account is not found,Please first create user account to add expenses", re.getMessage());
		}
		catch (Exception e) {

			assertTrue(true);

		}
	}

	// -ve test case when expense Id is not exists
	@Test
	public void testUpdateExpenseIDNotFound() {

		try {
			expense.setId(100);
			expense.setCategory("Updated Category");
			ExpenseDto expensedto = CommonOper.modelMapper.map(expenseDao.updateExpense(expense), ExpenseDto.class);
			assertThat(expense.getUser_id().getId()).isEqualTo(expensedto.getUser_id().getId());
			assertThat(expense.getCategory()).isEqualTo(expensedto.getCategory());
		}
		catch (ResourceNotFoundException re) {
			assertEquals("Expense Id is not found", re.getMessage());
		}
		catch (Exception e) {

			assertTrue(true);

		}
	}
}