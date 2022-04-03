package sn.api.response;
 import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class PersonResponseWithToken extends PersonResponse{

 private  String token;

public PersonResponseWithToken(PersonResponse personResponse, String token) {
    super(personResponse.getId(), personResponse.getFirstName(), personResponse.getLastName(), personResponse.getRegDate(), personResponse.getBirthDate(), personResponse.getEmail(), personResponse.getPhone(), personResponse.getPhoto(), personResponse.getAbout(), personResponse.getCity(), personResponse.getCountry(), personResponse.getMessagesPermission(), personResponse.getLastOnlineTime(), personResponse.isBlocked());
    this.token = token;
}
}