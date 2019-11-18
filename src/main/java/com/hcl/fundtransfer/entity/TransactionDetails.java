package com.hcl.fundtransfer.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class TransactionDetails {
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private long transactionId;
	private double fromAccNum;
	private double toAccNum;
	private double amount;
	private String transactionType;
	private LocalDateTime date;
	
}
