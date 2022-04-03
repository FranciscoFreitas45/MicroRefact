package com.webapp.payload.request;
 import javax.validation.constraints.NotBlank;
public class CreateWordRequest {

@NotBlank
 private  String original;

@NotBlank
 private  String translation;

@NotBlank
 private  Long userId;


public void setOriginal(String original){
    this.original = original;
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