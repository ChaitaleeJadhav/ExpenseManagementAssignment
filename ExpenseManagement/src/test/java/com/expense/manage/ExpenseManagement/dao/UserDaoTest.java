package com.expense.manage.ExpenseManagement.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.expense.manage.ExpenseManagement.dto.UserDto;
import com.expense.manage.ExpenseManagement.exceptions.ResourceNotFoundException;
import com.expense.manage.ExpenseManagement.model.UserCredentials;
import com.expense.manage.ExpenseManagement.service.CommonOper;

@RunWith(SpringRunner.class)
@Transactional
@SpringBootTest
@PropertySource("classpath:application-test.properties")
public class UserDaoTest {

	@Autowired
	public UserDao userDao;

	private UserCredentials user = CommonOper.getUser();

	// Test case to save code
	@Test
	public void testSaveuser() {

		try {
			UserDto userdto = CommonOper.modelMapper.map(userDao.saveUser(user), UserDto.class);
			assertThat(user).isEqualTo(userdto);
		}
		catch (ResourceNotFoundException re) {

			assertEquals("UserId id is already Exists,Want Unique user id", re.getMessage());
		}
		catch (Exception e) {
			// AssertionFailedException("");
		}

	}
}