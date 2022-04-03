package com.softserve.edu.Resources.entity;
 import com.softserve.edu.Resources.Constants;
import javax.persistence;
import java.util.Calendar;
import java.util.Date;
import com.softserve.edu.Resources.Request.UserRequest;
import com.softserve.edu.Resources.Request.Impl.UserRequestImpl;
import com.softserve.edu.Resources.DTO.User;
@Entity
@Table(name = "verification_token")
public class VerificationToken {

 private  int EXPIRATION;

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
 private  Long id;

@Column
 private  String token;

@Transient
 private  User user;

@Column(name = "expiry_date")
 private  Date expiryDate;

@Column(name = "idXLHJ")
 private Long idXLHJ;

@Transient
 private UserRequest userrequest = new UserRequestImpl();;

public VerificationToken() {
    super();
}public VerificationToken(final String token) {
    this.token = token;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
}public VerificationToken(final String token, final User user) {
    this.token = token;
    this.user = user;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
}
public void updateToken(String token){
    this.token = token;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
}


public Date getExpiryDate(){
    return expiryDate;
}


public User getUser(){
  this.user = userrequest.getUser(this.idXLHJ);
return this.user;
}}



public Long getId(){
    return id;
}


public Date calculateExpiryDate(int expiryTimeInMinutes){
    final Calendar cal = Calendar.getInstance();
    cal.setTimeInMillis(new Date().getTime());
    cal.add(Calendar.MINUTE, expiryTimeInMinutes);
    return new Date(cal.getTime().getTime());
}


public VerificationToken setToken(String token){
    this.token = token;
    return this;
}


public VerificationToken setExpiryDate(Date expiryDate){
    this.expiryDate = expiryDate;
    return this;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((expiryDate == null) ? 0 : expiryDate.hashCode());
    result = prime * result + ((token == null) ? 0 : token.hashCode());
    result = prime * result + ((user == null) ? 0 : user.hashCode());
    return result;
}


public String getToken(){
    return token;
}


@Override
public boolean equals(Object obj){
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    final VerificationToken other = (VerificationToken) obj;
    if (expiryDate == null) {
        if (other.expiryDate != null) {
            return false;
        }
    } else if (!expiryDate.equals(other.expiryDate)) {
        return false;
    }
    if (token == null) {
        if (other.token != null) {
            return false;
        }
    } else if (!token.equals(other.token)) {
        return false;
    }
    if (user == null) {
        if (other.user != null) {
            return false;
        }
    } else if (!user.equals(other.user)) {
        return false;
    }
    return true;
}


public VerificationToken setId(Long id){
    this.id = id;
    return this;
}


@Override
public String toString(){
    final StringBuilder builder = new StringBuilder();
    builder.append("Token [String=").append(token).append("]").append("[Expires").append(expiryDate).append("]");
    return builder.toString();
}


public VerificationToken setUser(User user){
  this.user = userrequest.setUser(user,this.idXLHJ);
return this.user;
}}



}