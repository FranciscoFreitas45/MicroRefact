package upce.semprace.eshop.DTO;

import java.util.Set;
  import upce.semprace.eshop.entity.*;


public class Doprava {

 private  Long id;

 private  String popis;

 private  Integer cena;

 private  Set<Nakup> nakup;


public Set<Nakup> getNakup(){
    return nakup;
}


public Integer getCena(){
    return cena;
}


public String getPopis(){
    return popis;
}


public Long getId(){
    return id;
}


}