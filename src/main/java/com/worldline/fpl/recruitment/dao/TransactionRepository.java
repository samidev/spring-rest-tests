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

	/**
	 *
	 * @param transactionId
	 *            the transaction id
	 * @return
	 */
	Optional<Transaction> getTransaction(String transactionId);

	/**
	 *
	 * @param transaction
	 *            the transaction
	 */
	void removeTransaction(Transaction transaction);

	/**
	 *
	 * @param transaction
	 *             the transaction id
	 * @return
	 */
	Transaction createTransaction(Transaction transaction);

	/**
	 *
	 * @param updatedTransaction
	 *             the updated transaction
	 */
	void updateTransaction(Transaction updatedTransaction);

}
