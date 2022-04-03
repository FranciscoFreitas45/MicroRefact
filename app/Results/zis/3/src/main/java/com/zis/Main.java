package com.zis;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.zis.Interface.BookAmountDao;
import com.zis.Interface.BookAmountDaoImpl;
import com.zis.Interface.DefaultBookMetadataCaptureHandler;
import com.zis.Interface.DefaultBookMetadataCaptureHandlerImpl;
import com.zis.Interface.BookAmountDao;
import com.zis.Interface.BookAmountDaoImpl;
import com.zis.Interface.BookMetadata;
import com.zis.Interface.BookMetadataImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.DoPurchaseServiceDWR;
import com.zis.Interface.DoPurchaseServiceDWRImpl;
import com.zis.Interface.YouLuNetDetailCapture;
import com.zis.Interface.YouLuNetDetailCaptureImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.StorageService;
import com.zis.Interface.StorageServiceImpl;
import com.zis.Interface.YouLuNetDetailCapture;
import com.zis.Interface.YouLuNetDetailCaptureImpl;
import com.zis.Interface.TempImportDetailDao;
import com.zis.Interface.TempImportDetailDaoImpl;
import com.zis.Interface.SimpleMailSender;
import com.zis.Interface.SimpleMailSenderImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.StorageService;
import com.zis.Interface.StorageServiceImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.DoPurchaseService;
import com.zis.Interface.DoPurchaseServiceImpl;
import com.zis.Interface.StorageService;
import com.zis.Interface.StorageServiceImpl;
import com.zis.Interface.TempImportDetailDao;
import com.zis.Interface.TempImportDetailDaoImpl;
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
public BookAmountDao bookamountdao(){

return  new BookAmountDaoImpl(); 
    }



@Bean
public DefaultBookMetadataCaptureHandler defaultbookmetadatacapturehandler(){

return  new DefaultBookMetadataCaptureHandlerImpl(); 
    }



@Bean
public BookAmountDao bookamountdao(){

return  new BookAmountDaoImpl(); 
    }



@Bean
public BookMetadata bookmetadata(){

return  new BookMetadataImpl(); 
    }



@Bean
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
    }



@Bean
public DoPurchaseServiceDWR dopurchaseservicedwr(){

return  new DoPurchaseServiceDWRImpl(); 
    }



@Bean
public YouLuNetDetailCapture youlunetdetailcapture(){

return  new YouLuNetDetailCaptureImpl(); 
    }



@Bean
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
    }



@Bean
public StorageService storageservice(){

return  new StorageServiceImpl(); 
    }



@Bean
public YouLuNetDetailCapture youlunetdetailcapture(){

return  new YouLuNetDetailCaptureImpl(); 
    }



@Bean
public TempImportDetailDao tempimportdetaildao(){

return  new TempImportDetailDaoImpl(); 
    }



@Bean
public SimpleMailSender simplemailsender(){

return  new SimpleMailSenderImpl(); 
    }



@Bean
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
    }



@Bean
public StorageService storageservice(){

return  new StorageServiceImpl(); 
    }



@Bean
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
    }



@Bean
public DoPurchaseService dopurchaseservice(){

return  new DoPurchaseServiceImpl(); 
    }



@Bean
public StorageService storageservice(){

return  new StorageServiceImpl(); 
    }



@Bean
public TempImportDetailDao tempimportdetaildao(){

return  new TempImportDetailDaoImpl(); 
    }



}