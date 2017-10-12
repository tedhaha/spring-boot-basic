package kr.tedchoi.www.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import kr.tedchoi.www.model.Customer;

public interface CustomerService {

	ResponseEntity<Integer> registCustomers(List<Customer> customerList);
}
