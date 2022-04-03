package upce.semprace.eshop.entity;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence;
import java.util.Set;
import upce.semprace.eshop.Request.NakupRequest;
import upce.semprace.eshop.Request.Impl.NakupRequestImpl;
import upce.semprace.eshop.DTO.Nakup;
@Entity
public class Platba {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@Column(length = 45)
 private  String popis;

@Column(nullable = false)
 private  Double prevod;

@Transient
 private  Set<Nakup> nakup;

@Transient
 private NakupRequest nakuprequest = new NakupRequestImpl();;


public Double getPrevod(){
    return prevod;
}


public void setPopis(String popis){
    this.popis = popis;
}


public Set<Nakup> getNakup(){
  this.nakup = nakuprequest.getNakup(this.id);
return this.nakup;
}


public void setNakup(Set<Nakup> nakup){
 nakuprequest.setNakup(nakup,this.id);
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


public void setPrevod(Double prevod){
    this.prevod = prevod;
}


}