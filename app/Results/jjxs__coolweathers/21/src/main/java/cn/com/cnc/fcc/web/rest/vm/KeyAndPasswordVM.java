package cn.com.cnc.fcc.web.rest.vm;
 public class KeyAndPasswordVM {

 private  String key;

 private  String newPassword;


public String getKey(){
    return key;
}


public void setNewPassword(String newPassword){
    this.newPassword = newPassword;
}


public String getNewPassword(){
    return newPassword;
}


public void setKey(String key){
    this.key = key;
}


}