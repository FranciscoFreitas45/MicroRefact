package app.qienuren.model;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import app.qienuren.Request.TraineeRequest;
import app.qienuren.Request.Impl.TraineeRequestImpl;
import app.qienuren.DTO.Trainee;
@Entity
public class KlantContactPersoon extends Persoon{

 private  MedewerkerType type;

@ManyToOne
 private  Bedrijf bedrijf;

@Transient
 private  List<Trainee> trainees;

@Transient
 private TraineeRequest traineerequest = new TraineeRequestImpl();;

public KlantContactPersoon() {
    this.setRoles("ROLE_KCP");
    this.setActive(true);
}
public Bedrijf getBedrijf(){
    return bedrijf;
}


public void setBedrijf(Bedrijf bedrijf){
    this.bedrijf = bedrijf;
}


public void koppelTrainee(Trainee trainee){
traineerequest.koppelTrainee(trainee,this.id);
}



public List<Trainee> getTrainees(){
  this.trainees = traineerequest.getTrainees(this.id);
return this.trainees;
}}



public void koppelBedrijf(Bedrijf bedrijf){
    this.bedrijf = bedrijf;
}


public void setTrainees(List<Trainee> trainees){
traineerequest.setTrainees(trainees,this.id);
 this.trainees = trainees;
}



}