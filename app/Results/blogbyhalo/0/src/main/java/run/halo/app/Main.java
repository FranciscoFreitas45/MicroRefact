package run.halo.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.PostRepository;
import run.halo.app.Interface.PostRepositoryImpl;
import run.halo.app.Interface.CommentBlackListService;
import run.halo.app.Interface.CommentBlackListServiceImpl;
import run.halo.app.Interface.PostService;
import run.halo.app.Interface.PostServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.MailService;
import run.halo.app.Interface.MailServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.PostService;
import run.halo.app.Interface.PostServiceImpl;
import run.halo.app.Interface.SheetService;
import run.halo.app.Interface.SheetServiceImpl;
import run.halo.app.Interface.UserService;
import run.halo.app.Interface.UserServiceImpl;
import run.halo.app.Interface.ThemeService;
import run.halo.app.Interface.ThemeServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.ThemeService;
import run.halo.app.Interface.ThemeServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.SheetService;
import run.halo.app.Interface.SheetServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.UserService;
import run.halo.app.Interface.UserServiceImpl;
import run.halo.app.Interface.SheetRepository;
import run.halo.app.Interface.SheetRepositoryImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.BasePostMinimalDTO;
import run.halo.app.Interface.BasePostMinimalDTOImpl;
import run.halo.app.Interface.BasePostMinimalDTO;
import run.halo.app.Interface.BasePostMinimalDTOImpl;
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
public PostRepository postrepository(){

return  new PostRepositoryImpl(); 
    }



@Bean
public CommentBlackListService commentblacklistservice(){

return  new CommentBlackListServiceImpl(); 
    }



@Bean
public PostService postservice(){

return  new PostServiceImpl(); 
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
public OptionService optionservice(){

return  new OptionServiceImpl(); 
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
public UserService userservice(){

return  new UserServiceImpl(); 
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
public OptionService optionservice(){

return  new OptionServiceImpl(); 
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
public SheetService sheetservice(){

return  new SheetServiceImpl(); 
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
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public SheetRepository sheetrepository(){

return  new SheetRepositoryImpl(); 
    }



@Bean
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public BasePostMinimalDTO basepostminimaldto(){

return  new BasePostMinimalDTOImpl(); 
    }



@Bean
public BasePostMinimalDTO basepostminimaldto(){

return  new BasePostMinimalDTOImpl(); 
    }



}