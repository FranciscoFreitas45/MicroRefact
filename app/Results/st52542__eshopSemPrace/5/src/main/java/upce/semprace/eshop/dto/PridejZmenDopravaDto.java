package upce.semprace.eshop.dto;
 public class PridejZmenDopravaDto {

 private  Long id;

 private  String popis;

 private  Integer cena;


public void setPopis(String popis){
    this.popis = popis;
}


public Integer getCena(){
    return cena;
}


public void setId(Long id){
    this.id = id;
}


public String getPopis(){
    return popis;
}


public Long getId(){
    return id;
}


public void setCena(Integer cena){
    this.cena = cena;
}


}