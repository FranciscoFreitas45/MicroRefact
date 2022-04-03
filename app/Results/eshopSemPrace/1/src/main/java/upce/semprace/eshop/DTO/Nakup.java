package upce.semprace.eshop.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.Set;
import upce.semprace.eshop.DTO.Uzivatel;
import upce.semprace.eshop.DTO.Doprava;
import upce.semprace.eshop.DTO.Platba;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class Nakup {

 private  Long id;

 private  Date datumVytvoreni;

 private  Integer objednavka;

 private  Boolean stav;

 private  Uzivatel uzivatel;

 private  Doprava doprava;

 private  Platba platba;

 private  Set<NakoupenaPolozka> nakoupenaPolozka;



 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public Integer getObjednavka(){
    return objednavka;
}


public Boolean getStav(){
    return stav;
}


public Long getId(){
    return id;
}


public Date getDatumVytvoreni(){
    return datumVytvoreni;
}


public Doprava getDoprava(){
    return doprava;
}


public Platba getPlatba(){
    return platba;
}


public Uzivatel getUzivatel(){
    return uzivatel;
}


public Set<NakoupenaPolozka> getNakoupenaPolozka(){
    return nakoupenaPolozka;
}


public void setDoprava(Doprava doprava){
 this.doprava = doprava;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDoprava"))

.queryParam("doprava",doprava)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUzivatel(Uzivatel uzivatel){
 this.uzivatel = uzivatel;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setUzivatel"))

.queryParam("uzivatel",uzivatel)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPlatba(Platba platba){
 this.platba = platba;
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setPlatba"))

.queryParam("platba",platba)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDatumVytvoreni(Date datumVytvoreni){
    this.datumVytvoreni = datumVytvoreni;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setDatumVytvoreni"))

.queryParam("datumVytvoreni",datumVytvoreni)
;
restTemplate.put(builder.toUriString(),null);
}


public void setObjednavka(Integer objednavka){
    this.objednavka = objednavka;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setObjednavka"))

.queryParam("objednavka",objednavka)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStav(Boolean stav){
    this.stav = stav;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setStav"))

.queryParam("stav",stav)
;
restTemplate.put(builder.toUriString(),null);
}


}