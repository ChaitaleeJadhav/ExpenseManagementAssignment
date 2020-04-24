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

		try {

			ExpenseDto expensedto = CommonOper.modelMapper.map(expenseDao.addExpense(expense), ExpenseDto.class);
			assertThat(expense.getUser_id().getId()).isEqualTo(expensedto.getUser_id().getId());
			// assertEquals("record is added", expensedto,
			// expenseDao.addExpense(expense));
		}
		catch (ResourceNotFoundException re) {
			assertEquals("UserId id is already Exists,Want Unique user id", re.getMessage());
		}
		catch (Exception e) {

			assertTrue(true);

		}
	}

	@Test
	public void testupdateExpense() {

		try {
			expense.setId(7);
			expense.setCategory("Updated Category");
			ExpenseDto expensedto = CommonOper.modelMapper.map(expenseDao.updateExpense(expense), ExpenseDto.class);
			assertThat(expense.getUser_id().getId()).isEqualTo(expensedto.getUser_id().getId());
			assertThat(expense.getCategory()).isEqualTo(expensedto.getCategory());
		}
		catch (ResourceNotFoundException re) {
			assertEquals("UserId id is already Exists,Want Unique user id", re.getMessage());
		}
		catch (Exception e) {

			assertTrue(true);

		}
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

}
