package upce.semprace.eshop.entity;
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
@Entity
public class Nakup {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(nullable = false)
 private  Date datumVytvoreni;

@Column(nullable = false)
 private  Integer objednavka;

@Column(nullable = false)
 private  Boolean stav;

@Transient
 private  Uzivatel uzivatel;

@Transient
 private  Doprava doprava;

@Transient
 private  Platba platba;

@OneToMany(mappedBy = "id")
@JsonIgnore
 private  Set<NakoupenaPolozka> nakoupenaPolozka;

@Column(name = "id")
 private Long id;

@Transient
 private UzivatelRequest uzivatelrequest = new UzivatelRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private DopravaRequest dopravarequest = new DopravaRequestImpl();;

@Column(name = "id")
 private Long id;

@Transient
 private PlatbaRequest platbarequest = new PlatbaRequestImpl();;


public void setDatumVytvoreni(Date datumVytvoreni){
    this.datumVytvoreni = datumVytvoreni;
}


public void setUzivatel(Uzivatel uzivatel){
 uzivatelrequest.setUzivatel(uzivatel,this.id);
}



public Integer getObjednavka(){
    return objednavka;
}


public void setObjednavka(Integer objednavka){
    this.objednavka = objednavka;
}


public void setStav(Boolean stav){
    this.stav = stav;
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


public void setDoprava(Doprava doprava){
 dopravarequest.setDoprava(doprava,this.id);
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


public void setNakoupenaPolozka(Set<NakoupenaPolozka> nakoupenaPolozka){
    this.nakoupenaPolozka = nakoupenaPolozka;
}


public void setId(Long id){
    this.id = id;
}


public void setPlatba(Platba platba){
 platbarequest.setPlatba(platba,this.id);
}



public Set<NakoupenaPolozka> getNakoupenaPolozka(){
    return nakoupenaPolozka;
}


}