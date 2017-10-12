package kr.tedchoi.www.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import kr.tedchoi.www.model.Customer;
import kr.tedchoi.www.repo.CustomerRepository;
import kr.tedchoi.www.service.CustomerService;


/*
 * (8/15) 8:00 - 16:00 까지만 신청을 받으며, 최대 100명까지만 받는다.
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerRepository repository;

	@Override
	public ResponseEntity<Integer> registCustomers(List<Customer> customerList) {

		
		// validation1 : 인원수 체크
		// (등록 요청수 + DB 현재 인원수)가 100을 넘는다면 에러를 리턴하면서 몇 명까지 신청가능한지 알려준다.
		int registerdSize = repository.findAll().size();
		if(customerList.size() + registerdSize > 100) {
			return new ResponseEntity<Integer>(100 - registerdSize, HttpStatus.INSUFFICIENT_STORAGE);
		}
		
		// validation2 : 참여 신청 기간 (8/15) 8:00 - 16:00 
//		long currentTimeM = Calendar.getInstance().getTimeInMillis();
//		long inDayStartM = new GregorianCalendar(2017, 7, 15, 8, 0, 0).getTimeInMillis();
//		long inDayEndM =  new GregorianCalendar(2017, 7, 15, 16, 0, 0).getTimeInMillis();
//		if(  !((currentTimeM > inDayStartM) && (currentTimeM < inDayEndM)) ) {
//			return new ResponseEntity<Integer>(0, HttpStatus.LOCKED);
//		}
		
		// 신청자 먼저 저장해서 Id를 받아온다. 
		Customer customer = new Customer(
				customerList.get(0).getName(),
				customerList.get(0).getPhone(),
				customerList.get(0).getEmail(),
				customerList.get(0).getSex(),
				customerList.get(0).getAge());
		long parentId = repository.save(customer).getId();
		
		// 해당 신청자id를 부모로하는 동반Customers 저장
		for(int i=1; i<customerList.size(); i++) {
			customer = new Customer(
					customerList.get(i).getName(),
					customerList.get(i).getPhone(),
					customerList.get(i).getEmail(),
					customerList.get(i).getSex(),
					customerList.get(i).getAge(),
					parentId
					);
			repository.save(customer);
		}
		
		return new ResponseEntity<Integer>(0, HttpStatus.CREATED);
	}
}
