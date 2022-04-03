package DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import com.kingen.vo.Comboable;
public class ClientContact implements Comboable{

 private  String id;

 private  String email;

 private  String userName;

 private  String pwd;

 private  String phone;

 private  String salt;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";

// Constructors
/**
 * default constructor
 */
public ClientContact() {
}/**
 * full constructor
 */
public ClientContact(String email, String userName, String pwd, String phone) {
    this.email = email;
    this.userName = userName;
    this.pwd = pwd;
    this.phone = phone;
}
@Column(name = "phone", length = 20)
public String getPhone(){
    return this.phone;
}


@Override
@Transient
public String getName(){
    return userName;
}


@Column(name = "salt")
public String getSalt(){
    return salt;
}


@GenericGenerator(name = "generator", strategy = "uuid")
@Id
@GeneratedValue(generator = "generator")
@Column(name = "id", unique = true, nullable = false, length = 32)
public String getId(){
    return this.id;
}


@Column(name = "pwd", length = 100)
public String getPwd(){
    return this.pwd;
}


@Column(name = "email", length = 50)
public String getEmail(){
    return this.email;
}


@Column(name = "user_name", length = 50)
public String getUserName(){
    return this.userName;
}


@Override
@Transient
public String getCode(){
    return id;
}


public void setSalt(String salt){
    this.salt = salt;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSalt"))

.queryParam("salt",salt)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPwd(String pwd){
    this.pwd = pwd;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setPwd"))

.queryParam("pwd",pwd)
;
restTemplate.put(builder.toUriString(),null);
}


}