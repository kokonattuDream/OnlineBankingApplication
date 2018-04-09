package com.kokonatsuDream.userfront.service.UserServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kokonatsuDream.userfront.Dao.PrimaryAccountDao;
import com.kokonatsuDream.userfront.Dao.SavingsAccountDao;
import com.kokonatsuDream.userfront.domain.PrimaryAccount;
import com.kokonatsuDream.userfront.domain.PrimaryTransaction;
import com.kokonatsuDream.userfront.domain.SavingsAccount;
import com.kokonatsuDream.userfront.domain.SavingsTransaction;
import com.kokonatsuDream.userfront.domain.User;
import com.kokonatsuDream.userfront.service.AccountService;
import com.kokonatsuDream.userfront.service.UserService;

@Service
public class AccountServiceImpl implements AccountService {

	private static int nextAccountNumber = 1314142;
	@Autowired
	private PrimaryAccountDao primaryAccountDao;
	@Autowired
	private SavingsAccountDao savingsAccountDao;
	@Autowired
	private UserService userService;
	
	@Override
	public PrimaryAccount createPrimaryAccount() {
		// TODO Auto-generated method stub
		PrimaryAccount primaryAccount = new PrimaryAccount();
		
		primaryAccount.setAccountBalance(new BigDecimal(0.0));
		primaryAccount.setAccountNumber(accountGen());
		
		primaryAccountDao.save(primaryAccount);
		
		return primaryAccountDao.findByAccountNumber(primaryAccount.getAccountNumber());
	}

	@Override
	public SavingsAccount createSavingsAccount() {
		// TODO Auto-generated method stub
		SavingsAccount savingAccount = new SavingsAccount();
		
		savingAccount.setAccountBalance(new BigDecimal(0.0));
		savingAccount.setAccountNumber(accountGen());
		
		savingsAccountDao.save(savingAccount);
		
		return savingsAccountDao.findByAccountNumber(savingAccount.getAccountNumber());
	}
	
	public void deposit(String accountType, double amount, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		if(accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().add(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
			Date date = new Date();
			
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposit to Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
		
		} else if(accountType.equalsIgnoreCase("Savings")) {
			SavingsAccount savingsAccount = new SavingsAccount();
			
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
			savingsAccountDao.save(savingsAccount);
			
			Date date = new Date();
			
			SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposit to savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
		}
	}
	
	public void withdraw(String accountType, double amount, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		
		if(accountType.equalsIgnoreCase("Primary")) {
			PrimaryAccount primaryAccount = user.getPrimaryAccount();
			
			primaryAccount.setAccountBalance(primaryAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			primaryAccountDao.save(primaryAccount);
			
			Date date = new Date();
			
			PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Withdraw from Primary Account", "Account", "Finished", amount, primaryAccount.getAccountBalance(), primaryAccount);
		} else if(accountType.equalsIgnoreCase("Savings")) {
			SavingsAccount savingsAccount = new SavingsAccount();
			
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingsAccountDao.save(savingsAccount);
			
			Date date = new Date();
			
			SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Withdraw from savings Account", "Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount);
		}
	}
	
	private int accountGen() {
		return nextAccountNumber++;
	}
	
	

}
