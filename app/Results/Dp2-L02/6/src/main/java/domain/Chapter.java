package domain;
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
@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "area") })
public class Chapter extends Actor{

 private  String title;

 private  Area area;

 private  List<Proclaim> proclaims;


@NotBlank
public String getTitle(){
    return this.title;
}


public void setArea(Area area){
    this.area = area;
}


public void setProclaims(List<Proclaim> proclaims){
    this.proclaims = proclaims;
}


public void setTitle(String title){
    this.title = title;
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