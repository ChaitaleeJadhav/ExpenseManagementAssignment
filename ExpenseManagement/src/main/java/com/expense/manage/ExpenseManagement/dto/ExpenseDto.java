package com.expense.manage.ExpenseManagement.dto;

import java.io.Serializable;
import java.util.Date;

import com.expense.manage.ExpenseManagement.model.UserCredentials;

public class ExpenseDto implements Serializable {

	private Integer id;
	private UserCredentials user_id;
	private String title;
	private String category;
	private Double amount;
	private String description;
	private String contact;
	private Date expenseDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserCredentials getUser_id() {
		return user_id;
	}

	public void setUser_id(UserCredentials user_id) {
		this.user_id = user_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

}
