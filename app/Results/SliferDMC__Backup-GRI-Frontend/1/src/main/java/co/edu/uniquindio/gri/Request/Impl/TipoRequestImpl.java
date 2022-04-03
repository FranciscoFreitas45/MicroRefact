package co.edu.uniquindio.gri.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.DTO.Tipo;
import co.edu.uniquindio.gri.Request.TipoRequest;
public class TipoRequestImpl implements TipoRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setTipo(Tipo tipo,long id){
 restTemplate.put("http://2/Produccion/{id}/Tipo/setTipo",tipo,id);
 return ;
}


public Tipo getTipo(long id){
 Tipo aux = restTemplate.getForObject("http://2/Produccion/{id}/Tipo/getTipo",Tipo.class,id);
return aux;
}


}