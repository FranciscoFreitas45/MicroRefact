package domain;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "polarity") })
public class Member extends Actor{

 private  List<Enrolment> enrolments;

 private  Finder finder;

 private  List<Request> requests;


@Valid
@OneToMany(mappedBy = "member")
public List<Request> getRequests(){
    return this.requests;
}


@Valid
@OneToMany(mappedBy = "member")
public List<Enrolment> getEnrolments(){
    return this.enrolments;
}


public void setRequests(List<Request> requests){
    this.requests = requests;
}


public void setEnrolments(List<Enrolment> enrolments){
    this.enrolments = enrolments;
}


@Valid
@OneToOne(optional = true, cascade = CascadeType.ALL)
public Finder getFinder(){
    return this.finder;
}


public void setFinder(Finder finder){
    this.finder = finder;
}


}