package com.bank.service.impl;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bank.dao.AccountDao;
import com.bank.dao.TransactionDao;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.service.TransactionService;

@Service
public class TransactionServiceIpml implements TransactionService{	
	
	@Autowired
	TransactionDao transactionDao;
	
	@Autowired
	AccountDao accountDao;
	
	@Autowired
    private JavaMailSender javaMailSender;

	@Override
	public Transaction withDraw(Transaction transaction) {
		
		Long time = System.currentTimeMillis();
		transaction.setIsSuccess("N");
		transaction.setTime(time.toString());
		transaction.setOtp(getOTP());
		
		Account user = accountDao.findById(transaction.getAccount().getId()).get();
		
		if(null != user) {
			
			if(canBeWithdrawn(user.getMoney(), transaction.getMoney())) {
				sendEmail(user.getEmail(), transaction.getOtp(), user.getFullName() ,user.getBank().getName());
				return transactionDao.save(transaction);
			}
			
		}
		
		return null;
	}
	
	
	public String getOTP() {
		
		String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));
		
		return otp;
	}


	@Override
	public Transaction verifyOTP(int id, String otp) {
		
		Transaction trans = transactionDao.findByIdAndOtp(id, otp);
		
		if(null != trans) {
			
			if(validTime(trans.getTime())) {
				
				trans.setIsSuccess("Y");
				trans.setOtp(null);
				
				long moneyAfter = Long.valueOf(trans.getAccount().getMoney()) - Long.valueOf(trans.getMoney());
				
				trans.getAccount().setMoney(moneyAfter + "");
				
				return transactionDao.save(trans);
				
			}
			
		}
		
		return null;
	}
	
	void sendEmail(String email, String OTP, String fullName, String bankName) {
		
		StringBuilder subject = new StringBuilder();
		StringBuilder text = new StringBuilder();
		
		subject.append("<h3> [TB/Alert] Giao dịch được thực hiện với Thẻ TD ");
		subject.append(bankName);
		subject.append("</h3>");
		
		text.append("Dear ");
		text.append(fullName);
		text.append(", ");
		text.append("Đây là OTP của giao dịch \n");
		text.append("<b>");
		text.append(OTP);
		text.append("</b>");

        SimpleMailMessage msg = new SimpleMailMessage();
        
        msg.setTo(email);
        
        msg.setSubject(subject.toString());
        
        msg.setText(text.toString());
        
        javaMailSender.send(msg);

    }
	
	public Boolean validTime(String oldTime) {
		
		Long currentTime = System.currentTimeMillis();
		
		Long time = Long.valueOf(oldTime) + TimeUnit.MINUTES.toMillis(5);
		
		if(time < currentTime) {
			return false;
		}
		
		return true;
	}
	
	public Boolean canBeWithdrawn(String accountBalance, String moneyWithDraw) {
		
		Long balance = Long.valueOf(accountBalance);
		Long moneyDraw = Long.valueOf(moneyWithDraw);
		
		if(balance < moneyDraw) {
			return false;
		}
		
		return true;
	}

}
