package com.expense.manage.ExpenseManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expense.manage.ExpenseManagement.model.UserCredentials;

public interface RegisterRepository extends JpaRepository<UserCredentials, String> {

}
