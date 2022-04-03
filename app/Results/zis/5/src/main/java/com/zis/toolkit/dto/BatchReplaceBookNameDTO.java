package com.zis.toolkit.dto;
 import org.hibernate.validator.constraints.NotBlank;
public class BatchReplaceBookNameDTO {

@NotBlank(message = "原始内容不能为空")
 private  String orig;

 private  String replace;


public String getOrig(){
    return orig;
}


public String getReplace(){
    return replace;
}


public void setReplace(String replace){
    this.replace = replace;
}


public void setOrig(String orig){
    this.orig = orig;
}


}