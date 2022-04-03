package co.edu.uniquindio.gri.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uniquindio.gri.DTO.Programa;
import co.edu.uniquindio.gri.Request.ProgramaRequest;
public class ProgramaRequestImpl implements ProgramaRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setPrograma(List<Programa> programa,long id){
 restTemplate.put("http://3/Facultad/{id}/Programa/setPrograma",programa,id);
 return ;
}


public List<Programa> getPrograma(long id){
 List<Programa> aux = restTemplate.getForObject("http://3/Facultad/{id}/Programa/getPrograma",List<Programa>.class,id);
return aux;
}


}