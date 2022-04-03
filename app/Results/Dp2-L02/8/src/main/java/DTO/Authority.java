package DTO;
 import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
public class Authority implements GrantedAuthority{

 private  long serialVersionUID;

 public  String ADMIN;

 public  String BROTHERHOOD;

 public  String MEMBER;

 public  String CHAPTER;

 public  String SPONSOR;

 private  String authority;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";

public Authority() {
    super();
}
@NotBlank
@Pattern(regexp = "^" + Authority.ADMIN + "|" + Authority.BROTHERHOOD + "|" + Authority.CHAPTER + "|" + Authority.SPONSOR + "|" + Authority.MEMBER + "$")
@Override
public String getAuthority(){
    return this.authority;
}


@Override
public String toString(){
    return this.authority;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toString"))

;
String aux = restTemplate.getForObject(builder.toUriString(),String.class);
return aux;
}


@Override
public boolean equals(Object other){
    boolean result;
    if (this == other)
        result = true;
    else if (other == null)
        result = false;
    else if (!this.getClass().isInstance(other))
        result = false;
    else
        result = (this.getAuthority().equals(((Authority) other).getAuthority()));
    return result;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))

.queryParam("other",other)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}