package upce.semprace.eshop.DTO;

 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class NakoupenaPolozka {

 private  Long id;

 private  Integer mnozstvi;

 private  Nakup nakup;

 private  Produkt produkt;

public Nakup getNakup(){
    return nakup;
}


public Integer getMnozstvi(Integer value){
    return mnozstvi;
}


public Long getId(){
    return id;
}


public Produkt getProdukt(){
    return produkt;
}


public void setProdukt(Produkt produkt){
 this.produkt = produkt;

}


public void setMnozstvi(Integer mnozstvi){
    this.mnozstvi = mnozstvi;

}


public void setNakup(Nakup nakup){
    this.nakup = nakup;

}


}