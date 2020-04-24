package com.expense.manage.ExpenseManagement.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.expense.manage.ExpenseManagement.exceptions.ResourceNotFoundException;
import com.expense.manage.ExpenseManagement.model.UserCredentials;
import com.expense.manage.ExpenseManagement.service.CommonOper;
import com.expense.manage.ExpenseManagement.service.ModelMapperService;

@RunWith(SpringRunner.class)
@DataJpaTest
@PropertySource("classpath:application-test.properties")
public class RegisterRepositoryTest {

	@Autowired
	public TestEntityManager entityManager;

	@MockBean
	public ModelMapperService modelMapperService;

	@Autowired
	public RegisterRepository regrpo;

	private UserCredentials user = CommonOper.getUser();

	// Test case to save code
	@Test
	public void testSaveuser() {

		Boolean existUser = regrpo.existsById(user.getId());
		try {
			if (existUser) {
				throw new ResourceNotFoundException("UserId id is already Exists,Want Unique user id");

			}
			else {

				UserCredentials savedInDb = entityManager.persist(user);
				Optional<UserCredentials> optionalGetFromDb = regrpo.findById(savedInDb.getId());
				UserCredentials getFromDb = null;
				if (optionalGetFromDb.isPresent()) {
					getFromDb = optionalGetFromDb.get();
				}
				assertThat(getFromDb).isEqualTo(savedInDb);
			}
		}
		catch (Exception e) {
			assertEquals("UserId id is already Exists,Want Unique user id", e.getMessage());
		}

	}

	// Test case to update code
	@Test
	public void testUpdateUserPasswords() {
		user.setPassword("updatedPaaword");
		Boolean existUser = regrpo.existsById(user.getId());
		try {
			if (existUser) {

				UserCredentials savedInDb = entityManager.persist(user);
				Optional<UserCredentials> optionalGetFromDb = regrpo.findById(savedInDb.getId());
				UserCredentials getFromDb = null;
				if (optionalGetFromDb.isPresent()) {
					getFromDb = optionalGetFromDb.get();
				}
				assertThat(getFromDb).isEqualTo(savedInDb);

			}
			else {
				throw new ResourceNotFoundException("UserId id is already Exists,Want Unique user id");

			}
		}
		catch (Exception e) {
			assertEquals("UserId id is already Exists,Want Unique user id", e.getMessage());
		}

	}

}
