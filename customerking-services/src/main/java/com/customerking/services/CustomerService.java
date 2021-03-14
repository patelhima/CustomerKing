package com.customerking.services;

import com.customerking.CustomerDTO;
import com.customerking.commons.CustomerResponseDTO;

public interface CustomerService {

	CustomerResponseDTO addCustomer(CustomerDTO customerDTO);

	CustomerResponseDTO getAllCustomers(int page, int limit, String sortdatafield, String sortorder);

	CustomerDTO getCustomerInfo(Long id);

	CustomerResponseDTO deleteCustomer(Long id);

	CustomerResponseDTO searchCustomer(String searchText);

}
