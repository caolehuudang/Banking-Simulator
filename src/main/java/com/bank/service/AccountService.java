package com.bank.service;

import java.util.List;

import com.bank.model.Account;

public interface AccountService {

	Account addNewAccount(Account account);
	
	Account findAccountBySerialNo(String serialNo);
	
	List<Account> getAllAccounts();
	
	Account depositMoney(String serialNo, String money);
}
