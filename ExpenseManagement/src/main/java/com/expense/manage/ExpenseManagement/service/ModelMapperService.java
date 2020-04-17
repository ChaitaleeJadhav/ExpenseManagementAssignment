package com.expense.manage.ExpenseManagement.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expense.manage.ExpenseManagement.dto.ExpenseDto;
import com.expense.manage.ExpenseManagement.dto.UserDto;
import com.expense.manage.ExpenseManagement.model.ExpenseDetails;
import com.expense.manage.ExpenseManagement.model.UserCredentials;

@Service("modelMapperService")
public class ModelMapperService {

	@Autowired
	private ModelMapper modelMapper;

	public UserDto mappedToUsetDto(UserCredentials user) {
		return modelMapper.map(user, UserDto.class);
	}

	public ExpenseDto mappedToExpenseDto(ExpenseDetails expense) {
		return modelMapper.map(expense, ExpenseDto.class);

	}
}
