package com.expense.manage.ExpenseManagement.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.expense.manage.ExpenseManagement.dao.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RegisterServiceImp.class })
public class RegisterServiceImpTest {

	@Autowired
	private RegisterService regService;

	@MockBean
	public UserDao userdao;

	@Test
	public void testAddUser() {

		CommonOper.craeteUser();
		Mockito.when(userdao.saveUser(CommonOper.userDetail)).thenReturn(CommonOper.userDto);

		assertThat(regService.registerUser(CommonOper.userDetail)).isEqualTo(CommonOper.userDto);

	}

	@Test
	public void testUpdatePasswod() {

		Mockito.when(userdao.UpdateUserPassword(CommonOper.userDetail)).thenReturn(CommonOper.userDto);

		assertThat(regService.UpdateUser(CommonOper.userDetail)).isEqualTo(CommonOper.userDto);

	}

}
