package app.qienuren.DTO;
 import javax.persistence.Entity;
public class InterneMedewerker extends Medewerker{

 private  MedewerkerType type;

public InterneMedewerker() {
    this.setRoles("ROLE_INTERNEMEDEWERKER");
    this.setActive(true);
}
public MedewerkerType getType(){
    return type;
}


}