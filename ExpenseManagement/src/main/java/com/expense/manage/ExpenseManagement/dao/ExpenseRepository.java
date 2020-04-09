package com.expense.manage.ExpenseManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.manage.ExpenseManagement.model.ExpenseDetails;

public interface ExpenseRepository extends JpaRepository<ExpenseDetails, Integer> {

}
