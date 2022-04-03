package DTO;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import org.hibernate.validator.constraints.NotBlank;
public class Chapter extends Actor{

 private  String title;

 private  Area area;

 private  List<Proclaim> proclaims;


@NotBlank
public String getTitle(){
    return this.title;
}


@OneToMany
public List<Proclaim> getProclaims(){
    return this.proclaims;
}


@Valid
@OneToOne(optional = true)
public Area getArea(){
    return this.area;
}


}