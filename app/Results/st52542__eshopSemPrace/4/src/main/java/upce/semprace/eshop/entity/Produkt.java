package upce.semprace.eshop.entity;
 import javax.persistence;
import upce.semprace.eshop.Request.NakoupenaPolozkaRequest;
import upce.semprace.eshop.Request.Impl.NakoupenaPolozkaRequestImpl;
import upce.semprace.eshop.DTO.NakoupenaPolozka;
@Entity
public class Produkt {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(nullable = false)
 private  String nazev;

@Column(nullable = false)
 private  String popis;

@Column(nullable = false)
 private  int cena;

@Column(nullable = false)
 private  int slevaProcenta;

@Column(nullable = false)
 private  boolean vNabidce;

@Column
 private  String cestaKObrazku;

@Transient
 private  NakoupenaPolozka nakoupenaPolozka;

@ManyToOne
 private  Vyrobce vyrobce;

@Column(name = "id")
 private Long id;

@Transient
 private NakoupenaPolozkaRequest nakoupenapolozkarequest = new NakoupenaPolozkaRequestImpl();;


public Long getId(){
    return id;
}


public boolean isvNabidce(){
    return vNabidce;
}


public int getSlevaProcenta(){
    return slevaProcenta;
}


public void setCestaKObrazku(String cestaKObrazku){
    this.cestaKObrazku = cestaKObrazku;
}


public Vyrobce getVyrobce(){
    return vyrobce;
}


public void setVyrobce(Vyrobce vyrobce){
    this.vyrobce = vyrobce;
}


public void setNazev(String nazev){
    this.nazev = nazev;
}


public void setPopis(String popis){
    this.popis = popis;
}


public int getCena(){
    return cena;
}


public void setSlevaProcenta(int slevaProcenta){
    this.slevaProcenta = slevaProcenta;
}


public void setNakoupenaPolozka(NakoupenaPolozka nakoupenaPolozka){
 nakoupenapolozkarequest.setNakoupenaPolozka(nakoupenaPolozka,this.id);
}



public void setId(Long id){
    this.id = id;
}


public String getPopis(){
    return popis;
}


public String getCestaKObrazku(){
    return cestaKObrazku;
}


public String getNazev(){
    return nazev;
}


public void setvNabidce(boolean vNabidce){
    this.vNabidce = vNabidce;
}


public NakoupenaPolozka getNakoupenaPolozka(){
  this.nakoupenaPolozka = nakoupenapolozkarequest.getNakoupenaPolozka(this.id);
return this.nakoupenaPolozka;
}


public void setCena(int cena){
    this.cena = cena;
}


}