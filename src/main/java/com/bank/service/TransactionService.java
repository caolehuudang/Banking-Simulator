package com.bank.service;

import com.bank.model.Transaction;

public interface TransactionService {
	
	Transaction withDraw(Transaction transaction);
	
	Transaction verifyOTP(int id, String otp);

}
