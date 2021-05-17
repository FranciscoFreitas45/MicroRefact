import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
public class PhAdmin implements UserDetails{

 private  long serialVersionUID;

 private  Long id;

 private  Date createdtime;

 private  String password;

 private  String username;

 private  String nickname;

 private  Set<String> urls;

 private  List<PhResource> resources;

 private  List<Long> brandIds;

 private  List<Long> roleIds;


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Temporal(TemporalType.TIMESTAMP)
@Column(name = "createdtime")
public Date getCreatedtime(){
    return createdtime;
}


@Transient
@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return null;
}


@Column(name = "username", length = 255)
public String getUsername(){
    return username;
}


@Transient
public List<Long> getBrandIds(){
    return brandIds;
}


@Column(name = "password", length = 255)
public String getPassword(){
    return password;
}


@Transient
public List<Long> getRoleIds(){
    return roleIds;
}


@Column(name = "nickname", length = 255)
public String getNickname(){
    return nickname;
}


@Transient
public List<PhResource> getResources(){
    return resources;
}


@Transient
public Set<String> getUrls(){
    return urls;
}


}