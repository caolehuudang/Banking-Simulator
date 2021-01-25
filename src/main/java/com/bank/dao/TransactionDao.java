package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Transaction;

@Repository
public interface TransactionDao extends JpaRepository<Transaction, Integer>{

	Transaction findByIdAndOtp(int id, String Otp);
}
