package upce.semprace.eshop.dto;
 public class PridejZmenProduktDto {

 private  Long id;

 private  String nazev;

 private  String popis;

 private  int cena;

 private  int slevaProcenta;

 private  boolean vNabidce;

 private  String cestaKObrazku;

 private  Long vyrobce;


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


public Long getVyrobce(){
    return vyrobce;
}


public void setVyrobce(Long vyrobce){
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


public void setCena(int cena){
    this.cena = cena;
}


}