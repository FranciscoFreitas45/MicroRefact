package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.UserService;
import Interface.UserServiceImpl;
import Interface.SystemService;
import Interface.SystemServiceImpl;
import Interface.MutiLangServiceI;
import Interface.MutiLangServiceIImpl;
import Interface.ClientManager;
import Interface.ClientManagerImpl;
import Interface.CacheServiceI;
import Interface.CacheServiceIImpl;
import Interface.LogService;
import Interface.LogServiceImpl;
import Interface.JeecgMinidaoDao;
import Interface.JeecgMinidaoDaoImpl;
import Interface.CgFormVersionDao;
import Interface.CgFormVersionDaoImpl;
import Interface.CgUploadServiceI;
import Interface.CgUploadServiceIImpl;
import Interface.TSDictTableConfigServiceI;
import Interface.TSDictTableConfigServiceIImpl;
import Interface.DataGrid;
import Interface.DataGridImpl;
import Interface.CgFormFieldServiceI;
import Interface.CgFormFieldServiceIImpl;
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
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public SystemService systemservice(){

return  new SystemServiceImpl(); 
    }



@Bean
public MutiLangServiceI mutilangservicei(){

return  new MutiLangServiceIImpl(); 
    }



@Bean
public ClientManager clientmanager(){

return  new ClientManagerImpl(); 
    }



@Bean
public CacheServiceI cacheservicei(){

return  new CacheServiceIImpl(); 
    }



@Bean
public LogService logservice(){

return  new LogServiceImpl(); 
    }



@Bean
public JeecgMinidaoDao jeecgminidaodao(){

return  new JeecgMinidaoDaoImpl(); 
    }



@Bean
public CgFormVersionDao cgformversiondao(){

return  new CgFormVersionDaoImpl(); 
    }



@Bean
public CgUploadServiceI cguploadservicei(){

return  new CgUploadServiceIImpl(); 
    }



@Bean
public TSDictTableConfigServiceI tsdicttableconfigservicei(){

return  new TSDictTableConfigServiceIImpl(); 
    }



@Bean
public DataGrid datagrid(){

return  new DataGridImpl(); 
    }



@Bean
public CgFormFieldServiceI cgformfieldservicei(){

return  new CgFormFieldServiceIImpl(); 
    }



}