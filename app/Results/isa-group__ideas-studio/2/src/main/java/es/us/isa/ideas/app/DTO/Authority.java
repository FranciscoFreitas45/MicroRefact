package es.us.isa.ideas.app.DTO;
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

 public  String RESEARCHER;

 public  String REVIEWER;

 private  String authority;

public Authority() {
    super();
}
public Authority get(String authority){
    Authority result = null;
    if (ADMIN.equalsIgnoreCase(authority)) {
        result = new Authority();
        result.setAuthority(ADMIN);
    } else if (RESEARCHER.equalsIgnoreCase(authority)) {
        result = new Authority();
        result.setAuthority(RESEARCHER);
    } else if (REVIEWER.equalsIgnoreCase(authority)) {
        result = new Authority();
        result.setAuthority(REVIEWER);
    }
    return result;
}


@NotBlank
@Pattern(regexp = "^" + ADMIN + "|" + RESEARCHER + "|" + REVIEWER + "$")
@Override
public String getAuthority(){
    return authority;
}


}