package com.gs;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gs.Interface.MaintainRecord;
import com.gs.Interface.MaintainRecordImpl;
import com.gs.Interface.User;
import com.gs.Interface.UserImpl;
import com.gs.Interface.MaintainRecord;
import com.gs.Interface.MaintainRecordImpl;
import com.gs.Interface.CheckinService;
import com.gs.Interface.CheckinServiceImpl;
import com.gs.Interface.MaintainRecordService;
import com.gs.Interface.MaintainRecordServiceImpl;
import com.gs.Interface.IncomingOutgoingService;
import com.gs.Interface.IncomingOutgoingServiceImpl;
import com.gs.Interface.MaintainRecordService;
import com.gs.Interface.MaintainRecordServiceImpl;
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
public MaintainRecord maintainrecord(){

return  new MaintainRecordImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public MaintainRecord maintainrecord(){

return  new MaintainRecordImpl(); 
    }



@Bean
public CheckinService checkinservice(){

return  new CheckinServiceImpl(); 
    }



@Bean
public MaintainRecordService maintainrecordservice(){

return  new MaintainRecordServiceImpl(); 
    }



@Bean
public IncomingOutgoingService incomingoutgoingservice(){

return  new IncomingOutgoingServiceImpl(); 
    }



@Bean
public MaintainRecordService maintainrecordservice(){

return  new MaintainRecordServiceImpl(); 
    }



}