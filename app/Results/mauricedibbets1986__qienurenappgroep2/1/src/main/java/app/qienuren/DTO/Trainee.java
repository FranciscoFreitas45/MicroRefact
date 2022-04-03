package app.qienuren.DTO;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import app.qienuren.Request.KlantContactPersoonRequest;
import app.qienuren.Request.Impl.KlantContactPersoonRequestImpl;
import app.qienuren.DTO.KlantContactPersoon;
public class Trainee extends Medewerker{

 private  MedewerkerType type;

 private  KlantContactPersoon leidingGevende;

 private long id0KCA;

public Trainee() {
    this.setRoles("ROLE_TRAINEE");
    this.setActive(true);
}
public KlantContactPersoon getLeidingGevende(){
  this.leidingGevende = klantcontactpersoonrequest.getLeidingGevende(this.id0KCA);
return this.leidingGevende;
}}



public MedewerkerType getType(){
    return type;
}


}