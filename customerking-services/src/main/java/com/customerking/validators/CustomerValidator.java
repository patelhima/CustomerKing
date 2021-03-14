package com.customerking.validators;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.customerking.CustomerDTO;
import com.customerking.commons.CustomerResponseDTO;

@Component
public class CustomerValidator {

	String EMAIL_VALIDATION_REGEX="^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	
	public void addCustomerValidator(CustomerResponseDTO responseDTO, CustomerDTO customerDTO) {
		List<String> errorMessages = new ArrayList<>();
		if (StringUtils.isBlank(customerDTO.getName())) {
			errorMessages.add("Please enter customer name");
		}
		if (StringUtils.isBlank(customerDTO.getEmail())) {
			errorMessages.add("Please enter customer email");
		}else if (!validateEmailID(customerDTO.getEmail())) {
			errorMessages.add("Please enter valid email");
		}
		if (StringUtils.isBlank(customerDTO.getAddress())) {
			errorMessages.add("Please enter customer address");
		}
		if (StringUtils.isBlank(customerDTO.getGender())) {
			errorMessages.add("Please select gender");
		}
		if (StringUtils.isBlank(customerDTO.getDob())) {
			errorMessages.add("Please select date of birth");
		}
		if (StringUtils.isBlank(customerDTO.getProfession())) {
			errorMessages.add("Please enter profession");
		}
		if(!errorMessages.isEmpty()) {
			responseDTO.setHasAnyError(true);
			responseDTO.setMessages(errorMessages);
			System.out.println(errorMessages);
		}
	}
	
	public boolean validateEmailID(String emailId) {
		Pattern pattern = Pattern.compile(EMAIL_VALIDATION_REGEX);
		Matcher matcher = pattern.matcher(emailId);
		return matcher.find();
	}

}
