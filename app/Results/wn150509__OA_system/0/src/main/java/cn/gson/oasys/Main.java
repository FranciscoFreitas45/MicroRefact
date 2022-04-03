package cn.gson.oasys;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.ProcessService;
import cn.gson.oasys.Interface.ProcessServiceImpl;
import cn.gson.oasys.Interface.FileServices;
import cn.gson.oasys.Interface.FileServicesImpl;
import cn.gson.oasys.Interface.AttachService;
import cn.gson.oasys.Interface.AttachServiceImpl;
import cn.gson.oasys.Interface.AttachmentDao;
import cn.gson.oasys.Interface.AttachmentDaoImpl;
import cn.gson.oasys.Interface.MailServices;
import cn.gson.oasys.Interface.MailServicesImpl;
import cn.gson.oasys.Interface.AttachmentDao;
import cn.gson.oasys.Interface.AttachmentDaoImpl;
import cn.gson.oasys.Interface.AttachmentDao;
import cn.gson.oasys.Interface.AttachmentDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.DeptDao;
import cn.gson.oasys.Interface.DeptDaoImpl;
import cn.gson.oasys.Interface.PositionDao;
import cn.gson.oasys.Interface.PositionDaoImpl;
import cn.gson.oasys.Interface.RoleDao;
import cn.gson.oasys.Interface.RoleDaoImpl;
import cn.gson.oasys.Interface.DaymanageDao;
import cn.gson.oasys.Interface.DaymanageDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.StatusDao;
import cn.gson.oasys.Interface.StatusDaoImpl;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.TypeDaoImpl;
import cn.gson.oasys.Interface.ProcessService;
import cn.gson.oasys.Interface.ProcessServiceImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.DaymanageDao;
import cn.gson.oasys.Interface.DaymanageDaoImpl;
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
public ProcessService processservice(){

return  new ProcessServiceImpl(); 
    }



@Bean
public FileServices fileservices(){

return  new FileServicesImpl(); 
    }



@Bean
public AttachService attachservice(){

return  new AttachServiceImpl(); 
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
public AttachmentDao attachmentdao(){

return  new AttachmentDaoImpl(); 
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
public RoleDao roledao(){

return  new RoleDaoImpl(); 
    }



@Bean
public DaymanageDao daymanagedao(){

return  new DaymanageDaoImpl(); 
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
public ProcessService processservice(){

return  new ProcessServiceImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public DaymanageDao daymanagedao(){

return  new DaymanageDaoImpl(); 
    }



}