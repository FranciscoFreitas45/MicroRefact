package org.sdrc;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.sdrc.Interface.ValueObject;
import org.sdrc.Interface.ValueObjectImpl;
import org.sdrc.Interface.ValueObject;
import org.sdrc.Interface.ValueObjectImpl;
import org.sdrc.Interface.ValueObject;
import org.sdrc.Interface.ValueObjectImpl;
import org.sdrc.Interface.ValueObject;
import org.sdrc.Interface.ValueObjectImpl;
import org.sdrc.Interface.UtDataRepository;
import org.sdrc.Interface.UtDataRepositoryImpl;
import org.sdrc.Interface.UtAreaEnRepository;
import org.sdrc.Interface.UtAreaEnRepositoryImpl;
import org.sdrc.Interface.UtIndicatorClassificationsEnRepository;
import org.sdrc.Interface.UtIndicatorClassificationsEnRepositoryImpl;
import org.sdrc.Interface.UtIndicatorUnitSubgroupRepository;
import org.sdrc.Interface.UtIndicatorUnitSubgroupRepositoryImpl;
import org.sdrc.Interface.UtTimeperiodRepository;
import org.sdrc.Interface.UtTimeperiodRepositoryImpl;
import org.sdrc.Interface.TemplateUploadMetaRepository;
import org.sdrc.Interface.TemplateUploadMetaRepositoryImpl;
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
public ValueObject valueobject(){

return  new ValueObjectImpl(); 
    }



@Bean
public ValueObject valueobject(){

return  new ValueObjectImpl(); 
    }



@Bean
public ValueObject valueobject(){

return  new ValueObjectImpl(); 
    }



@Bean
public ValueObject valueobject(){

return  new ValueObjectImpl(); 
    }



@Bean
public UtDataRepository utdatarepository(){

return  new UtDataRepositoryImpl(); 
    }



@Bean
public UtAreaEnRepository utareaenrepository(){

return  new UtAreaEnRepositoryImpl(); 
    }



@Bean
public UtIndicatorClassificationsEnRepository utindicatorclassificationsenrepository(){

return  new UtIndicatorClassificationsEnRepositoryImpl(); 
    }



@Bean
public UtIndicatorUnitSubgroupRepository utindicatorunitsubgrouprepository(){

return  new UtIndicatorUnitSubgroupRepositoryImpl(); 
    }



@Bean
public UtTimeperiodRepository uttimeperiodrepository(){

return  new UtTimeperiodRepositoryImpl(); 
    }



@Bean
public TemplateUploadMetaRepository templateuploadmetarepository(){

return  new TemplateUploadMetaRepositoryImpl(); 
    }



}