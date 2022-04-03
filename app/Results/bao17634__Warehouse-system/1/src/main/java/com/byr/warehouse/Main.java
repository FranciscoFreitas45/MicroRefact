package com.byr.warehouse;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.byr.warehouse.Interface.ApplyEnterService;
import com.byr.warehouse.Interface.ApplyEnterServiceImpl;
import com.byr.warehouse.Interface.ApplyOutService;
import com.byr.warehouse.Interface.ApplyOutServiceImpl;
import com.byr.warehouse.Interface.EntrepotStatusService;
import com.byr.warehouse.Interface.EntrepotStatusServiceImpl;
import com.byr.warehouse.Interface.ReportService;
import com.byr.warehouse.Interface.ReportServiceImpl;
import com.byr.warehouse.Interface.ExcelService;
import com.byr.warehouse.Interface.ExcelServiceImpl;
import com.byr.warehouse.Interface.LogService;
import com.byr.warehouse.Interface.LogServiceImpl;
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
public ApplyEnterService applyenterservice(){

return  new ApplyEnterServiceImpl(); 
    }



@Bean
public ApplyOutService applyoutservice(){

return  new ApplyOutServiceImpl(); 
    }



@Bean
public EntrepotStatusService entrepotstatusservice(){

return  new EntrepotStatusServiceImpl(); 
    }



@Bean
public ReportService reportservice(){

return  new ReportServiceImpl(); 
    }



@Bean
public ExcelService excelservice(){

return  new ExcelServiceImpl(); 
    }



@Bean
public LogService logservice(){

return  new LogServiceImpl(); 
    }



}