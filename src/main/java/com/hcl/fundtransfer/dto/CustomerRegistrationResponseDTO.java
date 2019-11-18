package com.hcl.fundtransfer.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerRegistrationResponseDTO {

	private Long custId;
	private String message;
	private Integer Statuscode;
}
