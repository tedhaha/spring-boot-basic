package kr.tedchoi.www.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.tedchoi.www.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
