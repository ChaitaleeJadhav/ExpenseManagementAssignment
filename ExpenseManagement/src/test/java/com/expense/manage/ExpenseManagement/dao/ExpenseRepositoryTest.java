package com.expense.manage.ExpenseManagement.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.expense.manage.ExpenseManagement.model.UserCredentials;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ExpenseRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ExpenseRepository expenserpo;

	@Test
	public void testSaveExpense() {
		ExpenseDetails expense = getExpense();
		ExpenseDetails savedInDb = entityManager.persist(expense);
		Optional<ExpenseDetails> optionalGetFromDb = expenserpo.findById(savedInDb.getId());
		ExpenseDetails getFromDb = null;
		if (optionalGetFromDb.isPresent()) {
			getFromDb = optionalGetFromDb.get();
		}
		assertThat(getFromDb).isEqualTo(savedInDb);
	}

	private ExpenseDetails getExpense() {
		ExpenseDetails expense = new ExpenseDetails();
		expense.setAmount(2234.67);
		expense.setCategory("phone");
		expense.setContact("87609835334");
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
