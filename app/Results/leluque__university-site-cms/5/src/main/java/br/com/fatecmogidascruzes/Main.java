package br.com.fatecmogidascruzes;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import br.com.fatecmogidascruzes.Interface.EventService;
import br.com.fatecmogidascruzes.Interface.EventServiceImpl;
import br.com.fatecmogidascruzes.Interface.FileWebService;
import br.com.fatecmogidascruzes.Interface.FileWebServiceImpl;
import br.com.fatecmogidascruzes.Interface.EventService;
import br.com.fatecmogidascruzes.Interface.EventServiceImpl;
import br.com.fatecmogidascruzes.Interface.FileService;
import br.com.fatecmogidascruzes.Interface.FileServiceImpl;
import br.com.fatecmogidascruzes.Interface.AlbumDAO;
import br.com.fatecmogidascruzes.Interface.AlbumDAOImpl;
import br.com.fatecmogidascruzes.Interface.UserService;
import br.com.fatecmogidascruzes.Interface.UserServiceImpl;
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
public EventService eventservice(){

return  new EventServiceImpl(); 
    }



@Bean
public FileWebService filewebservice(){

return  new FileWebServiceImpl(); 
    }



@Bean
public EventService eventservice(){

return  new EventServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



@Bean
public AlbumDAO albumdao(){

return  new AlbumDAOImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



}