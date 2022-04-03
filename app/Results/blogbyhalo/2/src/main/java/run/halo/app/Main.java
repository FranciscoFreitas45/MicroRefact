package run.halo.app;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import run.halo.app.Interface.AttachmentService;
import run.halo.app.Interface.AttachmentServiceImpl;
import run.halo.app.Interface.CategoryService;
import run.halo.app.Interface.CategoryServiceImpl;
import run.halo.app.Interface.CommentBlackListService;
import run.halo.app.Interface.CommentBlackListServiceImpl;
import run.halo.app.Interface.JournalService;
import run.halo.app.Interface.JournalServiceImpl;
import run.halo.app.Interface.JournalCommentService;
import run.halo.app.Interface.JournalCommentServiceImpl;
import run.halo.app.Interface.LinkService;
import run.halo.app.Interface.LinkServiceImpl;
import run.halo.app.Interface.LogService;
import run.halo.app.Interface.LogServiceImpl;
import run.halo.app.Interface.MenuService;
import run.halo.app.Interface.MenuServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.PhotoService;
import run.halo.app.Interface.PhotoServiceImpl;
import run.halo.app.Interface.PostService;
import run.halo.app.Interface.PostServiceImpl;
import run.halo.app.Interface.PostCategoryService;
import run.halo.app.Interface.PostCategoryServiceImpl;
import run.halo.app.Interface.PostCommentService;
import run.halo.app.Interface.PostCommentServiceImpl;
import run.halo.app.Interface.PostMetaService;
import run.halo.app.Interface.PostMetaServiceImpl;
import run.halo.app.Interface.PostTagService;
import run.halo.app.Interface.PostTagServiceImpl;
import run.halo.app.Interface.SheetService;
import run.halo.app.Interface.SheetServiceImpl;
import run.halo.app.Interface.SheetCommentService;
import run.halo.app.Interface.SheetCommentServiceImpl;
import run.halo.app.Interface.SheetMetaService;
import run.halo.app.Interface.SheetMetaServiceImpl;
import run.halo.app.Interface.PostTagService;
import run.halo.app.Interface.PostTagServiceImpl;
import run.halo.app.Interface.ThemeSettingService;
import run.halo.app.Interface.ThemeSettingServiceImpl;
import run.halo.app.Interface.UserService;
import run.halo.app.Interface.UserServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.ThemeService;
import run.halo.app.Interface.ThemeServiceImpl;
import run.halo.app.Interface.ThemeSettingService;
import run.halo.app.Interface.ThemeSettingServiceImpl;
import run.halo.app.Interface.UserService;
import run.halo.app.Interface.UserServiceImpl;
import run.halo.app.Interface.OptionService;
import run.halo.app.Interface.OptionServiceImpl;
import run.halo.app.Interface.ThemeService;
import run.halo.app.Interface.ThemeServiceImpl;
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
public AttachmentService attachmentservice(){

return  new AttachmentServiceImpl(); 
    }



@Bean
public CategoryService categoryservice(){

return  new CategoryServiceImpl(); 
    }



@Bean
public CommentBlackListService commentblacklistservice(){

return  new CommentBlackListServiceImpl(); 
    }



@Bean
public JournalService journalservice(){

return  new JournalServiceImpl(); 
    }



@Bean
public JournalCommentService journalcommentservice(){

return  new JournalCommentServiceImpl(); 
    }



@Bean
public LinkService linkservice(){

return  new LinkServiceImpl(); 
    }



@Bean
public LogService logservice(){

return  new LogServiceImpl(); 
    }



@Bean
public MenuService menuservice(){

return  new MenuServiceImpl(); 
    }



@Bean
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public PhotoService photoservice(){

return  new PhotoServiceImpl(); 
    }



@Bean
public PostService postservice(){

return  new PostServiceImpl(); 
    }



@Bean
public PostCategoryService postcategoryservice(){

return  new PostCategoryServiceImpl(); 
    }



@Bean
public PostCommentService postcommentservice(){

return  new PostCommentServiceImpl(); 
    }



@Bean
public PostMetaService postmetaservice(){

return  new PostMetaServiceImpl(); 
    }



@Bean
public PostTagService posttagservice(){

return  new PostTagServiceImpl(); 
    }



@Bean
public SheetService sheetservice(){

return  new SheetServiceImpl(); 
    }



@Bean
public SheetCommentService sheetcommentservice(){

return  new SheetCommentServiceImpl(); 
    }



@Bean
public SheetMetaService sheetmetaservice(){

return  new SheetMetaServiceImpl(); 
    }



@Bean
public PostTagService posttagservice(){

return  new PostTagServiceImpl(); 
    }



@Bean
public ThemeSettingService themesettingservice(){

return  new ThemeSettingServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
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
public ThemeSettingService themesettingservice(){

return  new ThemeSettingServiceImpl(); 
    }



@Bean
public UserService userservice(){

return  new UserServiceImpl(); 
    }



@Bean
public OptionService optionservice(){

return  new OptionServiceImpl(); 
    }



@Bean
public ThemeService themeservice(){

return  new ThemeServiceImpl(); 
    }



}