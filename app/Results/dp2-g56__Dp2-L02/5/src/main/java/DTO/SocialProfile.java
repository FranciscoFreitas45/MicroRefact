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


}