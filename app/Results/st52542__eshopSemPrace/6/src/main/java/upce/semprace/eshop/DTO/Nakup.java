package upce.semprace.eshop.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence;
import java.util.Date;
import java.util.Set;
import upce.semprace.eshop.Request.UzivatelRequest;
import upce.semprace.eshop.Request.Impl.UzivatelRequestImpl;
import upce.semprace.eshop.DTO.Uzivatel;
import upce.semprace.eshop.Request.DopravaRequest;
import upce.semprace.eshop.Request.Impl.DopravaRequestImpl;
import upce.semprace.eshop.DTO.Doprava;
import upce.semprace.eshop.Request.PlatbaRequest;
import upce.semprace.eshop.Request.Impl.PlatbaRequestImpl;
import upce.semprace.eshop.DTO.Platba;
public class Nakup {

 private  Long id;

 private  Date datumVytvoreni;

 private  Integer objednavka;

 private  Boolean stav;

 private  Uzivatel uzivatel;

 private  Doprava doprava;

 private  Platba platba;

 private  Set<NakoupenaPolozka> nakoupenaPolozka;

 private Long id;

 private Long id;

 private Long id;


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
  this.doprava = dopravarequest.getDoprava(this.id);
return this.doprava;
}


public Platba getPlatba(){
  this.platba = platbarequest.getPlatba(this.id);
return this.platba;
}


public Uzivatel getUzivatel(){
  this.uzivatel = uzivatelrequest.getUzivatel(this.id);
return this.uzivatel;
}


public Set<NakoupenaPolozka> getNakoupenaPolozka(){
    return nakoupenaPolozka;
}


}