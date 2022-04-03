package ;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import Interface.OperationHistoryRequest;
import Interface.OperationHistoryRequestImpl;
import Interface.SmsHistoryRequest;
import Interface.SmsHistoryRequestImpl;
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
public OperationHistoryRequest operationhistoryrequest(){

return  new OperationHistoryRequestImpl(); 
    }



@Bean
public SmsHistoryRequest smshistoryrequest(){

return  new SmsHistoryRequestImpl(); 
    }



}