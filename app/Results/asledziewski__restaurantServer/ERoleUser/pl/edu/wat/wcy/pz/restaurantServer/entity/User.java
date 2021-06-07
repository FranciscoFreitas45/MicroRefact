import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import javax.persistence;
import java.util.List;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class User {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "USER_ID")
 private  Long userId;

@NaturalId
@Column(name = "MAIL", length = 50)
 private  String mail;

@Column(name = "FIRST_NAME")
 private  String firstName;

@Column(name = "LAST_NAME")
 private  String lastName;

@Column(name = "PASSWORD")
 private  String password;

@ManyToMany(fetch = FetchType.EAGER)
@JoinTable(name = "USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
 private  Set<Role> roles;

@Transient
 private  List<Reservation> reservations;

@Transient
 private ReservationRequest reservationrequest = new ReservationRequestImpl();;


public void setPassword(String password){
    this.password = password;
}


public void setReservations(List<Reservation> reservations){
 reservationrequestimpl.setReservations(reservations,this.userId);



public String getLastName(){
    return lastName;
}


public String getMail(){
    return mail;
}


public void setLastName(String lastName){
    this.lastName = lastName;
}


public String getPassword(){
    return password;
}


public void setMail(String mail){
    this.mail = mail;
}


public void setFirstName(String firstName){
    this.firstName = firstName;
}


public List<Reservation> getReservations(){
  this.reservations = reservationrequestimpl.getReservations(this.userId);
return this.reservations;
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