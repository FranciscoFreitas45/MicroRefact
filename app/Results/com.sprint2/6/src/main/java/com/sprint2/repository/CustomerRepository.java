package com.sprint2.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sprint2.model.Customer;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{


}