package com.dtdhehe.ptulife.entity;
 import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class HotLabel {

@Id
 private  String labelId;

 private  String postId;

 private  String labelTitle;

 private  Integer labelHot;

public HotLabel() {
}
public void setPostId(String postId){
    this.postId = postId;
}


public String getLabelId(){
    return labelId;
}


public void setLabelHot(Integer labelHot){
    this.labelHot = labelHot;
}


public String getLabelTitle(){
    return labelTitle;
}


public void setLabelTitle(String labelTitle){
    this.labelTitle = labelTitle;
}


public void setLabelId(String labelId){
    this.labelId = labelId;
}


public String getPostId(){
    return postId;
}


public Integer getLabelHot(){
    return labelHot;
}


}