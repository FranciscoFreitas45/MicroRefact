package com.webapp.models;
 import javax.persistence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;
import com.webapp.Request.UserRequest;
import com.webapp.Request.Impl.UserRequestImpl;
import com.webapp.DTO.User;
@Entity
@Table(name = "words")
public class Word {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@NotBlank
@Size(max = 120)
 private  String original;

@NotBlank
@Size(max = 120)
 private  String translation;

@Transient
 private  User user;

@Column(name = "user_id")
 private  Long userId;

@Column(name = "idJI9E")
 private Long idJI9E;

@Transient
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