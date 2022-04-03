package com.sprint2;
 import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.sprint2.repository.AdminRepository;
import com.sprint2.repository.ContractRepository;
import com.sprint2.repository.CustomerRepository;
import com.sprint2.repository.LandRepository;
import com.sprint2.repository.OrderRepository;
import com.sprint2.repository.ProductRepository;
import com.sprint2.repository.SchedulerRepository;
import com.sprint2.repository.UserRepository;
// single annotation is equivalent to @Configuration,@EnableAutoConfiguration,@ComponentScan
@SpringBootApplication
public class Application {

// to establish relation with admin repository
@Autowired
 private AdminRepository adminrepo;

// to establish relationship with user repository
@Autowired
 private UserRepository userrep;

// to establish relationship with land repository
@Autowired
 private LandRepository landrep;

// to establish relationship with order repository
@Autowired
 private ProductRepository productrep;

// to establish relationship with product repository
@Autowired
 private ContractRepository contractrep;

// to establish relationship with contract repository
@Autowired
 private SchedulerRepository schrep;

// to establish relationship with product repository
@Autowired
 private CustomerRepository customerrep;

// to establish relationship with contract repository
@Autowired
 private OrderRepository orderrep;


public void main(String[] args){
    SpringApplication.run(Application.class, args);
}


}