package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_spt_point")
@org.hibernate.annotations.Proxy(lazy = false)
public class SalesPatterPoint {

 private  long serialVersionUID;

 private  String id;

 private  String questionid;

 private  String name;

 private  String pointtype;

 private  String focusword;

 private  Integer mincalltime;

 private  Integer maxcalltime;

 private  int score;

 private  String orgi;

 private  String creater;

 private  Date createtime;

 private  Date updatetime;

 private  String processid;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setQuestionid(String questionid){
    this.questionid = questionid;
}


public void setFocusword(String focusword){
    this.focusword = focusword;
}


public void setMaxcalltime(Integer maxcalltime){
    this.maxcalltime = maxcalltime;
}


public Integer getMincalltime(){
    return mincalltime;
}


public void setUpdatetime(Date updatetime){
    this.updatetime = updatetime;
}


public void setProcessid(String processid){
    this.processid = processid;
}


public void setOrgi(String orgi){
    this.orgi = orgi;
}


@Id
@Column(length = 32)
@GeneratedValue(generator = "system-uuid")
@GenericGenerator(name = "system-uuid", strategy = "uuid")
public String getId(){
    return id;
}


public String getQuestionid(){
    return questionid;
}


public Date getCreatetime(){
    return createtime;
}


public void setPointtype(String pointtype){
    this.pointtype = pointtype;
}


public void setId(String id){
    this.id = id;
}


public String getCreater(){
    return creater;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public void setMincalltime(Integer mincalltime){
    this.mincalltime = mincalltime;
}


public Date getUpdatetime(){
    return updatetime;
}


public String getPointtype(){
    return pointtype;
}


public String getOrgi(){
    return orgi;
}


public String getProcessid(){
    return processid;
}


public void setCreater(String creater){
    this.creater = creater;
}


public Integer getMaxcalltime(){
    return maxcalltime;
}


public String getFocusword(){
    return focusword;
}


public int getScore(){
    return score;
}


public void setScore(int score){
    this.score = score;
}


}