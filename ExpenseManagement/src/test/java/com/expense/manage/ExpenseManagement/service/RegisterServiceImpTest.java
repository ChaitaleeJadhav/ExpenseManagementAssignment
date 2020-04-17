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
import com.expense.manage.ExpenseManagement.model.UserCredentials;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { RegisterServiceImp.class })
public class RegisterServiceImpTest {

	@Autowired
	private RegisterService regService;

	@MockBean
	public UserDao userdao;

	@Test
	public void testAddUser() {

		UserCredentials user = new UserCredentials();
		user.setId("subi19990j@gmail.com");
		user.setPassword("password");
		Mockito.when(userdao.saveUser(user)).thenReturn(user);

		assertThat(regService.registerUser(user)).isEqualTo(user);

	}

	@Test
	public void testUpdatePasswod() {

		UserCredentials user = new UserCredentials();
		user.setId("subi19990j@gmail.com");
		user.setPassword("Nanciy");
		Mockito.when(userdao.UpdateUserPassword(user)).thenReturn(user);

		assertThat(regService.UpdateUser(user)).isEqualTo(user);

	}

}
