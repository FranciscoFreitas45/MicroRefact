package cn.maxcj;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import cn.maxcj.Interface.GunsProperties;
import cn.maxcj.Interface.GunsPropertiesImpl;
import cn.maxcj.Interface.INoticeService;
import cn.maxcj.Interface.INoticeServiceImpl;
import cn.maxcj.Interface.MenuMapper;
import cn.maxcj.Interface.MenuMapperImpl;
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
public GunsProperties gunsproperties(){

return  new GunsPropertiesImpl(); 
    }



@Bean
public INoticeService inoticeservice(){

return  new INoticeServiceImpl(); 
    }



@Bean
public MenuMapper menumapper(){

return  new MenuMapperImpl(); 
    }



}