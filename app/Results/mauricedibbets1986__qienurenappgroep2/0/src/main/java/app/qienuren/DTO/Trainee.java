package app.qienuren.DTO;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
public class Trainee extends Medewerker{

 private  MedewerkerType type;

 private  KlantContactPersoon leidingGevende;

 private long id0KCA;

public Trainee() {
    this.setRoles("ROLE_TRAINEE");
    this.setActive(true);
}
public void setLeidingGevende(KlantContactPersoon leidingGevende){
    this.leidingGevende = leidingGevende;
}


public KlantContactPersoon getLeidingGevende(){
    return leidingGevende;
}


public MedewerkerType getType(){
    return type;
}


public void koppelKlantContactPersoon(KlantContactPersoon kcp){
    this.leidingGevende = kcp;
}


public void setType(MedewerkerType type){
    this.type = type;
}


}