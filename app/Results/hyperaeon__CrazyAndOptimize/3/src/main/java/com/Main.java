package com;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.Interface.BasicService;
import com.Interface.BasicServiceImpl;
import com.Interface.ReportService;
import com.Interface.ReportServiceImpl;
import com.Interface.Loan_RecordService;
import com.Interface.Loan_RecordServiceImpl;
import com.Interface.Loan_Record_DetailService;
import com.Interface.Loan_Record_DetailServiceImpl;
import com.Interface.Check_RecordService;
import com.Interface.Check_RecordServiceImpl;
import com.Interface.BasicService;
import com.Interface.BasicServiceImpl;
import com.Interface.ReportService;
import com.Interface.ReportServiceImpl;
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
public BasicService basicservice(){

return  new BasicServiceImpl(); 
    }



@Bean
public ReportService reportservice(){

return  new ReportServiceImpl(); 
    }



@Bean
public Loan_RecordService loan_recordservice(){

return  new Loan_RecordServiceImpl(); 
    }



@Bean
public Loan_Record_DetailService loan_record_detailservice(){

return  new Loan_Record_DetailServiceImpl(); 
    }



@Bean
public Check_RecordService check_recordservice(){

return  new Check_RecordServiceImpl(); 
    }



@Bean
public BasicService basicservice(){

return  new BasicServiceImpl(); 
    }



@Bean
public ReportService reportservice(){

return  new ReportServiceImpl(); 
    }



}