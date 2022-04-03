package com.cg.sprint.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.cg.sprint.entity.Customer;
public interface CustomerDao extends JpaRepository<Customer, Integer>{


@Query("select c from Customer c where account_no=?1")
public Customer getAccountData(int acc_no)
;

}