package org.live;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.live.Interface.AnchorService;
import org.live.Interface.AnchorServiceImpl;
import org.live.Interface.LiveRoomService;
import org.live.Interface.LiveRoomServiceImpl;
import org.live.Interface.LiveCategoryService;
import org.live.Interface.LiveCategoryServiceImpl;
import org.live.Interface.MemberService;
import org.live.Interface.MemberServiceImpl;
import org.live.Interface.ApplyAnchorService;
import org.live.Interface.ApplyAnchorServiceImpl;
import org.live.Interface.UploadFilePathConfig;
import org.live.Interface.UploadFilePathConfigImpl;
import org.live.Interface.AttentionRepository;
import org.live.Interface.AttentionRepositoryImpl;
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
public AnchorService anchorservice(){

return  new AnchorServiceImpl(); 
    }



@Bean
public LiveRoomService liveroomservice(){

return  new LiveRoomServiceImpl(); 
    }



@Bean
public LiveCategoryService livecategoryservice(){

return  new LiveCategoryServiceImpl(); 
    }



@Bean
public MemberService memberservice(){

return  new MemberServiceImpl(); 
    }



@Bean
public ApplyAnchorService applyanchorservice(){

return  new ApplyAnchorServiceImpl(); 
    }



@Bean
public UploadFilePathConfig uploadfilepathconfig(){

return  new UploadFilePathConfigImpl(); 
    }



@Bean
public AttentionRepository attentionrepository(){

return  new AttentionRepositoryImpl(); 
    }



}