package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.common.Status;
import com.bank.model.Bank;
import com.bank.service.BankService;

@RestController
public class BankController {

	@Autowired
	BankService bankService;
	
	@PostMapping(value = "/addNewBank", produces = "application/json; charset=UTF-8")
	public Status<Bank> addNewBank(@RequestBody Bank bank) {
		return new Status<Bank>(bankService.addNewCategory(bank));
		
	}
	
	@GetMapping(value = "/getAllBanks")
	public List<Bank> getAllBanks() {
		return bankService.getAllBanks();
		
	}
	
}
