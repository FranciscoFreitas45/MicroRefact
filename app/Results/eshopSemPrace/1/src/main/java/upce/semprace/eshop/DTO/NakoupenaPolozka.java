package upce.semprace.eshop.DTO;

import upce.semprace.eshop.DTO.Produkt;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
public class NakoupenaPolozka {

 private  Long id;

 private  Integer mnozstvi;

 private  Nakup nakup;

 private  Produkt produkt;



 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


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
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setProdukt"))

.queryParam("produkt",produkt)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMnozstvi(Integer mnozstvi){
    this.mnozstvi = mnozstvi;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setMnozstvi"))

.queryParam("mnozstvi",mnozstvi)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNakup(Nakup nakup){
    this.nakup = nakup;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/setNakup"))

.queryParam("nakup",nakup)
;
restTemplate.put(builder.toUriString(),null);
}


}