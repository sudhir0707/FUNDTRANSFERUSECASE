package com.hcl.fundtransfer.service;

import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hcl.fundtransfer.dto.CustomerRegistrationDTO;
import com.hcl.fundtransfer.dto.CustomerRegistrationResponseDTO;
import com.hcl.fundtransfer.dto.ListOfAccDetailsDTO;
import com.hcl.fundtransfer.dto.LoginDTO;
import com.hcl.fundtransfer.dto.TransactionDetailsRequestDTO;
import com.hcl.fundtransfer.dto.TransactionDetailsResponseDTO;
import com.hcl.fundtransfer.entity.CustomerRegistration;
import com.hcl.fundtransfer.entity.TransactionDetails;
import com.hcl.fundtransfer.exception.ErrormessageException;
import com.hcl.fundtransfer.repository.CustomerRegistrationRepository;
import com.hcl.fundtransfer.repository.TransactionDetailsRepository;
import com.hcl.fundtransfer.utility.ApplicationUtility;
import com.hcl.fundtransfer.utility.FeildValidation;

@Service
public class CustomerRegistrationServiceImpl implements CustomerRegistrationService {
	
	@Autowired 
	CustomerRegistrationRepository customerRegistrationRepository;
	@Autowired
	TransactionDetailsRepository transactionDetailsRepository;
	
	
	@Override
	public CustomerRegistrationResponseDTO CustomerRegistration(CustomerRegistrationDTO customerRegistrationDTO)
			throws ErrormessageException {
		//--------------------Customer Registration-----------
		
		Optional<List<CustomerRegistration>> userDetailsemail= customerRegistrationRepository.findByEmail(customerRegistrationDTO.getEmail());
		//Optional<List<CustomerRegistration>> userAcc= customerRegistrationRepository.findByCustAccNum(customerRegistrationDTO.getCustAccNum());
		if(userDetailsemail.isPresent())
			throw new ErrormessageException(ApplicationUtility.EMAIL_STATUS);
		if(!FeildValidation.isValid(customerRegistrationDTO.getEmail()))
			throw new ErrormessageException(ApplicationUtility.INVALID_EMAIL_STATUS);
	
			Optional<List<CustomerRegistration>> userDetailsMobile= customerRegistrationRepository.findByMobile(customerRegistrationDTO.getMobile());
			
				if(userDetailsMobile.isPresent())
			throw new ErrormessageException(ApplicationUtility.EXITED_MOBILE_NUMBER_STATUS);
				if(!FeildValidation.mobileValid(""+customerRegistrationDTO.getMobile()))
					throw new ErrormessageException(ApplicationUtility.INVALID_MOBILE_NUMBER_STATUS);
	
				double custAccNum=FeildValidation.getRandomIntegerBetweenRange(10001, 999999);
				
		 CustomerRegistration customerRegistration = new CustomerRegistration();
	 customerRegistration.setCustAccNum(custAccNum);
		BeanUtils.copyProperties(customerRegistrationDTO, customerRegistration);
		customerRegistrationRepository.save(customerRegistration);
		CustomerRegistrationResponseDTO responseDTO=new CustomerRegistrationResponseDTO();
			responseDTO.setMessage(ApplicationUtility.CUSTOMER_REGISTRTION_STATUS);
	     	responseDTO.setStatuscode(HttpStatus.CREATED.value());
			
		return  responseDTO;
}


	@Override
	public CustomerRegistrationResponseDTO CustomerLogin(LoginDTO loginDTO) throws ErrormessageException {
		
		//--------------User Login --------------
		
		Optional<List<CustomerRegistration>> loginDetails=customerRegistrationRepository.findByMobileAndPassword(loginDTO.getMobile(), loginDTO.getPassword());
		if(!loginDetails.isPresent())
			throw new ErrormessageException(ApplicationUtility.INVALID_LOGIN_STATUS);
		
		CustomerRegistrationResponseDTO loginresponseDTO= new CustomerRegistrationResponseDTO();
		loginresponseDTO.setCustId(loginDetails.get().get(0).getCustId());
		loginresponseDTO.setMessage(ApplicationUtility.LOGIN_STATUS);
     	loginresponseDTO.setStatuscode(HttpStatus.CREATED.value());
		return loginresponseDTO;
	}


	@Override
	public TransactionDetailsResponseDTO TransactionDetails(TransactionDetailsRequestDTO transactionDetailsRequestDTO)
			throws ErrormessageException {
		
		// Transaction Process
		Optional<List<CustomerRegistration>> userDetailsFromAccNum=customerRegistrationRepository.findByCustAccNum(transactionDetailsRequestDTO.getFromAccNum());
		if(!userDetailsFromAccNum.isPresent()|| userDetailsFromAccNum.get().isEmpty())
		throw new ErrormessageException(ApplicationUtility.NOACCOUNT_NUMBER_VALIDATION_STATUS);
		Optional<List<CustomerRegistration>> userDetailstoAccNum=customerRegistrationRepository.findByCustAccNum(transactionDetailsRequestDTO.getToAccNum());
		if(!userDetailstoAccNum.isPresent()|| userDetailsFromAccNum.get().isEmpty())
		throw new ErrormessageException(ApplicationUtility.NOACCOUNT_NUMBER_VALIDATION_STATUS);
		if (userDetailsFromAccNum.get().get(0).getBalance() < transactionDetailsRequestDTO.getAmount())
			throw new ErrormessageException(ApplicationUtility.BALANCE_STATUS);
		if(userDetailsFromAccNum.get().get(0).getBalance()< 500)
			throw new ErrormessageException(ApplicationUtility.BALANCE_STATUS);
		if(userDetailstoAccNum.get().get(0).getBalance()< 500)
			throw new ErrormessageException(ApplicationUtility.BALANCE_STATUS);
		
		
		
		userDetailsFromAccNum.get().get(0)
		.setBalance(userDetailsFromAccNum.get().get(0).getBalance()-transactionDetailsRequestDTO.getAmount());
		userDetailstoAccNum.get().get(0)
		.setBalance(userDetailstoAccNum.get().get(0).getBalance()+transactionDetailsRequestDTO.getAmount());
		
		customerRegistrationRepository.save(userDetailsFromAccNum.get().get(0));
		customerRegistrationRepository.save(userDetailstoAccNum.get().get(0));
		
		// enter TransactionHistory table

		TransactionDetails transactionDetails= new TransactionDetails();
	
		BeanUtils.copyProperties(transactionDetailsRequestDTO, transactionDetails);
		transactionDetails.setTransactionType("Credit");
		transactionDetails.setDate(LocalDateTime.now());
	    transactionDetailsRepository.save(transactionDetails);
		TransactionDetailsResponseDTO transactionDetailsResponseDTO  = new TransactionDetailsResponseDTO ();
		transactionDetailsResponseDTO.setMessage(ApplicationUtility.TRANSACTION_STATUS);
		transactionDetailsResponseDTO.setStatuscode(HttpStatus.CREATED.value());
		return transactionDetailsResponseDTO;	
		
	}

// list of Account Details


@Override
public List<CustomerRegistrationService> AccountDetails(ListOfAccDetailsDTO listOfAccDetailsDTO)
		throws ErrormessageException {
	//List<CustomerRegistration> userAccNumDetail=null;
	 List<CustomerRegistration> userAccNumDetails=customerRegistrationRepository.findAll();
	
	for(CustomerRegistration s : userAccNumDetails )
	{
		userAccNumDetails.add(s);
	
	System.out.println(((List<CustomerRegistration>) s).get(0).getCustAccNum());
	System.out.println(((List<CustomerRegistration>) s).get(0).getUserName());
	}
//	ListOfAccDetailsDTO listOfAccDetailsDTO= new ListOfAccDetailsDTO();
////	CustomerRegistrationResponseDTO loginresponseDTO= new CustomerRegistrationResponseDTO();
//	listOfAccDetailsDTO.setCustAccNum(userAccNumDetails.get(0).getCustAccNum());
//	listOfAccDetailsDTO.setUserName(userAccNumDetails.get(0).getUserName());
	return userAccNumDetails;
}

//return userAccNumDetail;
	
	}
	

	
	


