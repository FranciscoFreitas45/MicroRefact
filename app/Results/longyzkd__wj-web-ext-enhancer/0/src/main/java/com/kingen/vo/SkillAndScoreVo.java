package com.kingen.vo;
 import com.kingen.bean.PersonSkillScore;
import com.kingen.bean.SkillCat;
public class SkillAndScoreVo {

 private  String userId;

 private  String skillId;

 private  Integer score;

 private  String name;

 private  String id;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setSkillId(String skillId){
    this.skillId = skillId;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public String getSkillId(){
    return skillId;
}


public Integer getScore(){
    return score;
}


public String getUserId(){
    return userId;
}


public void setScore(Integer score){
    this.score = score;
}


public void setUserId(String userId){
    this.userId = userId;
}


}