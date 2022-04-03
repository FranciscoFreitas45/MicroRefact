package app.qienuren.model;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import app.qienuren.Request.KlantContactPersoonRequest;
import app.qienuren.Request.Impl.KlantContactPersoonRequestImpl;
import app.qienuren.DTO.KlantContactPersoon;
@Entity
public class Trainee extends Medewerker{

 private  MedewerkerType type;

@Transient
 private  KlantContactPersoon leidingGevende;

@Column(name = "id0KCA")
 private long id0KCA;

@Transient
 private KlantContactPersoonRequest klantcontactpersoonrequest = new KlantContactPersoonRequestImpl();;

public Trainee() {
    this.setRoles("ROLE_TRAINEE");
    this.setActive(true);
}
public void setLeidingGevende(KlantContactPersoon leidingGevende){
this.id0KCA = leidingGevende.getLeidinggevende() ;
klantcontactpersoonrequest.setLeidingGevende(leidingGevende,this.id0KCA);
 this.leidingGevende = leidingGevende;
}



public KlantContactPersoon getLeidingGevende(){
  this.leidingGevende = klantcontactpersoonrequest.getLeidingGevende(this.id0KCA);
return this.leidingGevende;
}}



public MedewerkerType getType(){
    return type;
}


public void koppelKlantContactPersoon(KlantContactPersoon kcp){
this.id0KCA = kcp.getKcp() ;
klantcontactpersoonrequest.koppelKlantContactPersoon(kcp,this.id0KCA);
}



public void setType(MedewerkerType type){
    this.type = type;
}


}