package com.gs;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gs.Interface.CheckinService;
import com.gs.Interface.CheckinServiceImpl;
import com.gs.Interface.MaintainRecordService;
import com.gs.Interface.MaintainRecordServiceImpl;
import com.gs.Interface.UserService;
import com.gs.Interface.UserServiceImpl;
import com.gs.Interface.UserService;
import com.gs.Interface.UserServiceImpl;
import com.gs.Interface.MaintainRecordService;
import com.gs.Interface.MaintainRecordServiceImpl;
import com.gs.Interface.UserService;
import com.gs.Interface.UserServiceImpl;
import com.gs.Interface.RoleService;
import com.gs.Interface.RoleServiceImpl;
import com.gs.Interface.UserRoleService;
import com.gs.Interface.UserRoleServiceImpl;
import com.gs.Interface.UserService;
import com.gs.Interface.UserServiceImpl;
import com.gs.Interface.MaintainRecordService;
import com.gs.Interface.MaintainRecordServiceImpl;
import com.gs.Interface.AppointmentService;
import com.gs.Interface.AppointmentServiceImpl;
import com.gs.Interface.MaintainDetailService;
import com.gs.Interface.MaintainDetailServiceImpl;
import com.gs.Interface.Checkin;
import com.gs.Interface.CheckinImpl;
import com.gs.Interface.Checkin;
import com.gs.Interface.CheckinImpl;
import com.gs.Interface.User;
import com.gs.Interface.UserImpl;
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
public CheckinService checkinservice(){

return  new CheckinServiceImpl(); 
    }



@Bean
public MaintainRecordService maintainrecordservice(){

return  new MaintainRecordServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public MaintainRecordService maintainrecordservice(){

return  new MaintainRecordServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public RoleService roleservice(){

return  new RoleServiceImpl(); 
    }



@Bean
public UserRoleService userroleservice(){

return  new UserRoleServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public MaintainRecordService maintainrecordservice(){

return  new MaintainRecordServiceImpl(); 
    }



@Bean
public AppointmentService appointmentservice(){

return  new AppointmentServiceImpl(); 
    }



@Bean
public MaintainDetailService maintaindetailservice(){

return  new MaintainDetailServiceImpl(); 
    }



@Bean
public Checkin checkin(){

return  new CheckinImpl(); 
    }



@Bean
public Checkin checkin(){

return  new CheckinImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



}