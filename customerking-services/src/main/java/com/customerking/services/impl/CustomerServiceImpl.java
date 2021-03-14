package com.customerking.services.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.customerking.CustomerDTO;
import com.customerking.CustomerGridDTO;
import com.customerking.commons.CustomerResponseDTO;
import com.customerking.helpers.LuceneIndexHelper;
import com.customerking.model.Customers;
import com.customerking.repo.CustomersRepository;
import com.customerking.services.CustomerService;
import com.customerking.validators.CustomerValidator;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired
	private CustomerValidator customerValidator;

	@Autowired
	private CustomersRepository customersRepository;
	
	@Autowired
	private LuceneIndexHelper luceneIndexHelper;
	
	@Value("${spring.datasource.url}")
	String dbURL;
	
	@Value("${spring.datasource.username}")
	String dbUsername;
	
	@Value("${spring.datasource.password}")
	String dbPassword;
	
	@Override
	public CustomerResponseDTO addCustomer(CustomerDTO customerDTO) {
		CustomerResponseDTO responseDTO = new CustomerResponseDTO();
		this.customerValidator.addCustomerValidator(responseDTO, customerDTO);
		if(Boolean.FALSE.equals(responseDTO.getHasAnyError())) {
			Customers customer = new Customers();
			if(customerDTO.getId()!=null && customerDTO.getId()!=0L) {
				Optional<Customers> customerOptional = this.customersRepository.findById(customerDTO.getId());
				if(customerOptional.isPresent()) {
					customer = customerOptional.get();
				}
			}
			BeanUtils.copyProperties(customerDTO, customer);
			customer.setIsCustomerActive(true);
			customersRepository.saveAndFlush(customer);
			luceneIndexHelper.createIndex(customer);
			LOGGER.info("Customer {} saved successfully", customer.getName());
			responseDTO.setMessages(Arrays.asList(String.format("Customer %s Saved Successfully", customer.getName())));
		}
		return responseDTO;
	}

	@Override
	public CustomerResponseDTO getAllCustomers(int page, int limit, String sortdatafield, String sortorder) {
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		CustomerGridDTO customerGridDTO = new CustomerGridDTO();
		try {
			String countQuery = "select count(*) from Customers where isCustomerActive = 1";
			String query = "select  id as 'id', name as 'Customer Name', address as 'Customer Address', email as 'Email ID', gender as 'Gender', dob as 'Date of Birth', profession as 'Customer Profession'  from Customers where isCustomerActive = 1 order by id OFFSET "
					+ (page * limit) + " ROWS FETCH NEXT " + limit + " ROWS ONLY";
			customerGridDTO = getGridData(countQuery, query);
			customerResponseDTO.setData(Arrays.asList(customerGridDTO,customerGridDTO.getCount()));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return customerResponseDTO;
	}

	@Override
	public CustomerDTO getCustomerInfo(Long id) {
		CustomerDTO customerDTO = new CustomerDTO();
		if(id!=null && id!=0) {
			LOGGER.info("Fetching customer details for id {}", id);
			Optional<Customers> customerOptional = this.customersRepository.findById(id);
			if(customerOptional.isPresent()) {
				Customers customer = customerOptional.get();
				BeanUtils.copyProperties(customer, customerDTO);
				LOGGER.info("Customer details fetched for id {}, name {}", id, customer.getName());
			}
		}
		return customerDTO;
	}

	@Override
	public CustomerResponseDTO deleteCustomer(Long id) {
		LOGGER.info("Deleting customer details for id {}", id);
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		if(id!=null && id!=0) {
			Optional<Customers> customerOptional = this.customersRepository.findById(id);
			if(customerOptional.isPresent()) {
				Customers customer = customerOptional.get();
				customer.setIsCustomerActive(false);
				this.customersRepository.saveAndFlush(customer);
				LOGGER.info("Customer with id {}, name {} deleted", id, customer.getName());
				customerResponseDTO.setMessages(Arrays.asList(String.format("Customer %s deleted successfully",customer.getName())));
			}
		}
		return customerResponseDTO;
	}

	@Override
	public CustomerResponseDTO searchCustomer(String searchText) {
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO();
		List<Long> matchedIds = luceneIndexHelper.readIndex(searchText);
		CustomerGridDTO customerGridDTO = new CustomerGridDTO();
		try {
			String idList = StringUtils.join(matchedIds, ',');
			String countQuery = "select count(*) from Customers where isCustomerActive = 1 and id in ( " + idList + " )";
			String query = "select  id as 'id', name as 'Customer Name', address as 'Customer Address', email as 'Email ID', gender as 'Gender', dob as 'Date of Birth', profession as 'Customer Profession'  from Customers "
					+ "where isCustomerActive = 1 and id in ( " + idList + " ) order by id";
			customerGridDTO = getGridData(countQuery, query);

			customerResponseDTO.setData(Arrays.asList(customerGridDTO, customerGridDTO.getCount()));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return customerResponseDTO;
	}

	@SuppressWarnings("unchecked")
	private CustomerGridDTO getGridData(String countQuery, String query) {
		Connection con = null;
		Statement statement = null;
		JSONArray gridRecords = new JSONArray();
		CustomerGridDTO customerGridDTO = new CustomerGridDTO();
		try {
			//DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			DriverManager.registerDriver(new org.h2.Driver());
			con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
			statement = con.createStatement();
			LOGGER.info("Executing query for count :: {}",countQuery);
			ResultSet resultCount = statement.executeQuery(countQuery);
			String count = "0";
			while (resultCount.next()) {
				count = resultCount.getString(1);
			}
			LOGGER.info("Executing query :: {}",query);
			ResultSet result = statement.executeQuery(query);
			ResultSetMetaData columnInfo = result.getMetaData();
			int columnCount = columnInfo.getColumnCount();

			List<String> columnNames = new ArrayList<String>();
			for (int i = 1; i <= columnCount; i++) {
				columnNames.add(columnInfo.getColumnName(i));
			}

			while (result.next()) {
				JSONObject jsonObject = new JSONObject();
				for (int eachColumn = 0; eachColumn < columnNames.size(); eachColumn++) {
					String resultData = result.getString(eachColumn + 1);
					if (resultData == null || resultData.equalsIgnoreCase("null")) {
						resultData = "null";
					}
					jsonObject.put(columnNames.get(eachColumn), resultData);
				}
				gridRecords.add(jsonObject);
			}

			customerGridDTO.setColumnNames(columnNames);
			customerGridDTO.setGridRecords(gridRecords);
			customerGridDTO.setCount(count);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					LOGGER.error(e.getMessage(), e);
				}
			}
		}
		return customerGridDTO;
	}
}
