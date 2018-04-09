package com.kokonatsuDream.userfront.service;

import java.util.List;

import com.kokonatsuDream.userfront.domain.PrimaryTransaction;
import com.kokonatsuDream.userfront.domain.SavingsTransaction;

public interface TransactionService {
	List<PrimaryTransaction> findPrimaryTransactionList(String username);
	
	List<SavingsTransaction> findSavingsTransactionList(String username);
	
	void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction);
	
	void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);
	
	void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction);
	
	void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction);
}
