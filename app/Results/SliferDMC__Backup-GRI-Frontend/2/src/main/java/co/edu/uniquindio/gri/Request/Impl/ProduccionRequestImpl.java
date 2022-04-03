package co.edu.uniquindio.gri.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.DTO.Produccion;
import co.edu.uniquindio.gri.Request.ProduccionRequest;
public class ProduccionRequestImpl implements ProduccionRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setProducciones(List<Produccion> producciones,long id){
 restTemplate.put("http://1/Investigador/{id}/Produccion/setProducciones",producciones,id);
 return ;
}


public List<Produccion> getProducciones(long id){
 List<Produccion> aux = restTemplate.getForObject("http://1/Investigador/{id}/Produccion/getProducciones",List<Produccion>.class,id);
return aux;
}


}