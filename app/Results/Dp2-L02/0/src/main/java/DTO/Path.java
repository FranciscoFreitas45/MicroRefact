package DTO;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
public class Path extends DomainEntity{

 private  List<Segment> segments;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


@Valid
@OneToMany(cascade = CascadeType.ALL)
public List<Segment> getSegments(){
    return this.segments;
}


public void setSegments(List<Segment> segments){
    this.segments = segments;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSegments"))

.queryParam("segments",segments)
;
restTemplate.put(builder.toUriString(),null);
}


}