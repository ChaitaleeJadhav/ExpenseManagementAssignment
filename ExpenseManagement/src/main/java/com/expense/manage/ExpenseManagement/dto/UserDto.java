package com.expense.manage.ExpenseManagement.dto;

import java.io.Serializable;
import java.util.List;

import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto implements Serializable {

	private String id;

	@JsonIgnore
	private String password;

	private List<ExpenseDetails> expList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ExpenseDetails> getExpList() {
		return expList;
	}

	public void setExpList(List<ExpenseDetails> expList) {
		this.expList = expList;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
