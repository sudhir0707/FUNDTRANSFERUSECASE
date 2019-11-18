package com.hcl.fundtransfer.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.hcl.fundtransfer.dto.CustomerRegistrationDTO;
import com.hcl.fundtransfer.dto.CustomerRegistrationResponseDTO;
import com.hcl.fundtransfer.dto.ListOfAccDetailsDTO;
import com.hcl.fundtransfer.dto.LoginDTO;
import com.hcl.fundtransfer.dto.TransactionDetailsRequestDTO;
import com.hcl.fundtransfer.dto.TransactionDetailsResponseDTO;
import com.hcl.fundtransfer.exception.ErrormessageException;

public interface CustomerRegistrationService {
	public CustomerRegistrationResponseDTO CustomerRegistration(CustomerRegistrationDTO customerRegistrationDTO) throws ErrormessageException;
	public CustomerRegistrationResponseDTO CustomerLogin(LoginDTO loginDTO) throws ErrormessageException;
    public TransactionDetailsResponseDTO TransactionDetails(TransactionDetailsRequestDTO transactionDetailsRequestDTO ) throws ErrormessageException;
    public List<CustomerRegistrationService> AccountDetails(@RequestBody ListOfAccDetailsDTO listOfAccDetailsDTO) throws ErrormessageException;
    
    //pubilc List<CustomerReg>
}
