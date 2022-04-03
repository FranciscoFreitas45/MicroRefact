package com.ushahidi.swiftriver.core;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.ushahidi.swiftriver.core.Interface.SequenceDao;
import com.ushahidi.swiftriver.core.Interface.SequenceDaoImpl;
import com.ushahidi.swiftriver.core.Interface.IdentityDao;
import com.ushahidi.swiftriver.core.Interface.IdentityDaoImpl;
import com.ushahidi.swiftriver.core.Interface.TagDao;
import com.ushahidi.swiftriver.core.Interface.TagDaoImpl;
import com.ushahidi.swiftriver.core.Interface.LinkDao;
import com.ushahidi.swiftriver.core.Interface.LinkDaoImpl;
import com.ushahidi.swiftriver.core.Interface.PlaceDao;
import com.ushahidi.swiftriver.core.Interface.PlaceDaoImpl;
import com.ushahidi.swiftriver.core.Interface.MediaDao;
import com.ushahidi.swiftriver.core.Interface.MediaDaoImpl;
import com.ushahidi.swiftriver.core.Interface.Form;
import com.ushahidi.swiftriver.core.Interface.FormImpl;
import com.ushahidi.swiftriver.core.Interface.AccountDao;
import com.ushahidi.swiftriver.core.Interface.AccountDaoImpl;
import com.ushahidi.swiftriver.core.Interface.LinkDao;
import com.ushahidi.swiftriver.core.Interface.LinkDaoImpl;
import com.ushahidi.swiftriver.core.Interface.PlaceDao;
import com.ushahidi.swiftriver.core.Interface.PlaceDaoImpl;
import com.ushahidi.swiftriver.core.Interface.TagDao;
import com.ushahidi.swiftriver.core.Interface.TagDaoImpl;
import com.ushahidi.swiftriver.core.Interface.DropDocumentRepository;
import com.ushahidi.swiftriver.core.Interface.DropDocumentRepositoryImpl;
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
public SequenceDao sequencedao(){

return  new SequenceDaoImpl(); 
    }



@Bean
public IdentityDao identitydao(){

return  new IdentityDaoImpl(); 
    }



@Bean
public TagDao tagdao(){

return  new TagDaoImpl(); 
    }



@Bean
public LinkDao linkdao(){

return  new LinkDaoImpl(); 
    }



@Bean
public PlaceDao placedao(){

return  new PlaceDaoImpl(); 
    }



@Bean
public MediaDao mediadao(){

return  new MediaDaoImpl(); 
    }



@Bean
public Form form(){

return  new FormImpl(); 
    }



@Bean
public AccountDao accountdao(){

return  new AccountDaoImpl(); 
    }



@Bean
public LinkDao linkdao(){

return  new LinkDaoImpl(); 
    }



@Bean
public PlaceDao placedao(){

return  new PlaceDaoImpl(); 
    }



@Bean
public TagDao tagdao(){

return  new TagDaoImpl(); 
    }



@Bean
public DropDocumentRepository dropdocumentrepository(){

return  new DropDocumentRepositoryImpl(); 
    }



}