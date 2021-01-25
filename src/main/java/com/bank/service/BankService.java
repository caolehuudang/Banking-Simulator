package com.bank.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.bank.model.Bank;

public interface BankService {

	Bank addNewCategory(Bank category) throws DataAccessException;;
	
	List<Bank> getAllBanks();
	
}
