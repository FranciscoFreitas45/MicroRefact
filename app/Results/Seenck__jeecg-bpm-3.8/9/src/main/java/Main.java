package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.JdbcDao;
import Interface.JdbcDaoImpl;
import Interface.SystemService;
import Interface.SystemServiceImpl;
import Interface.CgDynamGraphServiceI;
import Interface.CgDynamGraphServiceIImpl;
import Interface.GraphReportServiceI;
import Interface.GraphReportServiceIImpl;
import Interface.CommonExcelServiceI;
import Interface.CommonExcelServiceIImpl;
import Interface.ICommonDao;
import Interface.ICommonDaoImpl;
import Interface.CacheServiceI;
import Interface.CacheServiceIImpl;
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
public JdbcDao jdbcdao(){

return  new JdbcDaoImpl(); 
    }



@Bean
public SystemService systemservice(){

return  new SystemServiceImpl(); 
    }



@Bean
public CgDynamGraphServiceI cgdynamgraphservicei(){

return  new CgDynamGraphServiceIImpl(); 
    }



@Bean
public GraphReportServiceI graphreportservicei(){

return  new GraphReportServiceIImpl(); 
    }



@Bean
public CommonExcelServiceI commonexcelservicei(){

return  new CommonExcelServiceIImpl(); 
    }



@Bean
public ICommonDao icommondao(){

return  new ICommonDaoImpl(); 
    }



@Bean
public CacheServiceI cacheservicei(){

return  new CacheServiceIImpl(); 
    }



}