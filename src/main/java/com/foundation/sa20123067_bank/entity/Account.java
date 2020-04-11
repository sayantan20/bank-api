package com.foundation.sa20123067_bank.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Account implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACCT_ID")
	private UUID id;
	
	private Long accountNumber;
	
	@OneToOne(cascade=CascadeType.ALL)
	private BankInfo bankInformation;
	
	private String accountStatus;
	
	private String accountType;
	
	private Double accountBalance;
    
    @Temporal(TemporalType.TIME)
	private Date createDateTime;
	
    @Temporal(TemporalType.TIME)
	private Date updateDateTime;
	
}
