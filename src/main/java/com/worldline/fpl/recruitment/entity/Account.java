package com.worldline.fpl.recruitment.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Account entity
 * 
 * @author A525125
 *
 */
@Data
@Entity(name = "accounts")
public class Account implements Serializable {

	private static final long serialVersionUID = -3548441891975414771L;

	@Id
	private String id;

	private String number;

	private String type;

	private BigDecimal balance;

	@Temporal(TemporalType.DATE)
	private Date creationDate;

	private boolean isActive;
}
