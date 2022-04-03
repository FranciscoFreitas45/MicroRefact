package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.SystemService;
import Interface.SystemServiceImpl;
import Interface.MutiLangServiceI;
import Interface.MutiLangServiceIImpl;
import Interface.CgFormIndexServiceI;
import Interface.CgFormIndexServiceIImpl;
import Interface.CgformButtonServiceI;
import Interface.CgformButtonServiceIImpl;
import Interface.CgformEnhanceJsServiceI;
import Interface.CgformEnhanceJsServiceIImpl;
import Interface.CgformFtlServiceI;
import Interface.CgformFtlServiceIImpl;
import Interface.CacheServiceI;
import Interface.CacheServiceIImpl;
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
public MutiLangServiceI mutilangservicei(){

return  new MutiLangServiceIImpl(); 
    }



@Bean
public CgFormIndexServiceI cgformindexservicei(){

return  new CgFormIndexServiceIImpl(); 
    }



@Bean
public CgformButtonServiceI cgformbuttonservicei(){

return  new CgformButtonServiceIImpl(); 
    }



@Bean
public CgformEnhanceJsServiceI cgformenhancejsservicei(){

return  new CgformEnhanceJsServiceIImpl(); 
    }



@Bean
public CgformFtlServiceI cgformftlservicei(){

return  new CgformFtlServiceIImpl(); 
    }



@Bean
public CacheServiceI cacheservicei(){

return  new CacheServiceIImpl(); 
    }



@Bean
public ICommonDao icommondao(){

return  new ICommonDaoImpl(); 
    }



}