package com.byr.warehouse;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.byr.warehouse.Interface.EntrepotStatusRepository;
import com.byr.warehouse.Interface.EntrepotStatusRepositoryImpl;
import com.byr.warehouse.Interface.DaliyComputeShedule;
import com.byr.warehouse.Interface.DaliyComputeSheduleImpl;
import com.byr.warehouse.Interface.LogService;
import com.byr.warehouse.Interface.LogServiceImpl;
import com.byr.warehouse.Interface.EntrepotStatusRepository;
import com.byr.warehouse.Interface.EntrepotStatusRepositoryImpl;
import com.byr.warehouse.Interface.RelationShipRepository;
import com.byr.warehouse.Interface.RelationShipRepositoryImpl;
import com.byr.warehouse.Interface.ApplyOutPutRepository;
import com.byr.warehouse.Interface.ApplyOutPutRepositoryImpl;
import com.byr.warehouse.Interface.ApplyOutPutRepository;
import com.byr.warehouse.Interface.ApplyOutPutRepositoryImpl;
import com.byr.warehouse.Interface.EntrepotStatusRepository;
import com.byr.warehouse.Interface.EntrepotStatusRepositoryImpl;
import com.byr.warehouse.Interface.ExcelService;
import com.byr.warehouse.Interface.ExcelServiceImpl;
import com.byr.warehouse.Interface.RelationShipRepository;
import com.byr.warehouse.Interface.RelationShipRepositoryImpl;
import com.byr.warehouse.Interface.EntrepotStatusService;
import com.byr.warehouse.Interface.EntrepotStatusServiceImpl;
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
public EntrepotStatusRepository entrepotstatusrepository(){

return  new EntrepotStatusRepositoryImpl(); 
    }



@Bean
public DaliyComputeShedule daliycomputeshedule(){

return  new DaliyComputeSheduleImpl(); 
    }



@Bean
public LogService logservice(){

return  new LogServiceImpl(); 
    }



@Bean
public EntrepotStatusRepository entrepotstatusrepository(){

return  new EntrepotStatusRepositoryImpl(); 
    }



@Bean
public RelationShipRepository relationshiprepository(){

return  new RelationShipRepositoryImpl(); 
    }



@Bean
public ApplyOutPutRepository applyoutputrepository(){

return  new ApplyOutPutRepositoryImpl(); 
    }



@Bean
public ApplyOutPutRepository applyoutputrepository(){

return  new ApplyOutPutRepositoryImpl(); 
    }



@Bean
public EntrepotStatusRepository entrepotstatusrepository(){

return  new EntrepotStatusRepositoryImpl(); 
    }



@Bean
public ExcelService excelservice(){

return  new ExcelServiceImpl(); 
    }



@Bean
public RelationShipRepository relationshiprepository(){

return  new RelationShipRepositoryImpl(); 
    }



@Bean
public EntrepotStatusService entrepotstatusservice(){

return  new EntrepotStatusServiceImpl(); 
    }



}