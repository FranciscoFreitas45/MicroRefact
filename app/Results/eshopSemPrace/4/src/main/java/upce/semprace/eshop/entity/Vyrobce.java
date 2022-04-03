package upce.semprace.eshop.entity;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Set;
@Entity
public class Vyrobce {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(nullable = false)
 private  String nazev;

@Column(nullable = false)
 private  String adresa;

@OneToMany(mappedBy = "id")
@JsonIgnore
 private  Set<Produkt> produkt;


public void setNazev(String nazev){
    this.nazev = nazev;
}


public void setId(Long id){
    this.id = id;
}


public String getAdresa(){
    return adresa;
}


public void setProdukt(Set<Produkt> produkt){
    this.produkt = produkt;
}


public Long getId(){
    return id;
}


public Set<Produkt> getProdukt(){
    return produkt;
}


public String getNazev(){
    return nazev;
}


public void setAdresa(String adresa){
    this.adresa = adresa;
}


}