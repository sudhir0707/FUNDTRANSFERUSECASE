package com.hcl.fundtransfer.dto;

import java.util.List;

import com.hcl.fundtransfer.entity.CustomerRegistration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter


public class ListOfAccDetailsDTO  {

	private List<CustomerRegistration> custAccNum, userName;
	
}
