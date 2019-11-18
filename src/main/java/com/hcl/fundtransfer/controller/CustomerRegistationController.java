package com.hcl.fundtransfer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.fundtransfer.dto.CustomerRegistrationDTO;
import com.hcl.fundtransfer.dto.CustomerRegistrationResponseDTO;
import com.hcl.fundtransfer.dto.ListOfAccDetailsDTO;
import com.hcl.fundtransfer.dto.LoginDTO;
import com.hcl.fundtransfer.dto.TransactionDetailsRequestDTO;
import com.hcl.fundtransfer.dto.TransactionDetailsResponseDTO;
import com.hcl.fundtransfer.exception.ErrormessageException;
import com.hcl.fundtransfer.service.CustomerRegistrationService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@RestController
@RequestMapping("/FundTransfer")
public class CustomerRegistationController {

	@Autowired 
	CustomerRegistrationService customerRegistrationService;

	@PostMapping("/Users")

	public ResponseEntity<CustomerRegistrationResponseDTO> CustomerRegistration(@RequestBody CustomerRegistrationDTO customerRegistrationDTO )throws ErrormessageException
	{
	CustomerRegistrationResponseDTO response=customerRegistrationService.CustomerRegistration(customerRegistrationDTO);
		log.info("BankController:: Customer Registration.....");;
			//log.info("CustomerRegistrationController::Customer Registration..");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	
	}
	
	@PostMapping("/Users/Login")

	public ResponseEntity<CustomerRegistrationResponseDTO> CustomerLogin(@RequestBody LoginDTO loginDTO )throws ErrormessageException
	{
		CustomerRegistrationResponseDTO response=customerRegistrationService.CustomerLogin(loginDTO);
		log.info("BankController:: Login Successfully.....");;
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	
	}
	
	@PostMapping("/Users/Login/AmountTransfer")
	
	public ResponseEntity<TransactionDetailsResponseDTO> TransactionDetails(@RequestBody TransactionDetailsRequestDTO transactionDetailsRequestDTO) throws ErrormessageException
	{
		TransactionDetailsResponseDTO response=customerRegistrationService.TransactionDetails(transactionDetailsRequestDTO);
		log.info("BankController:: Transaction Successfully.....");;
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	@GetMapping("Users/Login/UserAccountDetails")
	public ResponseEntity<List<CustomerRegistrationService>>AccountDetails(@RequestBody ListOfAccDetailsDTO listOfAccDetailsDTO)throws ErrormessageException
	{

		List<CustomerRegistrationService> response=customerRegistrationService.AccountDetails(listOfAccDetailsDTO);
		
return new ResponseEntity<>(response, HttpStatus.OK);
	}
}