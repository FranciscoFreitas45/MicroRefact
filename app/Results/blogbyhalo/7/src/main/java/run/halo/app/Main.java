package run.halo.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import run.halo.app.Interface.PostService;
import run.halo.app.Interface.PostServiceImpl;
import run.halo.app.Interface.SheetService;
import run.halo.app.Interface.SheetServiceImpl;
import run.halo.app.Interface.JournalService;
import run.halo.app.Interface.JournalServiceImpl;
import run.halo.app.Interface.PostCommentService;
import run.halo.app.Interface.PostCommentServiceImpl;
import run.halo.app.Interface.SheetCommentService;
import run.halo.app.Interface.SheetCommentServiceImpl;
import run.halo.app.Interface.JournalCommentService;
import run.halo.app.Interface.JournalCommentServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.CategoryService;
import run.halo.app.Interface.CategoryServiceImpl;
import run.halo.app.Interface.TagService;
import run.halo.app.Interface.TagServiceImpl;
import run.halo.app.Interface.UserService;
import run.halo.app.Interface.UserServiceImpl;
import run.halo.app.Interface.UserService;
import run.halo.app.Interface.UserServiceImpl;
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
public PostService postservice(){

return  new PostServiceImpl(); 
    }



@Bean
public SheetService sheetservice(){

return  new SheetServiceImpl(); 
    }



@Bean
public JournalService journalservice(){

return  new JournalServiceImpl(); 
    }



@Bean
public PostCommentService postcommentservice(){

return  new PostCommentServiceImpl(); 
    }



@Bean
public SheetCommentService sheetcommentservice(){

return  new SheetCommentServiceImpl(); 
    }



@Bean
public JournalCommentService journalcommentservice(){

return  new JournalCommentServiceImpl(); 
    }



@Bean
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public CategoryService categoryservice(){

return  new CategoryServiceImpl(); 
    }



@Bean
public TagService tagservice(){

return  new TagServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



}