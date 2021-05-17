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


public void setPassword(String password){
    this.password = password;
}


public void setLive(Boolean live){
    this.live = live;
}


public Timestamp getUpdatedDate(){
    return updatedDate;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public void setCreatedDate(Timestamp createdDate){
    this.createdDate = createdDate;
}


public void setUserName(String userName){
    this.userName = userName;
}


public String getUserName(){
    return userName;
}


public String getEmail(){
    return email;
}


public void setUpdatedDate(Timestamp updatedDate){
    this.updatedDate = updatedDate;
}


public Timestamp getCreatedDate(){
    return createdDate;
}


public Integer getUserId(){
    return userId;
}


public void setUserId(Integer userId){
    this.userId = userId;
}


}