package upce.semprace.eshop.dto;
 public class PridejZmenVyrobceDto {

 private  Long id;

 private  String nazev;

 private  String adresa;


public void setNazev(String nazev){
    this.nazev = nazev;
}


public void setId(Long id){
    this.id = id;
}


public String getAdresa(){
    return adresa;
}


public Long getId(){
    return id;
}


public String getNazev(){
    return nazev;
}


public void setAdresa(String adresa){
    this.adresa = adresa;
}


}