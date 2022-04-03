package com.ushahidi.swiftriver.core;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.ushahidi.swiftriver.core.Interface.RiverDao;
import com.ushahidi.swiftriver.core.Interface.RiverDaoImpl;
import com.ushahidi.swiftriver.core.Interface.AccountDao;
import com.ushahidi.swiftriver.core.Interface.AccountDaoImpl;
import com.ushahidi.swiftriver.core.Interface.AccountService;
import com.ushahidi.swiftriver.core.Interface.AccountServiceImpl;
import com.ushahidi.swiftriver.core.Interface.RiverCollaboratorDao;
import com.ushahidi.swiftriver.core.Interface.RiverCollaboratorDaoImpl;
import com.ushahidi.swiftriver.core.Interface.RiverDropDao;
import com.ushahidi.swiftriver.core.Interface.RiverDropDaoImpl;
import com.ushahidi.swiftriver.core.Interface.RiverDropFormDao;
import com.ushahidi.swiftriver.core.Interface.RiverDropFormDaoImpl;
import com.ushahidi.swiftriver.core.Interface.RuleDao;
import com.ushahidi.swiftriver.core.Interface.RuleDaoImpl;
import com.ushahidi.swiftriver.core.Interface.TagDao;
import com.ushahidi.swiftriver.core.Interface.TagDaoImpl;
import com.ushahidi.swiftriver.core.Interface.LinkDao;
import com.ushahidi.swiftriver.core.Interface.LinkDaoImpl;
import com.ushahidi.swiftriver.core.Interface.PlaceDao;
import com.ushahidi.swiftriver.core.Interface.PlaceDaoImpl;
import com.ushahidi.swiftriver.core.Interface.DropDocumentRepository;
import com.ushahidi.swiftriver.core.Interface.DropDocumentRepositoryImpl;
import com.ushahidi.swiftriver.core.Interface.BucketDao;
import com.ushahidi.swiftriver.core.Interface.BucketDaoImpl;
import com.ushahidi.swiftriver.core.Interface.AccountDao;
import com.ushahidi.swiftriver.core.Interface.AccountDaoImpl;
import com.ushahidi.swiftriver.core.Interface.BucketDropDao;
import com.ushahidi.swiftriver.core.Interface.BucketDropDaoImpl;
import com.ushahidi.swiftriver.core.Interface.TagDao;
import com.ushahidi.swiftriver.core.Interface.TagDaoImpl;
import com.ushahidi.swiftriver.core.Interface.LinkDao;
import com.ushahidi.swiftriver.core.Interface.LinkDaoImpl;
import com.ushahidi.swiftriver.core.Interface.PlaceDao;
import com.ushahidi.swiftriver.core.Interface.PlaceDaoImpl;
import com.ushahidi.swiftriver.core.Interface.BucketCollaboratorDao;
import com.ushahidi.swiftriver.core.Interface.BucketCollaboratorDaoImpl;
import com.ushahidi.swiftriver.core.Interface.RiverDropDao;
import com.ushahidi.swiftriver.core.Interface.RiverDropDaoImpl;
import com.ushahidi.swiftriver.core.Interface.BucketDropFormDao;
import com.ushahidi.swiftriver.core.Interface.BucketDropFormDaoImpl;
import com.ushahidi.swiftriver.core.Interface.BucketCommentDao;
import com.ushahidi.swiftriver.core.Interface.BucketCommentDaoImpl;
import com.ushahidi.swiftriver.core.Interface.DropDocumentRepository;
import com.ushahidi.swiftriver.core.Interface.DropDocumentRepositoryImpl;
import com.ushahidi.swiftriver.core.Interface.AccountService;
import com.ushahidi.swiftriver.core.Interface.AccountServiceImpl;
import com.ushahidi.swiftriver.core.Interface.BucketDropDao;
import com.ushahidi.swiftriver.core.Interface.BucketDropDaoImpl;
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
public RiverDao riverdao(){

return  new RiverDaoImpl(); 
    }



@Bean
public AccountDao accountdao(){

return  new AccountDaoImpl(); 
    }



@Bean
public AccountService accountservice(){

return  new AccountServiceImpl(); 
    }



@Bean
public RiverCollaboratorDao rivercollaboratordao(){

return  new RiverCollaboratorDaoImpl(); 
    }



@Bean
public RiverDropDao riverdropdao(){

return  new RiverDropDaoImpl(); 
    }



@Bean
public RiverDropFormDao riverdropformdao(){

return  new RiverDropFormDaoImpl(); 
    }



@Bean
public RuleDao ruledao(){

return  new RuleDaoImpl(); 
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
public DropDocumentRepository dropdocumentrepository(){

return  new DropDocumentRepositoryImpl(); 
    }



@Bean
public BucketDao bucketdao(){

return  new BucketDaoImpl(); 
    }



@Bean
public AccountDao accountdao(){

return  new AccountDaoImpl(); 
    }



@Bean
public BucketDropDao bucketdropdao(){

return  new BucketDropDaoImpl(); 
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
public BucketCollaboratorDao bucketcollaboratordao(){

return  new BucketCollaboratorDaoImpl(); 
    }



@Bean
public RiverDropDao riverdropdao(){

return  new RiverDropDaoImpl(); 
    }



@Bean
public BucketDropFormDao bucketdropformdao(){

return  new BucketDropFormDaoImpl(); 
    }



@Bean
public BucketCommentDao bucketcommentdao(){

return  new BucketCommentDaoImpl(); 
    }



@Bean
public DropDocumentRepository dropdocumentrepository(){

return  new DropDocumentRepositoryImpl(); 
    }



@Bean
public AccountService accountservice(){

return  new AccountServiceImpl(); 
    }



@Bean
public BucketDropDao bucketdropdao(){

return  new BucketDropDaoImpl(); 
    }



}