package com.hcl.fundtransfer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CustomerRegistrationDTO {
	private String userName;
	//private double custAccNum;
	private String password;
	private long mobile;
	private double balance;
	private String email;
}
