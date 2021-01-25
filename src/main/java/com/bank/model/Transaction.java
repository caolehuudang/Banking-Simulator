package com.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "TRANSACTION")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private int id;
	
	@Column(name = "OTP")
	private String otp;
	
	@Column(name = "MONEY")
	private String money;
	
	@Column(name = "IS_SUCCESS")
	private String isSuccess;
	
	@Column(name = "TIME")
	private String time;
	
	@ManyToOne
	@JoinColumn(name = "FK_ID_ACCOUNT")
	private Account account;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@JsonIgnore
	public Account getAccount() {
		return account;
	}

	@JsonProperty
	public void setAccount(Account account) {
		this.account = account;
	}

}
