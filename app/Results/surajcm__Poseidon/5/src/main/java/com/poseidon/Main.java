package com.poseidon;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.poseidon.Interface.CustomerService;
import com.poseidon.Interface.CustomerServiceImpl;
import com.poseidon.Interface.CustomerVO;
import com.poseidon.Interface.CustomerVOImpl;
import com.poseidon.Interface.CompanyTermsService;
import com.poseidon.Interface.CompanyTermsServiceImpl;
import com.poseidon.Interface.InvoiceService;
import com.poseidon.Interface.InvoiceServiceImpl;
import com.poseidon.Interface.TransactionDAO;
import com.poseidon.Interface.TransactionDAOImpl;
import com.poseidon.Interface.MakeDao;
import com.poseidon.Interface.MakeDaoImpl;
@SpringBootApplication
public class Main {


@Bean
public RestTemplate restTemplate(){
 
 return new RestTemplate();

  }



public static void main(String[] args){

SpringApplication.run(Main.class,args);

   }



@Bean
public CustomerService customerservice(){

return  new CustomerServiceImpl(); 
    }



@Bean
public CustomerVO customervo(){

return  new CustomerVOImpl(); 
    }



@Bean
public CompanyTermsService companytermsservice(){

return  new CompanyTermsServiceImpl(); 
    }



@Bean
public InvoiceService invoiceservice(){

return  new InvoiceServiceImpl(); 
    }



@Bean
public TransactionDAO transactiondao(){

return  new TransactionDAOImpl(); 
    }



@Bean
public MakeDao makedao(){

return  new MakeDaoImpl(); 
    }



}