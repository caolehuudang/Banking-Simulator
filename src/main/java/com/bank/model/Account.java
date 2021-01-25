package com.bank.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "ACCOUNT")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private int id;
	
	@Column(name = "SERIAL_NO", unique = true)
	private String serialNo;
	
	@Column(name = "FULL_NAME")
	private String fullName;
	
	@Column(name = "MONEY")
	private String money;
	
	@Column(name = "EMAIL")
	private String email;
	
	@ManyToOne
	@JoinColumn(name = "FK_ID_BANK")
	private Bank bank;
	
	@OneToMany(mappedBy = "account",fetch = FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	private List<Transaction> transactions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getSerialNo() {
		return serialNo;
	}



	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}



	public String getFullName() {
		return fullName;
	}



	public void setFullName(String fullName) {
		this.fullName = fullName;
	}



	public String getMoney() {
		return money;
	}



	public void setMoney(String money) {
		this.money = money;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Bank getBank() {
		return bank;
	}



	public void setBank(Bank bank) {
		this.bank = bank;
	}

	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Account() {
		super();
	}
	
	
}
