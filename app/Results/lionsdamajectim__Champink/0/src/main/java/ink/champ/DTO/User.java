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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";

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


public Set<Player> getPlayers(){
    return players;
}


public Set<Role> getRoles(){
    return roles;
}


public Set<Champ> getChamps(){
    return champs;
}


public Set<Team> getTeams(){
    return teams;
}


public String getPassword(){
    return password;
}


public String getEmail(){
    return email;
}


public boolean isAdmin(){
    return getRoles().contains(Role.ADMIN);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ id).concat("/isAdmin"))

;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}