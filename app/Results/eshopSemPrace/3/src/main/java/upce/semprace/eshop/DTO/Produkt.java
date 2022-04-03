package upce.semprace.eshop.DTO;
   import upce.semprace.eshop.entity.*;

public class Produkt {

 private  Long id;

 private  String nazev;

 private  String popis;

 private  int cena;

 private  int slevaProcenta;

 private  boolean vNabidce;

 private  String cestaKObrazku;

 private  NakoupenaPolozka nakoupenaPolozka;

 private  Vyrobce vyrobce;


public Long getId(){
    return id;
}


public int getSlevaProcenta(){
    return slevaProcenta;
}


public Vyrobce getVyrobce(){
    return vyrobce;
}


public int getCena(){
    return cena;
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


public NakoupenaPolozka getNakoupenaPolozka(){
    return nakoupenaPolozka;
}


}