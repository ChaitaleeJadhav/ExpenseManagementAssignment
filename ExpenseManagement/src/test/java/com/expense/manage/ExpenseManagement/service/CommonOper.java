package com.expense.manage.ExpenseManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.expense.manage.ExpenseManagement.dto.ExpenseDto;
import com.expense.manage.ExpenseManagement.dto.UserDto;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.expense.manage.ExpenseManagement.model.UserCredentials;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonOper {

	public static UserCredentials userDetail;
	public static UserDto userDto;
	public static ExpenseDetails expenseDetails;
	public static ModelMapper modelMapper = new ModelMapper();
	public static ExpenseDto expenseDto;
	public static List<ExpenseDetails> listExpense;

	public static List<ExpenseDto> listExpenseDto;

	@BeforeClass
	public static void craeteUser() {

		UserCredentials user = new UserCredentials();
		user.setId("subi19990j@gmail.com");
		user.setPassword("password");
		userDetail = user;
		userDto = modelMapper.map(user, UserDto.class);
	}

	@BeforeClass
	public static UserCredentials getUser() {
		UserCredentials user = new UserCredentials();

		user.setId("chaitalee16j@gmail.com");
		user.setPassword("passw");
		return user;
	}

	@BeforeClass
	public static void craeteExpense() {

		listExpenseDto = new ArrayList<ExpenseDto>();
		listExpense = new ArrayList<ExpenseDetails>();
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
		ExpenseDetails expense2 = new ExpenseDetails();
		expense2.setId(8);
		expense2.setAmount(24.6);
		expense2.setCategory("phone");
		expense2.setContact("87609835");
		expense2.setDescription("Mobile Iphone 5");
		expense2.setTitle("Iphone");
		expense2.setUser_id(user_id);
		expenseDetails = expense;
		expenseDto = modelMapper.map(expense, ExpenseDto.class);
		listExpense.add(expense);
		listExpense.add(expense2);
		for (ExpenseDetails l : listExpense) {
			ExpenseDto dto = modelMapper.map(l, ExpenseDto.class);
			listExpenseDto.add(dto);
		}
	}

	@BeforeClass
	public static ExpenseDetails getExpense() {
		ExpenseDetails expense = new ExpenseDetails();
		expense.setAmount(12.1);
		expense.setCategory("phone");
		expense.setContact("8760");
		expense.setDescription("description");
		expense.setTitle("Redmi");
		// Date date = new Date("2020-02-13");
		// expense.setExpenseDate(date);
		UserCredentials user_id = new UserCredentials();
		user_id.setId("chaitu16j@gmail.com");
		expense.setUser_id(user_id);
		return expense;
	}

}
