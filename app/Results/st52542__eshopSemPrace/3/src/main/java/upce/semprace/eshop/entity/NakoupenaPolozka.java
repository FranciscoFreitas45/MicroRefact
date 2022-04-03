package upce.semprace.eshop.entity;
 import javax.persistence;
import upce.semprace.eshop.Request.ProduktRequest;
import upce.semprace.eshop.Request.Impl.ProduktRequestImpl;
import upce.semprace.eshop.DTO.Produkt;
@Entity
public class NakoupenaPolozka {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(nullable = false)
 private  Integer mnozstvi;

@ManyToOne
 private  Nakup nakup;

@Transient
 private  Produkt produkt;

@Column(name = "id")
 private Long id;

@Transient
 private ProduktRequest produktrequest = new ProduktRequestImpl();;


public Nakup getNakup(){
    return nakup;
}


public void setMnozstvi(Integer mnozstvi){
    this.mnozstvi = mnozstvi;
}


public void setNakup(Nakup nakup){
    this.nakup = nakup;
}


public Integer getMnozstvi(Integer value){
    return mnozstvi;
}


public void setId(Long id){
    this.id = id;
}


public void setProdukt(Produkt produkt){
 produktrequest.setProdukt(produkt,this.id);
}



public Long getId(){
    return id;
}


public Produkt getProdukt(){
  this.produkt = produktrequest.getProdukt(this.id);
return this.produkt;
}


}