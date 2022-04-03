package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.SystemService;
import Interface.SystemServiceImpl;
import Interface.TempletContext;
import Interface.TempletContextImpl;
import Interface.CgFormFieldServiceI;
import Interface.CgFormFieldServiceIImpl;
import Interface.TempletContextWord;
import Interface.TempletContextWordImpl;
import Interface.DataBaseService;
import Interface.DataBaseServiceImpl;
import Interface.CgFormHeadEntity;
import Interface.CgFormHeadEntityImpl;
import Interface.CgFormFieldDao;
import Interface.CgFormFieldDaoImpl;
import Interface.ICommonDao;
import Interface.ICommonDaoImpl;
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
public TempletContext templetcontext(){

return  new TempletContextImpl(); 
    }



@Bean
public CgFormFieldServiceI cgformfieldservicei(){

return  new CgFormFieldServiceIImpl(); 
    }



@Bean
public TempletContextWord templetcontextword(){

return  new TempletContextWordImpl(); 
    }



@Bean
public DataBaseService databaseservice(){

return  new DataBaseServiceImpl(); 
    }



@Bean
public CgFormHeadEntity cgformheadentity(){

return  new CgFormHeadEntityImpl(); 
    }



@Bean
public CgFormFieldDao cgformfielddao(){

return  new CgFormFieldDaoImpl(); 
    }



@Bean
public ICommonDao icommondao(){

return  new ICommonDaoImpl(); 
    }



}