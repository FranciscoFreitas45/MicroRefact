package com.gs;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gs.Interface.Checkin;
import com.gs.Interface.CheckinImpl;
import com.gs.Interface.MaterialListService;
import com.gs.Interface.MaterialListServiceImpl;
import com.gs.Interface.WorkInfoService;
import com.gs.Interface.WorkInfoServiceImpl;
import com.gs.Interface.WorkInfo;
import com.gs.Interface.WorkInfoImpl;
import com.gs.Interface.CompanyService;
import com.gs.Interface.CompanyServiceImpl;
import com.gs.Interface.ChargeBillService;
import com.gs.Interface.ChargeBillServiceImpl;
import com.gs.Interface.UserService;
import com.gs.Interface.UserServiceImpl;
import com.gs.Interface.AppointmentService;
import com.gs.Interface.AppointmentServiceImpl;
import com.gs.Interface.IncomingOutgoingService;
import com.gs.Interface.IncomingOutgoingServiceImpl;
import com.gs.Interface.RemindService;
import com.gs.Interface.RemindServiceImpl;
import com.gs.Interface.Company;
import com.gs.Interface.CompanyImpl;
import com.gs.Interface.Accessories;
import com.gs.Interface.AccessoriesImpl;
import com.gs.Interface.Company;
import com.gs.Interface.CompanyImpl;
import com.gs.Interface.Accessories;
import com.gs.Interface.AccessoriesImpl;
import com.gs.Interface.AccessoriesType;
import com.gs.Interface.AccessoriesTypeImpl;
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
public Checkin checkin(){

return  new CheckinImpl(); 
    }



@Bean
public MaterialListService materiallistservice(){

return  new MaterialListServiceImpl(); 
    }



@Bean
public WorkInfoService workinfoservice(){

return  new WorkInfoServiceImpl(); 
    }



@Bean
public WorkInfo workinfo(){

return  new WorkInfoImpl(); 
    }



@Bean
public CompanyService companyservice(){

return  new CompanyServiceImpl(); 
    }



@Bean
public ChargeBillService chargebillservice(){

return  new ChargeBillServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public AppointmentService appointmentservice(){

return  new AppointmentServiceImpl(); 
    }



@Bean
public IncomingOutgoingService incomingoutgoingservice(){

return  new IncomingOutgoingServiceImpl(); 
    }



@Bean
public RemindService remindservice(){

return  new RemindServiceImpl(); 
    }



@Bean
public Company company(){

return  new CompanyImpl(); 
    }



@Bean
public Accessories accessories(){

return  new AccessoriesImpl(); 
    }



@Bean
public Company company(){

return  new CompanyImpl(); 
    }



@Bean
public Accessories accessories(){

return  new AccessoriesImpl(); 
    }



@Bean
public AccessoriesType accessoriestype(){

return  new AccessoriesTypeImpl(); 
    }



}