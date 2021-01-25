package com.bank.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.dao.AccountDao;
import com.bank.model.Account;
import com.bank.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	AccountDao accountDao;

	@Override
	public Account addNewAccount(Account account) {
		try {
			Long count = accountDao.countAccount(account.getBank().getId());
			String serialNo = account.getBank().getCode() + String.format("%05d", count + 1);
			account.setSerialNo(serialNo);
			if (account.getMoney() == null) {
				account.setMoney("0");
			}
			
			return accountDao.save(account);
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public Account findAccountBySerialNo(String serialNo) {
		return accountDao.findAccountBySerialNo(serialNo);
	}

	@Override
	public List<Account> getAllAccounts() {
		return accountDao.findAll();
	}

	@Override
	public Account depositMoney(String serialNo, String money) {
		
		try {
			Account account = accountDao.findAccountBySerialNo(serialNo);
			if(null != account) {
				Long deposits = Long.valueOf(money);
				
				account.setMoney(account.getMoney() == null ? deposits + "" :Long.valueOf(account.getMoney()) + deposits + "");
				
				return accountDao.save(account);
			}
			return null;
		} catch (Exception e) {
			return null;
		}
		
	}

}
