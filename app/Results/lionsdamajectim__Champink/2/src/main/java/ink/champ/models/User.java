package ink.champ.models;
 import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence;
import java.util.Collection;
import java.util.Set;
@Entity(name = "users")
public class User implements UserDetails{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Column(nullable = false, unique = true)
 private  String username;

@Column(nullable = false)
 private  String password;

@Column(nullable = false)
 private  String name;

@Column(nullable = false, unique = true)
 private  String email;

 private  boolean active;

@OneToMany(targetEntity = Champ.class, mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
 private  Set<Champ> champs;

@OneToMany(mappedBy = "user")
 private  Set<Team> teams;

@OneToMany(targetEntity = Player.class, mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY)
 private  Set<Player> players;

@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
@Enumerated(EnumType.STRING)
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
public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return name;
}


public void setTeams(Set<Team> teams){
    this.teams = teams;
}


public Long getId(){
    return id;
}


public boolean isActive(){
    return active;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return getRoles();
}


public String getUsername(){
    return username;
}


@Override
public boolean isAccountNonExpired(){
    return true;
}


public void setId(Long id){
    this.id = id;
}


public void setRoles(Set<Role> roles){
    this.roles = roles;
}


public Set<Player> getPlayers(){
    return players;
}


public Set<Role> getRoles(){
    return roles;
}


public void setPlayers(Set<Player> players){
    this.players = players;
}


public void setUsername(String username){
    this.username = username;
}


public Set<Champ> getChamps(){
    return champs;
}


@Override
public boolean isAccountNonLocked(){
    return true;
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


public void setActive(boolean active){
    this.active = active;
}


public void setEmail(String email){
    this.email = email;
}


public void setChamps(Set<Champ> champs){
    this.champs = champs;
}


@Override
public boolean isCredentialsNonExpired(){
    return true;
}


@Override
public boolean isEnabled(){
    return isActive();
}


public String getEmail(){
    return email;
}


}