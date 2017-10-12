package kr.tedchoi.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import kr.tedchoi.www.model.Customer;
import kr.tedchoi.www.repo.CustomerRepository;
import kr.tedchoi.www.service.CustomerService;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

	@Autowired
	CustomerRepository repository;
	@Autowired
	CustomerService customerService;

	@CrossOrigin
	@GetMapping(value = "/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable long id) {
		return new ResponseEntity<Customer>(repository.findOne(id), HttpStatus.OK);
	}

	@CrossOrigin
	@GetMapping(value = "/customers")
	public ResponseEntity<List<Customer>> getCustomers() {
		return new ResponseEntity<List<Customer>>(repository.findAll(), HttpStatus.OK);
	}
	
	@CrossOrigin
	@PostMapping(value = "/customers")
	public ResponseEntity<Integer> createCustomer(@RequestBody List<Customer> customer) {
		return customerService.registCustomers(customer);
	}
}
