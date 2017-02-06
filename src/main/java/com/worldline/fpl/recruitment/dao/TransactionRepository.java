package com.worldline.fpl.recruitment.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.worldline.fpl.recruitment.entity.Transaction;

import java.util.Optional;

/**
 * Transaction repository
 * 
 * @author A525125
 *
 */
public interface TransactionRepository {

	/**
	 * Get transactions by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable information
	 * @return
	 */
	Page<Transaction> getTransactionsByAccount(String accountId, Pageable p);

	Optional<Transaction> getTransaction(String accountId, String transactionId);

	boolean removeTransaction(Transaction transaction);

	Transaction createTransaction(String accountId, Transaction transaction);

	void updateTransaction(String accountId, Transaction transaction);

}
