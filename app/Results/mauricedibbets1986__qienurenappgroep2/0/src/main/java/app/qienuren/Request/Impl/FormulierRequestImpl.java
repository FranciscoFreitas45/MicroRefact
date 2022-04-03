package app.qienuren.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import app.qienuren.DTO.Formulier;
import app.qienuren.Request.FormulierRequest;
public class FormulierRequestImpl implements FormulierRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<Formulier> getArchief(long id){
 List<Formulier> aux = restTemplate.getForObject("http://4/Medewerker/{id}/Formulier/getArchief",List<Formulier>.class,id);
return aux;
}


public List<Formulier> getTijdelijkeFormulieren(long id){
 List<Formulier> aux = restTemplate.getForObject("http://4/Medewerker/{id}/Formulier/getTijdelijkeFormulieren",List<Formulier>.class,id);
return aux;
}


public void verwijderFormulierUitTijdelijkeLijst(Formulier tf,long id){
 restTemplate.put("http://4/Medewerker/{id}/Formulier/verwijderFormulierUitTijdelijkeLijst",tf,id);
 return ;
}


public void voegFormulierToe(Formulier tf,long id){
 restTemplate.put("http://4/Medewerker/{id}/Formulier/voegFormulierToe",tf,id);
 return ;
}


public void voegFormulierToeAanArchief(Formulier f,long id){
 restTemplate.put("http://4/Medewerker/{id}/Formulier/voegFormulierToeAanArchief",f,id);
 return ;
}


public void koppelFormulier(Formulier formulierTijdelijk,long id){
 restTemplate.put("http://4/Medewerker/{id}/Formulier/koppelFormulier",formulierTijdelijk,id);
 return ;
}


public void setArchief(List<Formulier> archief,long id){
 restTemplate.put("http://4/Medewerker/{id}/Formulier/setArchief",archief,id);
 return ;
}


public void setTijdelijkeFormulieren(List<Formulier> tijdelijkeFormulieren,long id){
 restTemplate.put("http://4/Medewerker/{id}/Formulier/setTijdelijkeFormulieren",tijdelijkeFormulieren,id);
 return ;
}


}