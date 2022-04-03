package run.halo.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.MailService;
import run.halo.app.Interface.MailServiceImpl;
import run.halo.app.Interface.AbstractStringCacheStore;
import run.halo.app.Interface.AbstractStringCacheStoreImpl;
import run.halo.app.Interface.HaloProperties;
import run.halo.app.Interface.HaloPropertiesImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.HaloProperties;
import run.halo.app.Interface.HaloPropertiesImpl;
import run.halo.app.Interface.HaloProperties;
import run.halo.app.Interface.HaloPropertiesImpl;
import run.halo.app.Interface.HaloProperties;
import run.halo.app.Interface.HaloPropertiesImpl;
import run.halo.app.Interface.HaloProperties;
import run.halo.app.Interface.HaloPropertiesImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.AbstractStringCacheStore;
import run.halo.app.Interface.AbstractStringCacheStoreImpl;
import run.halo.app.Interface.OneTimeTokenService;
import run.halo.app.Interface.OneTimeTokenServiceImpl;
import run.halo.app.Interface.AuthenticationFailureHandler;
import run.halo.app.Interface.AuthenticationFailureHandlerImpl;
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
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public MailService mailservice(){

return  new MailServiceImpl(); 
    }



@Bean
public AbstractStringCacheStore abstractstringcachestore(){

return  new AbstractStringCacheStoreImpl(); 
    }



@Bean
public HaloProperties haloproperties(){

return  new HaloPropertiesImpl(); 
    }



@Bean
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public HaloProperties haloproperties(){

return  new HaloPropertiesImpl(); 
    }



@Bean
public HaloProperties haloproperties(){

return  new HaloPropertiesImpl(); 
    }



@Bean
public HaloProperties haloproperties(){

return  new HaloPropertiesImpl(); 
    }



@Bean
public HaloProperties haloproperties(){

return  new HaloPropertiesImpl(); 
    }



@Bean
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public AbstractStringCacheStore abstractstringcachestore(){

return  new AbstractStringCacheStoreImpl(); 
    }



@Bean
public OneTimeTokenService onetimetokenservice(){

return  new OneTimeTokenServiceImpl(); 
    }



@Bean
public AuthenticationFailureHandler authenticationfailurehandler(){

return  new AuthenticationFailureHandlerImpl(); 
    }



}