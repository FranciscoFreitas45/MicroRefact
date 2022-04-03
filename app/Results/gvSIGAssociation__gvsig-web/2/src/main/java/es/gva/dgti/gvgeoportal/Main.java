package es.gva.dgti.gvgeoportal;
 import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import es.gva.dgti.gvgeoportal.Interface.SistemaCoordenadas;
import es.gva.dgti.gvgeoportal.Interface.SistemaCoordenadasImpl;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.CapasServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.CapasServicioWebServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.ConfVistasPredefinidasService;
import es.gva.dgti.gvgeoportal.Interface.ConfVistasPredefinidasServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.ConfVistasPredefinidasService;
import es.gva.dgti.gvgeoportal.Interface.ConfVistasPredefinidasServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.AgrupadorCapaService;
import es.gva.dgti.gvgeoportal.Interface.AgrupadorCapaServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.GeoportalServicioWebService;
import es.gva.dgti.gvgeoportal.Interface.GeoportalServicioWebServiceImpl;
import es.gva.dgti.gvgeoportal.Interface.ServicioWeb;
import es.gva.dgti.gvgeoportal.Interface.ServicioWebImpl;
import es.gva.dgti.gvgeoportal.Interface.AgrupadorCapa;
import es.gva.dgti.gvgeoportal.Interface.AgrupadorCapaImpl;
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
public SistemaCoordenadas sistemacoordenadas(){

return  new SistemaCoordenadasImpl(); 
    }



@Bean
public ServicioWebService serviciowebservice(){

return  new ServicioWebServiceImpl(); 
    }



@Bean
public CapasServicioWebService capasserviciowebservice(){

return  new CapasServicioWebServiceImpl(); 
    }



@Bean
public ConfVistasPredefinidasService confvistaspredefinidasservice(){

return  new ConfVistasPredefinidasServiceImpl(); 
    }



@Bean
public ConfVistasPredefinidasService confvistaspredefinidasservice(){

return  new ConfVistasPredefinidasServiceImpl(); 
    }



@Bean
public AgrupadorCapaService agrupadorcapaservice(){

return  new AgrupadorCapaServiceImpl(); 
    }



@Bean
public ServicioWebService serviciowebservice(){

return  new ServicioWebServiceImpl(); 
    }



@Bean
public GeoportalServicioWebService geoportalserviciowebservice(){

return  new GeoportalServicioWebServiceImpl(); 
    }



@Bean
public ServicioWeb servicioweb(){

return  new ServicioWebImpl(); 
    }



@Bean
public AgrupadorCapa agrupadorcapa(){

return  new AgrupadorCapaImpl(); 
    }



}