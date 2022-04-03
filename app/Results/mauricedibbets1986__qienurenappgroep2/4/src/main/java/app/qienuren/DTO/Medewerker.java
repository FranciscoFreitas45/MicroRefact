package app.qienuren.DTO;
 import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import app.qienuren.Request.FormulierRequest;
import app.qienuren.Request.Impl.FormulierRequestImpl;
import app.qienuren.DTO.Formulier;
public class Medewerker extends Persoon{

 private  String straatNaamNr;

 private  String postcode;

 private  String woonplaats;

 private  LocalDate startDatum;

 private  LocalDate eindDatum;

 private  List<Formulier> archief;

 private  List<Formulier> tijdelijkeFormulieren;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


public String getPostcode(){
    return postcode;
}


public List<Formulier> getArchief(){
    return archief;
}


public List<Formulier> getTijdelijkeFormulieren(){
    return tijdelijkeFormulieren;
}


public LocalDate getStartDatum(){
    return startDatum;
}


public String getWoonplaats(){
    return woonplaats;
}


public LocalDate getEindDatum(){
    return eindDatum;
}


public String getStraatNaamNr(){
    return straatNaamNr;
}


public void voegFormulierToeAanArchief(Formulier f){
formulierrequest.voegFormulierToeAanArchief(f,this.id);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/voegFormulierToeAanArchief"))

.queryParam("f",f)
;
restTemplate.put(builder.toUriString(),null);
}


public void verwijderFormulierUitTijdelijkeLijst(Formulier tf){
formulierrequest.verwijderFormulierUitTijdelijkeLijst(tf,this.id);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/verwijderFormulierUitTijdelijkeLijst"))

.queryParam("tf",tf)
;
restTemplate.put(builder.toUriString(),null);
}


}