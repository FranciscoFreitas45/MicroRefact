package upce.semprace.eshop.DTO;
 import javax.persistence;
import upce.semprace.eshop.Request.ProduktRequest;
import upce.semprace.eshop.Request.Impl.ProduktRequestImpl;
import upce.semprace.eshop.DTO.Produkt;
public class NakoupenaPolozka {

 private  Long id;

 private  Integer mnozstvi;

 private  Nakup nakup;

 private  Produkt produkt;

 private Long id;


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
  this.produkt = produktrequest.getProdukt(this.id);
return this.produkt;
}


}