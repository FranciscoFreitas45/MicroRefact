import javax.persistence;
public class User {

 private  int user_id;

 private  int empId;

 private  int empTypeId;

 private  String locId;

 private  String userName;

 private  String userPwd;

 private  int delStatus;

 private  int isActive;

 private  int makerUserId;

 private  String makerEnterDatetime;

 private  int exInt1;

 private  int exInt2;

 private  int exInt3;

 private  String exVar1;

 private  String exVar2;

 private  String exVar3;

 private  boolean error;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://21";


public String getExVar2(){
    return exVar2;
}


public String getExVar3(){
    return exVar3;
}


public int getExInt2(){
    return exInt2;
}


public int getExInt3(){
    return exInt3;
}


public int getExInt1(){
    return exInt1;
}


public String getLocId(){
    return locId;
}


public String getExVar1(){
    return exVar1;
}


public int getUser_id(){
    return user_id;
}


public String getUserName(){
    return userName;
}


public int getEmpTypeId(){
    return empTypeId;
}


public String getMakerEnterDatetime(){
    return makerEnterDatetime;
}


public int getMakerUserId(){
    return makerUserId;
}


public int getEmpId(){
    return empId;
}


public int getIsActive(){
    return isActive;
}


public int getDelStatus(){
    return delStatus;
}


public String getUserPwd(){
    return userPwd;
}


public void setError(boolean error){
    this.error = error;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ user_id).concat("/setError"));

.queryParam("error",error);
restTemplate.put(builder.toUriString(),null);
}


}