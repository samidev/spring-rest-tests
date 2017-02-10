package com.worldline.fpl.recruitment.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.worldline.fpl.recruitment.dao.AccountRepository;
import com.worldline.fpl.recruitment.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Implementation of {@link AccountRepository}
 * 
 * @author A525125
 *
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<Account> findAll(Pageable p) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Account> cq = cb.createQuery(Account.class);
		Root<Account> rootEntry = cq.from(Account.class);
		CriteriaQuery<Account> all = cq.select(rootEntry);
		TypedQuery<Account> allQuery = em.createQuery(all);
		return new PageImpl<>(allQuery.getResultList());
	}

	@Override
	public Optional<Account> findById(String accountId) {
		return Optional.ofNullable(em.find(Account.class, accountId));
	}

	@Override
	public boolean exists(String accountId) {
		return findById(accountId).isPresent();
	}
}
