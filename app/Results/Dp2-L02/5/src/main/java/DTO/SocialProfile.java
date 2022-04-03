package DTO;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
public class SocialProfile extends DomainEntity{

 private  String nick;

 private  String name;

 private  String profileLink;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";

public SocialProfile() {
    // Created for Json purposes
    super();
}
@NotBlank
public String getName(){
    return this.name;
}


@NotBlank
@URL
public String getProfileLink(){
    return this.profileLink;
}


@NotBlank
public String getNick(){
    return this.nick;
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNick(String nick){
    this.nick = nick;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setNick"))

.queryParam("nick",nick)
;
restTemplate.put(builder.toUriString(),null);
}


public void setProfileLink(String profileLink){
    this.profileLink = profileLink;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setProfileLink"))

.queryParam("profileLink",profileLink)
;
restTemplate.put(builder.toUriString(),null);
}


}