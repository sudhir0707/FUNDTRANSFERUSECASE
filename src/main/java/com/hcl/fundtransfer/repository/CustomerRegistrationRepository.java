package com.hcl.fundtransfer.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.fundtransfer.entity.CustomerRegistration;
@Repository
public interface CustomerRegistrationRepository extends JpaRepository<CustomerRegistration, Long> {

	Optional<List<CustomerRegistration>>findByMobile(long mobile);
	Optional<List<CustomerRegistration>>findByEmail(String email);
	Optional<List<CustomerRegistration>>findByCustAccNum(double custAccNum);
	Optional<List<CustomerRegistration>>findByMobileAndPassword(long mobile, String password);
}
