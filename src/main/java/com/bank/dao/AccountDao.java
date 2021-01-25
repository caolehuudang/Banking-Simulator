package com.bank.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bank.model.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {

	Account findAccountBySerialNo(String serialNo);
	
	@Query("SELECT count(*) FROM Account WHERE bank.id = :id")
	Long countAccount(@Param("id") int id);
}
