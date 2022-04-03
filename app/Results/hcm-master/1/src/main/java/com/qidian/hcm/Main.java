package com.qidian.hcm;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import com.qidian.hcm.Interface.TenantDataSourceProvider;
import com.qidian.hcm.Interface.TenantDataSourceProviderImpl;
import com.qidian.hcm.Interface.OrganizationRepository;
import com.qidian.hcm.Interface.OrganizationRepositoryImpl;
import com.qidian.hcm.Interface.OrganizationService;
import com.qidian.hcm.Interface.OrganizationServiceImpl;
import com.qidian.hcm.Interface.CustomizedFieldService;
import com.qidian.hcm.Interface.CustomizedFieldServiceImpl;
import com.qidian.hcm.Interface.FileService;
import com.qidian.hcm.Interface.FileServiceImpl;
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
public TenantDataSourceProvider tenantdatasourceprovider(){

return  new TenantDataSourceProviderImpl(); 
    }



@Bean
public OrganizationRepository organizationrepository(){

return  new OrganizationRepositoryImpl(); 
    }



@Bean
public OrganizationService organizationservice(){

return  new OrganizationServiceImpl(); 
    }



@Bean
public CustomizedFieldService customizedfieldservice(){

return  new CustomizedFieldServiceImpl(); 
    }



@Bean
public FileService fileservice(){

return  new FileServiceImpl(); 
    }



}