package kielce.tu.weaii.telelearn.DTO;
 import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType.ALL;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.InheritanceType.JOINED;
public class User implements Serializable,UserDetails{

 private  Long id;

 private  String username;

 private  char[] password;

 private  String email;

 private  String name;

 private  String surname;

 private  UserRole userRole;

 private  boolean enabled;

 private  List<Message> sendMessages;

 private  List<Message> receivedMessages;


@Override
public String getPassword(){
    return String.valueOf(password);
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return Collections.singletonList(new SimpleGrantedAuthority(userRole.toString()));
}


}