package app.qienuren.model;
 import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import app.qienuren.Request.FormulierRequest;
import app.qienuren.Request.Impl.FormulierRequestImpl;
import app.qienuren.DTO.Formulier;
@Entity
public class Medewerker extends Persoon{

 private  String straatNaamNr;

 private  String postcode;

 private  String woonplaats;

 private  LocalDate startDatum;

 private  LocalDate eindDatum;

@Transient
 private  List<Formulier> archief;

@Transient
 private  List<Formulier> tijdelijkeFormulieren;

@Transient
 private FormulierRequest formulierrequest = new FormulierRequestImpl();;


public String getPostcode(){
    return postcode;
}


public void setStraatNaamNr(String straatNaamNr){
    this.straatNaamNr = straatNaamNr;
}


public List<Formulier> getArchief(){
  this.tijdelijkeFormulieren = formulierrequest.getArchief(this.id);
return this.tijdelijkeFormulieren;
}}



public void setEindDatum(LocalDate eindDatum){
    this.eindDatum = eindDatum;
}


public List<Formulier> getTijdelijkeFormulieren(){
  this.tijdelijkeFormulieren = formulierrequest.getTijdelijkeFormulieren(this.id);
return this.tijdelijkeFormulieren;
}}



public void verwijderFormulierUitTijdelijkeLijst(Formulier tf){
formulierrequest.verwijderFormulierUitTijdelijkeLijst(tf,this.id);
}



public void voegFormulierToe(Formulier tf){
formulierrequest.voegFormulierToe(tf,this.id);
}



public LocalDate getStartDatum(){
    return startDatum;
}


public void setStartDatum(LocalDate startDatum){
    this.startDatum = startDatum;
}


public void voegFormulierToeAanArchief(Formulier f){
formulierrequest.voegFormulierToeAanArchief(f,this.id);
}



public void koppelFormulier(Formulier formulierTijdelijk){
formulierrequest.koppelFormulier(formulierTijdelijk,this.id);
}



public void setArchief(List<Formulier> archief){
formulierrequest.setArchief(archief,this.id);
 this.archief = archief;
}



public String getWoonplaats(){
    return woonplaats;
}


public void setWoonplaats(String woonplaats){
    this.woonplaats = woonplaats;
}


public void setPostcode(String postcode){
    this.postcode = postcode;
}


public void setTijdelijkeFormulieren(List<Formulier> tijdelijkeFormulieren){
formulierrequest.setTijdelijkeFormulieren(tijdelijkeFormulieren,this.id);
 this.tijdelijkeFormulieren = tijdelijkeFormulieren;
}



public LocalDate getEindDatum(){
    return eindDatum;
}


public String getStraatNaamNr(){
    return straatNaamNr;
}


}