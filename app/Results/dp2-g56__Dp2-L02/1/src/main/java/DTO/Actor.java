package DTO;
 import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import security.UserAccount;
public class Actor extends DomainEntity{

 private  String name;

 private  String middleName;

 private  String surname;

 private  String photo;

 private  String email;

 private  String phoneNumber;

 private  String address;

 private  Boolean hasSpam;

 private  double polarity;

 private  List<SocialProfile> socialProfiles;

 private  List<Box> boxes;

 private  UserAccount userAccount;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";


@NotBlank
public String getName(){
    return this.name;
}


@URL
public String getPhoto(){
    return this.photo;
}


@OneToOne(optional = false, cascade = CascadeType.ALL)
@Valid
public UserAccount getUserAccount(){
    return this.userAccount;
}


@Valid
public String getMiddleName(){
    return this.middleName;
}


@Min(-1)
@Max(1)
public double getPolarity(){
    return this.polarity;
}


@OneToMany(cascade = CascadeType.ALL)
@Valid
public List<SocialProfile> getSocialProfiles(){
    return this.socialProfiles;
}


@OneToMany(cascade = CascadeType.ALL)
@Valid
public List<Box> getBoxes(){
    return this.boxes;
}


@NotBlank
// @Email
@Pattern(regexp = "[\\w.%-]+\\@[-.\\w]+\\.[A-Za-z]{2,4}|[\\w.%-]+\\<+[\\w.%-]+\\@[-.\\w]+\\.[A-Za-z]{2,4}|[\\w.%-]+\\<[\\w.%-]+\\@+\\>|[\\w.%-]+", message = "Email doesn't follow the pattern, must be identifier@domain.asd or alias <identifier@domain.asd>")
@Column(unique = true)
public String getEmail(){
    return this.email;
}


@Valid
public String getPhoneNumber(){
    return this.phoneNumber;
}


@Valid
public String getAddress(){
    return this.address;
}


public Boolean getHasSpam(){
    return this.hasSpam;
}


@NotBlank
public String getSurname(){
    return this.surname;
}


public void setSocialProfiles(List<SocialProfile> socialProfiles){
    this.socialProfiles = socialProfiles;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSocialProfiles"))

.queryParam("socialProfiles",socialProfiles)
;
restTemplate.put(builder.toUriString(),null);
}


}