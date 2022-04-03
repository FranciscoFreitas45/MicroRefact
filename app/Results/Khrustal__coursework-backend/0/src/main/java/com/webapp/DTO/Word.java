package com.webapp.DTO;
 import javax.persistence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;
import com.webapp.Request.UserRequest;
import com.webapp.Request.Impl.UserRequestImpl;
import com.webapp.DTO.User;
public class Word {

 private  Long id;

 private  String original;

 private  String translation;

 private  User user;

 private  Long userId;

 private Long idJI9E;

 private UserRequest userrequest = new UserRequestImpl();;

public Word() {
}public Word(@NotBlank String original, @NotBlank String translation, User user) {
    this.original = original;
    this.translation = translation;
    this.user = user;
    userId = user.getId();
}
public void setOriginal(String original){
    this.original = original;
}


public Long getId(){
    return id;
}


public void setTranslation(String translation){
    this.translation = translation;
}


public String getOriginal(){
    return original;
}


public String getTranslation(){
    return translation;
}


public Long getUserId(){
    return userId;
}


public void setUserId(Long userId){
    this.userId = userId;
}


}