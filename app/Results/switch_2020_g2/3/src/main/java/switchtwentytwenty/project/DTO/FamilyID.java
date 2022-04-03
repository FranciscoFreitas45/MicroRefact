package switchtwentytwenty.project.DTO;
 import java.util.Objects;
import java.util.UUID;
public class FamilyID implements ID{

 private  UUID id;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";

// Constructor Methods
/**
 * Sole constructor
 *
 * @param id - family id
 */
public FamilyID(UUID id) {
    Objects.requireNonNull(id);
    this.id = id;
}
@Override
public String toString(){
    return this.id.toString();
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toString"))

;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


@Override
public boolean equals(Object other){
    if (this == other)
        return true;
    if (other == null || getClass() != other.getClass())
        return false;
    FamilyID familyID = (FamilyID) other;
    return Objects.equals(id, familyID.id);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))

.queryParam("other",other)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}