package com.ukefu.webim.web.model;
 import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "uk_qc_result_item")
@org.hibernate.annotations.Proxy(lazy = false)
public class QualityResultItem {

 private  long serialVersionUID;

 private  String id;

 private  String name;

 private  Date createtime;

 private  String creater;

 private  String orgi;

 private  int maxscore;

 private  int minscore;

 private  int score;

 private  String scheme;

 private  String resultid;

 private  String parentid;

 private  String type;

 private  String remarks;

 private  String itemid;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setMaxscore(int maxscore){
    this.maxscore = maxscore;
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


public void setScheme(String scheme){
    this.scheme = scheme;
}


public Date getCreatetime(){
    return createtime;
}


public int getMinscore(){
    return minscore;
}


public void setId(String id){
    this.id = id;
}


public void setParentid(String parentid){
    this.parentid = parentid;
}


public void setCreatetime(Date createtime){
    this.createtime = createtime;
}


public String getCreater(){
    return creater;
}


public int getMaxscore(){
    return maxscore;
}


public void setMinscore(int minscore){
    this.minscore = minscore;
}


public String getParentid(){
    return parentid;
}


public String getScheme(){
    return scheme;
}


public String getRemarks(){
    return remarks;
}


public void setType(String type){
    this.type = type;
}


public String getItemid(){
    return itemid;
}


public void setResultid(String resultid){
    this.resultid = resultid;
}


public String getType(){
    return type;
}


public String getOrgi(){
    return orgi;
}


public String getResultid(){
    return resultid;
}


public void setCreater(String creater){
    this.creater = creater;
}


public void setItemid(String itemid){
    this.itemid = itemid;
}


public int getScore(){
    return score;
}


public void setRemarks(String remarks){
    this.remarks = remarks;
}


public void setScore(int score){
    this.score = score;
}


}