package br.com.fatecmogidascruzes;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import br.com.fatecmogidascruzes.Interface.EmailService;
import br.com.fatecmogidascruzes.Interface.EmailServiceImpl;
import br.com.fatecmogidascruzes.Interface.ReCaptchaService;
import br.com.fatecmogidascruzes.Interface.ReCaptchaServiceImpl;
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
public EmailService emailservice(){

return  new EmailServiceImpl(); 
    }



@Bean
public ReCaptchaService recaptchaservice(){

return  new ReCaptchaServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



}