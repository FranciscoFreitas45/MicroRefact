package domain;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
@Entity
@Access(AccessType.PROPERTY)
public class Path extends DomainEntity{

 private  List<Segment> segments;


public void setSegments(List<Segment> segments){
    this.segments = segments;
}


@Valid
@OneToMany(cascade = CascadeType.ALL)
public List<Segment> getSegments(){
    return this.segments;
}


}