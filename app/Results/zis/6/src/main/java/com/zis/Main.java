package com.zis;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.zis.Interface.TempImportBO;
import com.zis.Interface.TempImportBOImpl;
import com.zis.Interface.TempImportDetailDao;
import com.zis.Interface.TempImportDetailDaoImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.StorageService;
import com.zis.Interface.StorageServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.SysVarCache;
import com.zis.Interface.SysVarCacheImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.BookService;
import com.zis.Interface.BookServiceImpl;
import com.zis.Interface.StorageIoBatchDao;
import com.zis.Interface.StorageIoBatchDaoImpl;
import com.zis.Interface.StorageService;
import com.zis.Interface.StorageServiceImpl;
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
public TempImportBO tempimportbo(){

return  new TempImportBOImpl(); 
    }



@Bean
public TempImportDetailDao tempimportdetaildao(){

return  new TempImportDetailDaoImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public StorageService storageservice(){

return  new StorageServiceImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public SysVarCache sysvarcache(){

return  new SysVarCacheImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public BookService bookservice(){

return  new BookServiceImpl(); 
    }



@Bean
public StorageIoBatchDao storageiobatchdao(){

return  new StorageIoBatchDaoImpl(); 
    }



@Bean
public StorageService storageservice(){

return  new StorageServiceImpl(); 
    }



}