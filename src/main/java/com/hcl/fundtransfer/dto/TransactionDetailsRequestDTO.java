package com.hcl.fundtransfer.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionDetailsRequestDTO {

	private double fromAccNum;
	private double toAccNum;
	private double amount;
	
}
