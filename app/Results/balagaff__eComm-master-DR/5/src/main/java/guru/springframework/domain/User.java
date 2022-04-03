package guru.springframework.domain;
 import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
@Entity
@Table(name = "Gate_user_details")
public class User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "user_id")
 private  int id;

@Column(name = "email")
@Email(message = "*Please provide a valid Email")
@NotEmpty(message = "*Please provide an email")
 private  String email;

@Column(name = "password")
@Length(min = 5, message = "*Your password must have at least 5 characters")
@NotEmpty(message = "*Please provide your password")
@Transient
 private  String password;

@Column(name = "name")
@NotEmpty(message = "*Please provide your name")
 private  String name;

@Column(name = "last_name")
@NotEmpty(message = "*Please provide your last name")
 private  String lastName;

@Column(name = "active")
 private  int active;

@Column(name = "dept")
@NotEmpty(message = "*Please provide your department")
 private  String dept;

@Column(name = "a_type")
@NotEmpty(message = "*Please provide your type")
 private  String a_type;

@Column(name = "location")
@NotEmpty(message = "*Please provide your location")
 private  String location;

@Column(name = "mobile")
@Length(max = 10, min = 10, message = "Phone number is not valid. Should be of length 10.")
@NotEmpty(message = "Phone field is mendatory.")
@NumberFormat(style = Style.NUMBER)
 private  String mobile;

@Column(name = "customer_id")
 private  int customer_id;

@ManyToMany(cascade = CascadeType.ALL)
@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
 private  Set<Role> roles;


public void setName(String name){
    this.name = name;
}


public void setPassword(String password){
    this.password = password;
}


public String getLocation(){
    return location;
}


public String getName(){
    return name;
}


public void seta_type(String a_type){
    this.a_type = a_type;
}


public String getDept(){
    return dept;
}


public void setCustomer_id(int customer_id){
    this.customer_id = customer_id;
}


public String geta_type(){
    return dept;
}


public int getId(){
    return id;
}


public String getLastName(){
    return lastName;
}


public void setDept(String dept){
    this.dept = dept;
}


public int getCustomer_id(){
    return customer_id;
}


public void setLastName(String lastName){
    this.lastName = lastName;
}


public void setMobile(String mobile){
    this.mobile = mobile;
}


public String getPassword(){
    return password;
}


public void setActive(int active){
    this.active = active;
}


public void setEmail(String email){
    this.email = email;
}


public void setLocation(String location){
    this.location = location;
}


public String getMobile(){
    return mobile;
}


public void setId(int id){
    this.id = id;
}


public String getEmail(){
    return email;
}


public int getActive(){
    return active;
}


public void setRoles(Set<Role> roles){
    this.roles = roles;
}


public Set<Role> getRoles(){
    return roles;
}


}