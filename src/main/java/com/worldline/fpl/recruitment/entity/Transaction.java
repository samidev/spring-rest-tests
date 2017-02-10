package com.worldline.fpl.recruitment.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Transaction entity
 * 
 * @author A525125
 *
 */
@Data
@Entity(name = "transactions")
public class Transaction implements Serializable {

	private static final long serialVersionUID = 706690724306325415L;

	@Id
	private String id;

	@ManyToOne
	@JoinColumn(name = "accountID")
	private Account account;

	private String number;

	private BigDecimal balance;
}
