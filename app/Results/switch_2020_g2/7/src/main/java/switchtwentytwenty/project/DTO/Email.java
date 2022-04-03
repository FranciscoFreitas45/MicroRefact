package switchtwentytwenty.project.DTO;
 import switchtwentytwenty.project.exception.InvalidEmailException;
import java.util.Objects;
import java.util.regex.Pattern;
public class Email implements ID{

 private  String emailAddress;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

// Constructor Methods
/**
 * Sole Constructor
 *
 * @param emailAddress - email address
 */
public Email(String emailAddress) throws InvalidEmailException {
    isValid(emailAddress);
    this.emailAddress = emailAddress.trim();
}
@Override
public boolean equals(Object object){
    if (this == object)
        return true;
    if (object == null || getClass() != object.getClass())
        return false;
    Email email = (Email) object;
    return Objects.equals(emailAddress, email.emailAddress);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))

.queryParam("object",object)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}