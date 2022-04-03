package DTO;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;
public class DomainEntity {

 private  int id;

 private  int version;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

// Constructors -----------------------------------------------------------
public DomainEntity() {
    super();
}
@Version
public int getVersion(){
    return this.version;
}


@Id
@GeneratedValue(strategy = GenerationType.TABLE)
public int getId(){
    return this.id;
}


public void setId(int id){
    this.id = id;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setId"))

.queryParam("id",id)
;
restTemplate.put(builder.toUriString(),null);
}


public void setVersion(int version){
    this.version = version;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setVersion"))

.queryParam("version",version)
;
restTemplate.put(builder.toUriString(),null);
}


}