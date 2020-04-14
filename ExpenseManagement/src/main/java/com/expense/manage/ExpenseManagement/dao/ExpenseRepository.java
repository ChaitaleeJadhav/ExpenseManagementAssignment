package com.expense.manage.ExpenseManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.expense.manage.ExpenseManagement.model.ExpenseDetails;

public interface ExpenseRepository extends JpaRepository<ExpenseDetails, Integer> {

	@Query(value = "select expd from ExpenseDetails expd where expd.user_id.id=?1")
	List<ExpenseDetails> getExpenseDetailsById(String userId);

	@Query(value = "select CONCAT(MONTHNAME(expenseDate),' ',YEAR(expd.expenseDate))as date,SUM(expd.amount) as amount from ExpenseDetails expd where expd.user_id.id=?1 GROUP BY Month(expenseDate),YEAR(expd.expenseDate)")
	List<Object[]> getSummaryExpenseById(String userId);

}
