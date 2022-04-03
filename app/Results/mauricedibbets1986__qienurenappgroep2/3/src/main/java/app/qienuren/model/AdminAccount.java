package app.qienuren.model;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class AdminAccount extends Persoon{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

public AdminAccount() {
    this.setRoles("ROLE_ADMIN");
    this.setActive(true);
}
}