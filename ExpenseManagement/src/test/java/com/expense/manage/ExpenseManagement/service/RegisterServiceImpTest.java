package com.expense.manage.ExpenseManagement.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.expense.manage.ExpenseManagement.dao.UserDao;
import com.expense.manage.ExpenseManagement.dto.UserDto;
import com.expense.manage.ExpenseManagement.model.UserCredentials;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RegisterServiceImp.class })
public class RegisterServiceImpTest {

	@Autowired
	private RegisterService regService;

	@MockBean
	public UserDao userdao;

	public static UserCredentials userDetail;
	public static UserDto userDto;

	@BeforeClass
	public static void craeteExpense() {
		ModelMapper modelMapper = new ModelMapper();
		UserCredentials user = new UserCredentials();
		user.setId("subi19990j@gmail.com");
		user.setPassword("password");
		userDetail = user;
		userDto = modelMapper.map(user, UserDto.class);
	}

	@Test
	public void testAddUser() {

		Mockito.when(userdao.saveUser(userDetail)).thenReturn(userDto);

		assertThat(regService.registerUser(userDetail)).isEqualTo(userDto);

	}

	@Test
	public void testUpdatePasswod() {

		Mockito.when(userdao.UpdateUserPassword(userDetail)).thenReturn(userDto);

		assertThat(regService.UpdateUser(userDetail)).isEqualTo(userDto);

	}

}
