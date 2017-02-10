package com.worldline.fpl.recruitment.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.worldline.fpl.recruitment.entity.Account;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.worldline.fpl.recruitment.dao.TransactionRepository;
import com.worldline.fpl.recruitment.entity.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

/**
 * Implementation of {@link TransactionRepository}
 * 
 * @author A525125
 *
 */
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Page<Transaction> getTransactionsByAccount(String accountId,
			Pageable p) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Transaction> cq = cb.createQuery(Transaction.class);
		Root<Transaction> rootEntry = cq.from(Transaction.class);
		cq.select(rootEntry);
		cq.where(cb.equal(rootEntry.join("account").get("id"), accountId));
		return new PageImpl<>(entityManager.createQuery(cq).getResultList());
	}

	@Override
	public Optional<Transaction> getTransaction(String transactionId) {
		return Optional.ofNullable(entityManager.find(Transaction.class, transactionId));
	}

	@Override
	public void removeTransaction(Transaction transaction) {
		entityManager.remove(transaction);
	}

	@Override
	public Transaction createTransaction(Transaction transaction) {
		transaction.setId(getNextId());
		entityManager.persist(transaction);
		return transaction;
	}

	@Override
	public void updateTransaction(Transaction updatedObject) {
		entityManager.merge(updatedObject);
	}

	private String getNextId() {
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		Root<Transaction> root = cq.from(Transaction.class);
		cq.select(qb.max(root.get("id")));
		Long newId = Long.parseLong(String.valueOf(entityManager.createQuery(cq).getSingleResult())) + 1;
		return newId.toString();
	}

}
