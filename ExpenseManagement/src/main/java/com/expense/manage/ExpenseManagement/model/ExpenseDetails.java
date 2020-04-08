package com.expense.manage.ExpenseManagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "expensemanager")
public class ExpenseDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private String id;

	@ManyToOne
	@JoinColumn(name = "emailId")
	private UserCredentials userId;

	@Column(name = "title")
	private String Title;

	@Column(name = "category")
	private String expense_details;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "description")
	private String description;

	@Column(name = "contact")
	private Integer contact;

	@Column(name = "expense_date")
	private Date expenseDate;

}
