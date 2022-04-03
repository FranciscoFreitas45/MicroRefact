package switchtwentytwenty.project.DTO;
 import java.util.Objects;
import java.util.UUID;
public class LedgerID implements ID{

 private  UUID id;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

// Constructor Methods
/**
 * Sole constructor.
 *
 * @param id - identifier
 */
public LedgerID(UUID id) {
    Objects.requireNonNull(id);
    this.id = id;
}
@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    LedgerID ledgerID = (LedgerID) o;
    return Objects.equals(id, ledgerID.id);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))

.queryParam("o",o)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}