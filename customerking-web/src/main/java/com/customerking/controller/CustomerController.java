package com.customerking.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.customerking.CustomerDTO;
import com.customerking.commons.CustomerResponseDTO;
import com.customerking.services.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerServiceImpl;
	
	@GetMapping(value = { "/home/{id}" })
	public ModelAndView customerForm(ModelAndView model,@PathVariable Long id) {
		LOGGER.info("custome form");
		CustomerDTO customerDTO = customerServiceImpl.getCustomerInfo(id);
		model.setViewName("customerform");
		model.addObject("customer", customerDTO);
		return model;
	}
	
	@PostMapping(value = "/addCustomer", produces = { "application/json" })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody CustomerResponseDTO addCustomer(@RequestBody CustomerDTO customerDTO) {
		return customerServiceImpl.addCustomer(customerDTO);
	}
	
	@GetMapping(value = "/getCustomers")
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody CustomerResponseDTO getCustomers(@RequestParam(value = "pagenum") int page,
            @RequestParam(value = "pagesize") int limit,
            @RequestParam(value = "groupscount", required = false) String groupscount,
            @RequestParam(value = "filterscount", required = false) String filterscount,
            @RequestParam(value = "sortdatafield", required = false) String sortdatafield,
			@RequestParam(value = "sortorder", required = false) String sortorder,
            @RequestParam(value = "recordstartindex", required = false) int recordstartindex,
            @RequestParam(value = "recordendindex", required = false) int recordendindex) {
		return this.customerServiceImpl.getAllCustomers(page, limit, sortdatafield, sortorder);
	}
	
	@PostMapping(value = "/deleteCustomer/{id}", produces = { "application/json" })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody CustomerResponseDTO deleteCustomer(@PathVariable Long id) {
		return customerServiceImpl.deleteCustomer(id);
	}
	
	@GetMapping(value = { "/search" })
	public ModelAndView search(ModelAndView model) {
		model.setViewName("searchResult");
		return model;
	}
	
	@PostMapping(value = "/searchCustomer", produces = { "application/json" })
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody CustomerResponseDTO searchCustomer(@RequestParam String searchText) {
		return customerServiceImpl.searchCustomer(searchText);
	}
}
