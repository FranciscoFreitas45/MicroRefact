package cn.gson.oasys;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.gson.oasys.Interface.FileServices;
import cn.gson.oasys.Interface.FileServicesImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.UserService;
import cn.gson.oasys.Interface.UserServiceImpl;
import cn.gson.oasys.Interface.AttachmentDao;
import cn.gson.oasys.Interface.AttachmentDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.DeptDao;
import cn.gson.oasys.Interface.DeptDaoImpl;
import cn.gson.oasys.Interface.PositionDao;
import cn.gson.oasys.Interface.PositionDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.DeptDao;
import cn.gson.oasys.Interface.DeptDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.DeptDao;
import cn.gson.oasys.Interface.DeptDaoImpl;
import cn.gson.oasys.Interface.RoleDao;
import cn.gson.oasys.Interface.RoleDaoImpl;
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
public FileServices fileservices(){

return  new FileServicesImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public AttachmentDao attachmentdao(){

return  new AttachmentDaoImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public DeptDao deptdao(){

return  new DeptDaoImpl(); 
    }



@Bean
public PositionDao positiondao(){

return  new PositionDaoImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public DeptDao deptdao(){

return  new DeptDaoImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public DeptDao deptdao(){

return  new DeptDaoImpl(); 
    }



@Bean
public RoleDao roledao(){

return  new RoleDaoImpl(); 
    }



}