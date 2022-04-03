package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.AssetsService;
import Interface.AssetsServiceImpl;
import Interface.AccountService;
import Interface.AccountServiceImpl;
import Interface.WorkflowDeployService;
import Interface.WorkflowDeployServiceImpl;
import Interface.ClientService;
import Interface.ClientServiceImpl;
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
public AssetsService assetsservice(){

return  new AssetsServiceImpl(); 
    }



@Bean
public AccountService accountservice(){

return  new AccountServiceImpl(); 
    }



@Bean
public WorkflowDeployService workflowdeployservice(){

return  new WorkflowDeployServiceImpl(); 
    }



@Bean
public ClientService clientservice(){

return  new ClientServiceImpl(); 
    }



}