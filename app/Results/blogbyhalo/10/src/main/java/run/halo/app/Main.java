package run.halo.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import run.halo.app.Interface.AbstractStringCacheStore;
import run.halo.app.Interface.AbstractStringCacheStoreImpl;
import run.halo.app.Interface.ThemeService;
import run.halo.app.Interface.ThemeServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.AbstractStringCacheStore;
import run.halo.app.Interface.AbstractStringCacheStoreImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.SheetCommentService;
import run.halo.app.Interface.SheetCommentServiceImpl;
import run.halo.app.Interface.ThemeService;
import run.halo.app.Interface.ThemeServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.PostRepository;
import run.halo.app.Interface.PostRepositoryImpl;
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
public AbstractStringCacheStore abstractstringcachestore(){

return  new AbstractStringCacheStoreImpl(); 
    }



@Bean
public ThemeService themeservice(){

return  new ThemeServiceImpl(); 
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
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public SheetCommentService sheetcommentservice(){

return  new SheetCommentServiceImpl(); 
    }



@Bean
public ThemeService themeservice(){

return  new ThemeServiceImpl(); 
    }



@Bean
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public PostRepository postrepository(){

return  new PostRepositoryImpl(); 
    }



}