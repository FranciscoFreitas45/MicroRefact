package org.sdrc;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import org.sdrc.Interface.IndicatorRepository;
import org.sdrc.Interface.IndicatorRepositoryImpl;
import org.sdrc.Interface.UtTimeperiodRepository;
import org.sdrc.Interface.UtTimeperiodRepositoryImpl;
import org.sdrc.Interface.SectorRepository;
import org.sdrc.Interface.SectorRepositoryImpl;
import org.sdrc.Interface.SourceRepository;
import org.sdrc.Interface.SourceRepositoryImpl;
import org.sdrc.Interface.UtDataRepository;
import org.sdrc.Interface.UtDataRepositoryImpl;
import org.sdrc.Interface.CounterCountRepository;
import org.sdrc.Interface.CounterCountRepositoryImpl;
import org.sdrc.Interface.IndicatorRepository;
import org.sdrc.Interface.IndicatorRepositoryImpl;
import org.sdrc.Interface.UtSubgroupValsEnRepository;
import org.sdrc.Interface.UtSubgroupValsEnRepositoryImpl;
import org.sdrc.Interface.UtDataRepository;
import org.sdrc.Interface.UtDataRepositoryImpl;
import org.sdrc.Interface.UtTimeperiodRepository;
import org.sdrc.Interface.UtTimeperiodRepositoryImpl;
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
public IndicatorRepository indicatorrepository(){

return  new IndicatorRepositoryImpl(); 
    }



@Bean
public UtTimeperiodRepository uttimeperiodrepository(){

return  new UtTimeperiodRepositoryImpl(); 
    }



@Bean
public SectorRepository sectorrepository(){

return  new SectorRepositoryImpl(); 
    }



@Bean
public SourceRepository sourcerepository(){

return  new SourceRepositoryImpl(); 
    }



@Bean
public UtDataRepository utdatarepository(){

return  new UtDataRepositoryImpl(); 
    }



@Bean
public CounterCountRepository countercountrepository(){

return  new CounterCountRepositoryImpl(); 
    }



@Bean
public IndicatorRepository indicatorrepository(){

return  new IndicatorRepositoryImpl(); 
    }



@Bean
public UtSubgroupValsEnRepository utsubgroupvalsenrepository(){

return  new UtSubgroupValsEnRepositoryImpl(); 
    }



@Bean
public UtDataRepository utdatarepository(){

return  new UtDataRepositoryImpl(); 
    }



@Bean
public UtTimeperiodRepository uttimeperiodrepository(){

return  new UtTimeperiodRepositoryImpl(); 
    }



}