package kielce.tu.weaii.telelearn.models;
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
@Entity
@Table(name = "USERS")
@Inheritance(strategy = JOINED)
@Data
public class User implements Serializable,UserDetails{

@Id
@GeneratedValue(strategy = IDENTITY)
 private  Long id;

 private  String username;

@Column(nullable = false, columnDefinition = "TEXT")
 private  char[] password;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String email;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String name;

@Column(nullable = false, columnDefinition = "TEXT")
 private  String surname;

@Column(nullable = false, columnDefinition = "TEXT")
 private  UserRole userRole;

@Column(nullable = false)
 private  boolean enabled;

@Transient
 private  List<Message> sendMessages;

@Transient
 private  List<Message> receivedMessages;


@Override
public String getPassword(){
    return String.valueOf(password);
}


@Override
public boolean isAccountNonExpired(){
    return true;
}


@Override
public boolean isCredentialsNonExpired(){
    return true;
}


@Override
public boolean isAccountNonLocked(){
    return true;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return Collections.singletonList(new SimpleGrantedAuthority(userRole.toString()));
}


}