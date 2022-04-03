package lk.sliit.csse.procurementsystem;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import lk.sliit.csse.procurementsystem.Interface.Supplier;
import lk.sliit.csse.procurementsystem.Interface.SupplierImpl;
import lk.sliit.csse.procurementsystem.Interface.BlacklistedSupplier;
import lk.sliit.csse.procurementsystem.Interface.BlacklistedSupplierImpl;
import lk.sliit.csse.procurementsystem.Interface.SupplierRepository;
import lk.sliit.csse.procurementsystem.Interface.SupplierRepositoryImpl;
import lk.sliit.csse.procurementsystem.Interface.BlacklistedSupplierRepository;
import lk.sliit.csse.procurementsystem.Interface.BlacklistedSupplierRepositoryImpl;
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
public Supplier supplier(){

return  new SupplierImpl(); 
    }



@Bean
public BlacklistedSupplier blacklistedsupplier(){

return  new BlacklistedSupplierImpl(); 
    }



@Bean
public SupplierRepository supplierrepository(){

return  new SupplierRepositoryImpl(); 
    }



@Bean
public BlacklistedSupplierRepository blacklistedsupplierrepository(){

return  new BlacklistedSupplierRepositoryImpl(); 
    }



}