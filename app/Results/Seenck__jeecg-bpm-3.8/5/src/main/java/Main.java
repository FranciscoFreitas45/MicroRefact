package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.SystemService;
import Interface.SystemServiceImpl;
import Interface.CacheServiceI;
import Interface.CacheServiceIImpl;
import Interface.CgFormFieldServiceI;
import Interface.CgFormFieldServiceIImpl;
import Interface.ICommonDao;
import Interface.ICommonDaoImpl;
import Interface.DataGrid;
import Interface.DataGridImpl;
import Interface.JdbcDao;
import Interface.JdbcDaoImpl;
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
public SystemService systemservice(){

return  new SystemServiceImpl(); 
    }



@Bean
public CacheServiceI cacheservicei(){

return  new CacheServiceIImpl(); 
    }



@Bean
public CgFormFieldServiceI cgformfieldservicei(){

return  new CgFormFieldServiceIImpl(); 
    }



@Bean
public ICommonDao icommondao(){

return  new ICommonDaoImpl(); 
    }



@Bean
public DataGrid datagrid(){

return  new DataGridImpl(); 
    }



@Bean
public JdbcDao jdbcdao(){

return  new JdbcDaoImpl(); 
    }



}