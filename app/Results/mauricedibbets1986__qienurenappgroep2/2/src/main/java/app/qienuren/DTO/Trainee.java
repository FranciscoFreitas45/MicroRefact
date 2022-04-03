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

public Trainee() {
    this.setRoles("ROLE_TRAINEE");
    this.setActive(true);
}
public KlantContactPersoon getLeidingGevende(){
    return leidingGevende;
}


public MedewerkerType getType(){
    return type;
}


public void setType(MedewerkerType type){
    this.type = type;
}


}