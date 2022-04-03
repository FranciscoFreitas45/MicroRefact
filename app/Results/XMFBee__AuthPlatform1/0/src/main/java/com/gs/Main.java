package com.gs;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.gs.Interface.MaintainRecord;
import com.gs.Interface.MaintainRecordImpl;
import com.gs.Interface.Accessories;
import com.gs.Interface.AccessoriesImpl;
import com.gs.Interface.AccessoriesService;
import com.gs.Interface.AccessoriesServiceImpl;
import com.gs.Interface.WorkInfoService;
import com.gs.Interface.WorkInfoServiceImpl;
import com.gs.Interface.WorkInfo;
import com.gs.Interface.WorkInfoImpl;
import com.gs.Interface.MaintainRecord;
import com.gs.Interface.MaintainRecordImpl;
import com.gs.Interface.Accessories;
import com.gs.Interface.AccessoriesImpl;
import com.gs.Interface.AccessoriesService;
import com.gs.Interface.AccessoriesServiceImpl;
import com.gs.Interface.RoleService;
import com.gs.Interface.RoleServiceImpl;
import com.gs.Interface.WorkInfoService;
import com.gs.Interface.WorkInfoServiceImpl;
import com.gs.Interface.MaintainRecord;
import com.gs.Interface.MaintainRecordImpl;
import com.gs.Interface.Accessories;
import com.gs.Interface.AccessoriesImpl;
import com.gs.Interface.MaintainRecord;
import com.gs.Interface.MaintainRecordImpl;
import com.gs.Interface.User;
import com.gs.Interface.UserImpl;
import com.gs.Interface.MaintainFix;
import com.gs.Interface.MaintainFixImpl;
import com.gs.Interface.MaintainRecord;
import com.gs.Interface.MaintainRecordImpl;
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
public Accessories accessories(){

return  new AccessoriesImpl(); 
    }



@Bean
public AccessoriesService accessoriesservice(){

return  new AccessoriesServiceImpl(); 
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
public MaintainRecord maintainrecord(){

return  new MaintainRecordImpl(); 
    }



@Bean
public Accessories accessories(){

return  new AccessoriesImpl(); 
    }



@Bean
public AccessoriesService accessoriesservice(){

return  new AccessoriesServiceImpl(); 
    }



@Bean
public RoleService roleservice(){

return  new RoleServiceImpl(); 
    }



@Bean
public WorkInfoService workinfoservice(){

return  new WorkInfoServiceImpl(); 
    }



@Bean
public MaintainRecord maintainrecord(){

return  new MaintainRecordImpl(); 
    }



@Bean
public Accessories accessories(){

return  new AccessoriesImpl(); 
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
public MaintainFix maintainfix(){

return  new MaintainFixImpl(); 
    }



@Bean
public MaintainRecord maintainrecord(){

return  new MaintainRecordImpl(); 
    }



}