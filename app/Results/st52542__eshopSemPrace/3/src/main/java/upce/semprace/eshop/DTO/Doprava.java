package upce.semprace.eshop.DTO;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence;
import java.util.Set;
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