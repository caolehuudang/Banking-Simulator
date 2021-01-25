package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bank.common.Status;
import com.bank.model.Transaction;
import com.bank.service.TransactionService;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;

	@PostMapping(value = "/withDrawMoney", produces = "application/json; charset=UTF-8")
	public Status<Transaction> getAccountBySerial(@RequestBody Transaction transaction){
		return new Status<Transaction>(transactionService.withDraw(transaction));
	}
	
	@GetMapping(value = "/verifyOTP/{id}/{otp}", produces = "application/json; charset=UTF-8")
	public Status<Transaction> verifyOTP(@PathVariable int id, @PathVariable String otp){
		return new Status<Transaction>(transactionService.verifyOTP(id, otp));
	}
}
