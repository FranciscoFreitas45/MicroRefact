package cn.gson.oasys;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.DeptDao;
import cn.gson.oasys.Interface.DeptDaoImpl;
import cn.gson.oasys.Interface.RoleDao;
import cn.gson.oasys.Interface.RoleDaoImpl;
import cn.gson.oasys.Interface.PositionDao;
import cn.gson.oasys.Interface.PositionDaoImpl;
import cn.gson.oasys.Interface.StatusDao;
import cn.gson.oasys.Interface.StatusDaoImpl;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.TypeDaoImpl;
import cn.gson.oasys.Interface.AttachmentDao;
import cn.gson.oasys.Interface.AttachmentDaoImpl;
import cn.gson.oasys.Interface.MailServices;
import cn.gson.oasys.Interface.MailServicesImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.StatusDao;
import cn.gson.oasys.Interface.StatusDaoImpl;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.TypeDaoImpl;
import cn.gson.oasys.Interface.AttachmentDao;
import cn.gson.oasys.Interface.AttachmentDaoImpl;
import cn.gson.oasys.Interface.AttendceDao;
import cn.gson.oasys.Interface.AttendceDaoImpl;
import cn.gson.oasys.Interface.User;
import cn.gson.oasys.Interface.UserImpl;
import cn.gson.oasys.Interface.User;
import cn.gson.oasys.Interface.UserImpl;
import cn.gson.oasys.Interface.User;
import cn.gson.oasys.Interface.UserImpl;
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



@Bean
public PositionDao positiondao(){

return  new PositionDaoImpl(); 
    }



@Bean
public StatusDao statusdao(){

return  new StatusDaoImpl(); 
    }



@Bean
public TypeDao typedao(){

return  new TypeDaoImpl(); 
    }



@Bean
public AttachmentDao attachmentdao(){

return  new AttachmentDaoImpl(); 
    }



@Bean
public MailServices mailservices(){

return  new MailServicesImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public StatusDao statusdao(){

return  new StatusDaoImpl(); 
    }



@Bean
public TypeDao typedao(){

return  new TypeDaoImpl(); 
    }



@Bean
public AttachmentDao attachmentdao(){

return  new AttachmentDaoImpl(); 
    }



@Bean
public AttendceDao attendcedao(){

return  new AttendceDaoImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



@Bean
public User user(){

return  new UserImpl(); 
    }



}