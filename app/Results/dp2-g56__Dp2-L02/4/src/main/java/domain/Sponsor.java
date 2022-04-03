package domain;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
@Entity
@Access(AccessType.PROPERTY)
public class Sponsor extends Actor{

 private  List<Sponsorship> sponsorships;

public Sponsor() {
}
public void setSponsorships(List<Sponsorship> sponsorships){
    this.sponsorships = sponsorships;
}


@OneToMany
public List<Sponsorship> getSponsorships(){
    return this.sponsorships;
}


}