package com.DTO;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.management.relation.Role;
import java.util.Set;

public class User {

 private  Long userId;

 private  String mail;

 private  String firstName;

 private  String lastName;

 private  String password;

 private Set<Role> roles;
/*
 private  List<Reservation> reservations;
 */

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://7";


public String getLastName(){
    return lastName;
}


public String getMail(){
    return mail;
}


public String getPassword(){
    return password;
}

/*
public List<Reservation> getReservations(){
return this.reservations;
}

*/
public String getFirstName(){
    return firstName;
}


public Long getUserId(){
    return userId;
}


public Set<Role> getRoles(){
    return roles;
}


public void setPassword(String password){
    this.password = password;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ userId).concat("/setPassword"))

.queryParam("password",password);
restTemplate.put(builder.toUriString(),null);
}


public void setUserId(Long userId){
    this.userId = userId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ userId).concat("/setUserId"))

.queryParam("userId",userId);
restTemplate.put(builder.toUriString(),null);
}


}