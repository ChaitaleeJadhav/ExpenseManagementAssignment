package com.expense.manage.ExpenseManagement.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.expense.manage.ExpenseManagement.exceptions.ResourceNotFoundException;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.expense.manage.ExpenseManagement.service.CommonOper;

@RunWith(SpringRunner.class)
@DataJpaTest
@PropertySource("classpath:application-test.properties")
public class ExpenseRepositoryTest {

	@Autowired
	public TestEntityManager entityManager;

	@Autowired
	public RegisterRepository regrpo;

	@Autowired
	public ExpenseRepository expenserpo;

	private ExpenseDetails expense = CommonOper.getExpense();

	@Test
	public void testSaveExpense() {

		try {
			ExpenseDetails savedInDb = entityManager.persist(expense);
			Optional<ExpenseDetails> optionalGetFromDb = expenserpo.findById(savedInDb.getId());

			ExpenseDetails getFromDb = null;
			if (optionalGetFromDb.isPresent()) {
				getFromDb = optionalGetFromDb.get();
				assertThat(getFromDb).isEqualTo(savedInDb);

			}
			else {
				throw new ResourceNotFoundException(
						"User account is not found,Please first create user account to add expenses");
			}

		}
		catch (Exception e) {

		}
	}

}
