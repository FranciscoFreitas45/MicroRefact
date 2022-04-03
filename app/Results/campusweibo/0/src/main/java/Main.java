
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.MyUserService;
import Interface.MyUserServiceImpl;
import Interface.LoginService;
import Interface.LoginServiceImpl;
import Interface.MyReplyService;
import Interface.MyReplyServiceImpl;
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
public MyUserService myuserservice(){

return  new MyUserServiceImpl() ;
    }



@Bean
public LoginService loginservice(){

return  new LoginServiceImpl() ;
    }



@Bean
public MyReplyService myreplyservice(){

return  new MyReplyServiceImpl() ;
    }



}
