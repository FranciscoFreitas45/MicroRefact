import java.sql.Timestamp;
public class UserDetailsModel {

 private  Integer userId;

 private  String userName;

 private  String email;

 private  String password;

 private  Timestamp createdDate;

 private  Boolean live;

 private  Timestamp updatedDate;


public Boolean getLive(){
    return live;
}


public Timestamp getUpdatedDate(){
    return updatedDate;
}


public String getPassword(){
    return password;
}


public String getUserName(){
    return userName;
}


public String getEmail(){
    return email;
}


public Timestamp getCreatedDate(){
    return createdDate;
}


public Integer getUserId(){
    return userId;
}


}