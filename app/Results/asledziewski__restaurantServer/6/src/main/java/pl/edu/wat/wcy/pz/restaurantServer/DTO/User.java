package pl.edu.wat.wcy.pz.restaurantServer.DTO;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import javax.persistence;
import java.util.List;
import java.util.Set;
import pl.edu.wat.wcy.pz.restaurantServer.Request.ReservationRequest;
import pl.edu.wat.wcy.pz.restaurantServer.Request.Impl.ReservationRequestImpl;
import pl.edu.wat.wcy.pz.restaurantServer.DTO.Reservation;
public class User {

 private  Long userId;

 private  String mail;

 private  String firstName;

 private  String lastName;

 private  String password;

 private  Set<Role> roles;

 private  List<Reservation> reservations;

public User(String mail, String firstName, String lastName, String password, Set<Role> roles) {
    this.mail = mail;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.roles = roles;
}
public void setReservations(List<Reservation> reservations){
    this.reservations = reservations;
}


public String getLastName(){
    return lastName;
}


public String getMail(){
    return mail;
}


public String getPassword(){
    return password;
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


public Long getUserId(){
    return userId;
}


public Set<Role> getRoles(){
    return roles;
}


}