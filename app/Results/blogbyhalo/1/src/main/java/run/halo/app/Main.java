package run.halo.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import run.halo.app.Interface.UserService;
import run.halo.app.Interface.UserServiceImpl;
import run.halo.app.Interface.CategoryService;
import run.halo.app.Interface.CategoryServiceImpl;
import run.halo.app.Interface.PostService;
import run.halo.app.Interface.PostServiceImpl;
import run.halo.app.Interface.SheetService;
import run.halo.app.Interface.SheetServiceImpl;
import run.halo.app.Interface.PostCommentService;
import run.halo.app.Interface.PostCommentServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
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
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public CategoryService categoryservice(){

return  new CategoryServiceImpl(); 
    }



@Bean
public PostService postservice(){

return  new PostServiceImpl(); 
    }



@Bean
public SheetService sheetservice(){

return  new SheetServiceImpl(); 
    }



@Bean
public PostCommentService postcommentservice(){

return  new PostCommentServiceImpl(); 
    }



@Bean
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



}