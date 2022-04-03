package ink.champ.DTO;
 import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence;
import java.util.Collection;
import java.util.Set;
public class User implements UserDetails{

 private  Long id;

 private  String username;

 private  String password;

 private  String name;

 private  String email;

 private  boolean active;

 private  Set<Champ> champs;

 private  Set<Team> teams;

 private  Set<Player> players;

 private  Set<Role> roles;

/**
 * Конструктор пользователя
 */
public User() {
}/**
 * Конструктор пользователя
 * @param username Логин
 * @param password Пароль
 * @param name Имя
 * @param email Адрес электронной почты
 */
public User(String username, String password, String name, String email) {
    this.username = username;
    this.password = new BCryptPasswordEncoder().encode(password);
    this.name = name;
    this.email = email;
    this.active = true;
}
public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return name;
}


public Long getId(){
    return id;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return getRoles();
}


public String getUsername(){
    return username;
}


public void setId(Long id){
    this.id = id;
}


public Set<Player> getPlayers(){
    return players;
}


public Set<Role> getRoles(){
    return roles;
}


public void setUsername(String username){
    this.username = username;
}


public Set<Champ> getChamps(){
    return champs;
}


public boolean isAdmin(){
    return getRoles().contains(Role.ADMIN);
}


public Set<Team> getTeams(){
    return teams;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


@Override
public boolean isCredentialsNonExpired(){
    return true;
}


public String getEmail(){
    return email;
}


}