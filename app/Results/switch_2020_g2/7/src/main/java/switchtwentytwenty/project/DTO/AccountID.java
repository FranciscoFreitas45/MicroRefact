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
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof AccountID))
        return false;
    AccountID that = (AccountID) o;
    return Objects.equals(id, that.id);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))

.queryParam("o",o)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
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