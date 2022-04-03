package net.shangtech;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import net.shangtech.Interface.SubProjectDao;
import net.shangtech.Interface.SubProjectDaoImpl;
import net.shangtech.Interface.ProjectTypeDao;
import net.shangtech.Interface.ProjectTypeDaoImpl;
import net.shangtech.Interface.ProjectImageDao;
import net.shangtech.Interface.ProjectImageDaoImpl;
import net.shangtech.Interface.PanoramaDao;
import net.shangtech.Interface.PanoramaDaoImpl;
import net.shangtech.Interface.HousePanoramaDao;
import net.shangtech.Interface.HousePanoramaDaoImpl;
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
public SubProjectDao subprojectdao(){

return  new SubProjectDaoImpl(); 
    }



@Bean
public ProjectTypeDao projecttypedao(){

return  new ProjectTypeDaoImpl(); 
    }



@Bean
public ProjectImageDao projectimagedao(){

return  new ProjectImageDaoImpl(); 
    }



@Bean
public PanoramaDao panoramadao(){

return  new PanoramaDaoImpl(); 
    }



@Bean
public HousePanoramaDao housepanoramadao(){

return  new HousePanoramaDaoImpl(); 
    }



}