package cn.gson.oasys;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.TypeDaoImpl;
import cn.gson.oasys.Interface.StatusDao;
import cn.gson.oasys.Interface.StatusDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.StatusDao;
import cn.gson.oasys.Interface.StatusDaoImpl;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.TypeDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.DeptDao;
import cn.gson.oasys.Interface.DeptDaoImpl;
import cn.gson.oasys.Interface.PositionDao;
import cn.gson.oasys.Interface.PositionDaoImpl;
import cn.gson.oasys.Interface.MailreciverDao;
import cn.gson.oasys.Interface.MailreciverDaoImpl;
import cn.gson.oasys.Interface.MenuSysService;
import cn.gson.oasys.Interface.MenuSysServiceImpl;
import cn.gson.oasys.Interface.StatusDao;
import cn.gson.oasys.Interface.StatusDaoImpl;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.TypeDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.AttendceDao;
import cn.gson.oasys.Interface.AttendceDaoImpl;
import cn.gson.oasys.Interface.DiscussDao;
import cn.gson.oasys.Interface.DiscussDaoImpl;
import cn.gson.oasys.Interface.FileListdao;
import cn.gson.oasys.Interface.FileListdaoImpl;
import cn.gson.oasys.Interface.PlanDao;
import cn.gson.oasys.Interface.PlanDaoImpl;
import cn.gson.oasys.Interface.UserLogDao;
import cn.gson.oasys.Interface.UserLogDaoImpl;
import cn.gson.oasys.Interface.ProcessListDao;
import cn.gson.oasys.Interface.ProcessListDaoImpl;
import cn.gson.oasys.Interface.MailreciverDao;
import cn.gson.oasys.Interface.MailreciverDaoImpl;
import cn.gson.oasys.Interface.TaskuserDao;
import cn.gson.oasys.Interface.TaskuserDaoImpl;
import cn.gson.oasys.Interface.RolepowerlistDao;
import cn.gson.oasys.Interface.RolepowerlistDaoImpl;
import cn.gson.oasys.Interface.DaymanageServices;
import cn.gson.oasys.Interface.DaymanageServicesImpl;
import cn.gson.oasys.Interface.DaymanageDao;
import cn.gson.oasys.Interface.DaymanageDaoImpl;
import cn.gson.oasys.Interface.StatusDao;
import cn.gson.oasys.Interface.StatusDaoImpl;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.TypeDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.DeptDao;
import cn.gson.oasys.Interface.DeptDaoImpl;
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
public TypeDao typedao(){

return  new TypeDaoImpl(); 
    }



@Bean
public StatusDao statusdao(){

return  new StatusDaoImpl(); 
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
public UserDao userdao(){

return  new UserDaoImpl(); 
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
public MailreciverDao mailreciverdao(){

return  new MailreciverDaoImpl(); 
    }



@Bean
public MenuSysService menusysservice(){

return  new MenuSysServiceImpl(); 
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
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public AttendceDao attendcedao(){

return  new AttendceDaoImpl(); 
    }



@Bean
public DiscussDao discussdao(){

return  new DiscussDaoImpl(); 
    }



@Bean
public FileListdao filelistdao(){

return  new FileListdaoImpl(); 
    }



@Bean
public PlanDao plandao(){

return  new PlanDaoImpl(); 
    }



@Bean
public UserLogDao userlogdao(){

return  new UserLogDaoImpl(); 
    }



@Bean
public ProcessListDao processlistdao(){

return  new ProcessListDaoImpl(); 
    }



@Bean
public MailreciverDao mailreciverdao(){

return  new MailreciverDaoImpl(); 
    }



@Bean
public TaskuserDao taskuserdao(){

return  new TaskuserDaoImpl(); 
    }



@Bean
public RolepowerlistDao rolepowerlistdao(){

return  new RolepowerlistDaoImpl(); 
    }



@Bean
public DaymanageServices daymanageservices(){

return  new DaymanageServicesImpl(); 
    }



@Bean
public DaymanageDao daymanagedao(){

return  new DaymanageDaoImpl(); 
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
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



@Bean
public DeptDao deptdao(){

return  new DeptDaoImpl(); 
    }



}