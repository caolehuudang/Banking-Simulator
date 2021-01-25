package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.common.Status;
import com.bank.model.Account;
import com.bank.service.AccountService;

@RestController
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@PostMapping(value = "/addNewAccount", produces = "application/json; charset=UTF-8")
	public Status<Account> addNewAccount(@RequestBody Account account) {
		return new Status<Account>(accountService.addNewAccount(account));
	}
	
	@GetMapping(value = "/getAllAccounts")
	public List<Account> getAllAccounts() {
		return accountService.getAllAccounts();
	}
	
	@PostMapping(value = "/getAccountBySerial", produces = "application/json; charset=UTF-8")
	public Account getAccountBySerial(@RequestBody Account account){
		return accountService.findAccountBySerialNo(account.getSerialNo());
	}
	
	@PostMapping(value = "/depositMoney", produces = "application/json; charset=UTF-8")
	public  Status<Account> depositMoney(@RequestBody Account account){
		return new Status<Account>(accountService.depositMoney(account.getSerialNo(), account.getMoney()));
	}
	
}
