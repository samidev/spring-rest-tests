package com.worldline.fpl.recruitment.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Transaction;
import com.worldline.fpl.recruitment.exception.ServiceException;
import com.worldline.fpl.recruitment.json.ErrorCode;
import com.worldline.fpl.recruitment.json.TransactionResponse;

/**
 * Transaction service
 *
 * @author A525125
 */
@Service
public class TransactionService {

    private AccountService accountService;

    private TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(AccountService accountService,
                              TransactionRepository transactionRepository) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
    }

	/**
	 * Get transactions by account
	 * 
	 * @param accountId
	 *            the account id
	 * @param p
	 *            the pageable object
	 * @return
	 */
	public Page<TransactionResponse> getTransactionsByAccount(String accountId,
			Pageable p) {
		if (!accountService.isAccountExist(accountId)) {
			throw new ServiceException(ErrorCode.UNEXISTED_ACCOUNT,
					"Account doesn't exist");
		}
		return new PageImpl<TransactionResponse>(transactionRepository
				.getTransactionsByAccount(accountId, p).getContent().stream()
				.map(this::map).collect(Collectors.toList()));
	}

    public void removeTransaction(String accountId, String transactionId) {
        if (!accountService.isAccountExist(accountId)) {
            throw new ServiceException(ErrorCode.UNEXISTED_ACCOUNT,
                    "Account doesn't exist");
        }
        Transaction transaction = transactionRepository.getTransaction(accountId, transactionId).orElseThrow(
                () -> new ServiceException(ErrorCode.UNEXISTED_TRANSACTION,
                        "Transaction doesn't exist"));
        if (!transaction.getAccountId().equals(accountId)) {
            throw new ServiceException(ErrorCode.TRANSACTION_NOT_BELONG_ACCOUNT,
                    "Transaction doesn't belong to the account");
        }
        transactionRepository.removeTransaction(transaction);
    }

    public Transaction createTransaction(String accountId, Transaction transaction) {
        if (!accountService.isAccountExist(accountId)) {
            throw new ServiceException(ErrorCode.UNEXISTED_ACCOUNT,
                    "Account doesn't exist");
        }
        if (transaction == null || transaction.getBalance() == null
                || transaction.getNumber() == null) {
            throw new ServiceException(ErrorCode.INVALID_TRANSACTION,
                    "Transaction isn't valid");
        }
        return transactionRepository.createTransaction(accountId, transaction);
    }

    public void updateTransaction(String accountId, String transactionId, Transaction newTransaction) {
        if (!accountService.isAccountExist(accountId)) {
            throw new ServiceException(ErrorCode.UNEXISTED_ACCOUNT,
                    "Account doesn't exist");
        }
        if (newTransaction == null || newTransaction.getBalance() == null
                || newTransaction.getNumber() == null) {
            throw new ServiceException(ErrorCode.INVALID_TRANSACTION,
                    "Transaction isn't valid");
        }
        Transaction transaction = transactionRepository.getTransaction(accountId, transactionId).orElseThrow(
                () -> new ServiceException(ErrorCode.UNEXISTED_TRANSACTION,
                        "Transaction doesn't exist"));
        if (!transaction.getAccountId().equals(accountId)) {
            throw new ServiceException(ErrorCode.TRANSACTION_NOT_BELONG_ACCOUNT,
                    "Transaction doesn't belong to the account");
        }
        transactionRepository.updateTransaction(transactionId, newTransaction);
    }

    /**
     * Map {@link Transaction} to {@link TransactionResponse}
     *
     * @param transaction
     * @return
     */
    private TransactionResponse map(Transaction transaction) {
        TransactionResponse result = new TransactionResponse();
        result.setBalance(transaction.getBalance());
        result.setId(transaction.getId());
        result.setNumber(transaction.getNumber());
        return result;
    }

}
