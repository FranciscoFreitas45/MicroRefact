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
@Entity
@Table(name = "ph_admin")
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


public void setPassword(String password){
    this.password = password;
}


public void setUsername(String username){
    this.username = username;
}


public void setCreatedtime(Date createdtime){
    this.createdtime = createdtime;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Long getId(){
    return id;
}


@Transient
@Override
public boolean isAccountNonLocked(){
    return true;
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


public void setResources(List<PhResource> resources){
    this.resources = resources;
}


@Column(name = "username", length = 255)
public String getUsername(){
    return username;
}


public void setRoleIds(List<Long> roleIds){
    this.roleIds = roleIds;
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
@Override
public boolean isAccountNonExpired(){
    return true;
}


@Transient
@Override
public boolean isCredentialsNonExpired(){
    return true;
}


@Transient
public List<PhResource> getResources(){
    return resources;
}


@Transient
@Override
public boolean isEnabled(){
    return true;
}


public void setUrls(Set<String> urls){
    this.urls = urls;
}


public void setId(Long id){
    this.id = id;
}


public void setNickname(String nickname){
    this.nickname = nickname;
}


@Transient
public Set<String> getUrls(){
    return urls;
}


public void setBrandIds(List<Long> brandIds){
    this.brandIds = brandIds;
}


}