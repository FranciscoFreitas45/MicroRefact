package app.qienuren.DTO;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
public class KlantContactPersoon extends Persoon{

 private  MedewerkerType type;

 private  Bedrijf bedrijf;

 private  List<Trainee> trainees;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

public KlantContactPersoon() {
    this.setRoles("ROLE_KCP");
    this.setActive(true);
}
public Bedrijf getBedrijf(){
    return bedrijf;
}


public List<Trainee> getTrainees(){
    return trainees;
}


public void koppelTrainee(Trainee trainee){
traineerequest.koppelTrainee(trainee,this.id);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/koppelTrainee"))

.queryParam("trainee",trainee)
;
restTemplate.put(builder.toUriString(),null);
}


}