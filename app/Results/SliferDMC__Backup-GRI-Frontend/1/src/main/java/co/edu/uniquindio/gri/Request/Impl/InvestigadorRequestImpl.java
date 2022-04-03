package co.edu.uniquindio.gri.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.DTO.Investigador;
import co.edu.uniquindio.gri.Request.InvestigadorRequest;
public class InvestigadorRequestImpl implements InvestigadorRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Investigador getInvestigador(long id){
 Investigador aux = restTemplate.getForObject("http://2/Produccion/{id}/Investigador/getInvestigador",Investigador.class,id);
return aux;
}


public void setInvestigador(Investigador investigador,long id){
 restTemplate.put("http://2/Produccion/{id}/Investigador/setInvestigador",investigador,id);
 return ;
}


}