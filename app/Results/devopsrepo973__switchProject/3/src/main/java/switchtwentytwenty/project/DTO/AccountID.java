package switchtwentytwenty.project.DTO;
 import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;
public class AccountID implements Serializable,ID{

 private  UUID id;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

/**
 * Constructor methods
 *
 * @param id - id
 */
public AccountID(UUID id) {
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


}