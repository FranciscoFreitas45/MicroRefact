package cn.maxcj;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.maxcj.Interface.RoleMapper;
import cn.maxcj.Interface.RoleMapperImpl;
import cn.maxcj.Interface.DeptMapper;
import cn.maxcj.Interface.DeptMapperImpl;
import cn.maxcj.Interface.DictMapper;
import cn.maxcj.Interface.DictMapperImpl;
import cn.maxcj.Interface.UserMapper;
import cn.maxcj.Interface.UserMapperImpl;
import cn.maxcj.Interface.MenuMapper;
import cn.maxcj.Interface.MenuMapperImpl;
import cn.maxcj.Interface.ActivityMapper;
import cn.maxcj.Interface.ActivityMapperImpl;
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
public RoleMapper rolemapper(){

return  new RoleMapperImpl(); 
    }



@Bean
public DeptMapper deptmapper(){

return  new DeptMapperImpl(); 
    }



@Bean
public DictMapper dictmapper(){

return  new DictMapperImpl(); 
    }



@Bean
public UserMapper usermapper(){

return  new UserMapperImpl(); 
    }



@Bean
public MenuMapper menumapper(){

return  new MenuMapperImpl(); 
    }



@Bean
public ActivityMapper activitymapper(){

return  new ActivityMapperImpl(); 
    }



}