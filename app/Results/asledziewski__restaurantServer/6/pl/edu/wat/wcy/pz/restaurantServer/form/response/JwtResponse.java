import lombok.Getter;
import lombok.Setter;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Reservation;
import pl.edu.wat.wcy.pz.restaurantServer.entity.Role;
import pl.edu.wat.wcy.pz.restaurantServer.entity.User;
import java.util.List;
import java.util.Set;
@Getter
@Setter
public class JwtResponse {

 private  String jwtToken;

 private  String tokenType;

 private  Long userId;

 private  String mail;

 private  String firstName;

 private  String lastName;

 private  Set<Role> roles;

 private  List<Reservation> reservations;


public String getTokenType(){
    return tokenType;
}


public void setTokenType(String tokenType){
    this.tokenType = tokenType;
}


public void setReservations(List<Reservation> reservations){
    this.reservations = reservations;
}


public String getJwtToken(){
    return jwtToken;
}


public String getLastName(){
    return lastName;
}


public void setJwtToken(String jwtToken){
    this.jwtToken = jwtToken;
}


public String getMail(){
    return mail;
}


public void setLastName(String lastName){
    this.lastName = lastName;
}


public void setMail(String mail){
    this.mail = mail;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public List<Reservation> getReservations(){
    return reservations;
}


public String getFirstName(){
    return firstName;
}


public void setRoles(Set<Role> roles){
    this.roles = roles;
}


public Long getUserId(){
    return userId;
}


public void setUserId(Long userId){
    this.userId = userId;
}


public Set<Role> getRoles(){
    return roles;
}


}