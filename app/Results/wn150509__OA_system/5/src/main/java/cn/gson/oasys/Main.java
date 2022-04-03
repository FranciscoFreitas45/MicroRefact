package cn.gson.oasys;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.gson.oasys.Interface.FileServices;
import cn.gson.oasys.Interface.FileServicesImpl;
import cn.gson.oasys.Interface.NoteDao;
import cn.gson.oasys.Interface.NoteDaoImpl;
import cn.gson.oasys.Interface.TypeDao;
import cn.gson.oasys.Interface.TypeDaoImpl;
import cn.gson.oasys.Interface.StatusDao;
import cn.gson.oasys.Interface.StatusDaoImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
import cn.gson.oasys.Interface.AttachmentDao;
import cn.gson.oasys.Interface.AttachmentDaoImpl;
import cn.gson.oasys.Interface.ProcessService;
import cn.gson.oasys.Interface.ProcessServiceImpl;
import cn.gson.oasys.Interface.Attachment;
import cn.gson.oasys.Interface.AttachmentImpl;
import cn.gson.oasys.Interface.UserDao;
import cn.gson.oasys.Interface.UserDaoImpl;
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
public NoteDao notedao(){

return  new NoteDaoImpl(); 
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
public AttachmentDao attachmentdao(){

return  new AttachmentDaoImpl(); 
    }



@Bean
public ProcessService processservice(){

return  new ProcessServiceImpl(); 
    }



@Bean
public Attachment attachment(){

return  new AttachmentImpl(); 
    }



@Bean
public UserDao userdao(){

return  new UserDaoImpl(); 
    }



}