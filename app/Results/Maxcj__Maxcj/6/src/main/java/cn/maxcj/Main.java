package cn.maxcj;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.maxcj.Interface.IUserService;
import cn.maxcj.Interface.IUserServiceImpl;
import cn.maxcj.Interface.IDeptService;
import cn.maxcj.Interface.IDeptServiceImpl;
import cn.maxcj.Interface.IClubinfoService;
import cn.maxcj.Interface.IClubinfoServiceImpl;
import cn.maxcj.Interface.IActivityService;
import cn.maxcj.Interface.IActivityServiceImpl;
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
public IUserService iuserservice(){

return  new IUserServiceImpl(); 
    }



@Bean
public IDeptService ideptservice(){

return  new IDeptServiceImpl(); 
    }



@Bean
public IClubinfoService iclubinfoservice(){

return  new IClubinfoServiceImpl(); 
    }



@Bean
public IActivityService iactivityservice(){

return  new IActivityServiceImpl(); 
    }



}