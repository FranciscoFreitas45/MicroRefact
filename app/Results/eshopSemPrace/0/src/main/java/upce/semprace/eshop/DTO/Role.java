package upce.semprace.eshop.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.NaturalId;
public class Role {

 private  Long id;

 private  RoleName name;

public Role() {
}public Role(RoleName name) {
    this.name = name;
}
public RoleName getName(){
    return name;
}


public Long getId(){
    return id;
}


}