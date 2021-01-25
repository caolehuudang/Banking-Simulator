package com.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.bank.common.Contants;
import com.bank.dao.BankDao;
import com.bank.model.Bank;
import com.bank.service.BankService;

@Service
public class BankServiceImpl implements BankService{

	@Autowired
	BankDao bankDao;

	@Override
	public Bank addNewCategory(Bank bank) throws DataAccessException{
		
		try {
			bank.setStatus(Contants.ACTIVE);
			return bankDao.save(bank);
		} catch (Exception e) {
			return null;
		}
		
	} 

	@Override
	public List<Bank> getAllBanks() {
		return bankDao.findAll();
	}
	
	
}
