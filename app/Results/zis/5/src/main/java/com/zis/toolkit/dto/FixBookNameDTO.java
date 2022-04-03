package com.zis.toolkit.dto;
 import org.hibernate.validator.constraints.NotBlank;
public class FixBookNameDTO {

@NotBlank(message = "起始字符不能为空")
 private  String startLabel;

@NotBlank(message = "关键字不能为空")
 private  String keyword;


public String getKeyword(){
    return keyword;
}


public void setKeyword(String keyword){
    this.keyword = keyword;
}


public void setStartLabel(String startLabel){
    this.startLabel = startLabel;
}


public String getStartLabel(){
    return startLabel;
}


}