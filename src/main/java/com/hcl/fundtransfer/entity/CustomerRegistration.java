package com.hcl.fundtransfer.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistration {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long custId;
	private String userName;
	private double custAccNum;
	private String password;
	private long mobile;
	private double balance;
	private String email;


}
