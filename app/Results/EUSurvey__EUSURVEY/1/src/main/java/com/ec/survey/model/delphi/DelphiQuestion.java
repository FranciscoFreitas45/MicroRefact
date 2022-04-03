package com.ec.survey.model.delphi;
 public class DelphiQuestion {

 private  String title;

 private  String uid;

 private  int id;

 private  String answer;

 private  boolean maxDistanceExceeded;

 private  boolean changedForMedian;

 private  boolean hasUnreadComments;


public void setMaxDistanceExceeded(boolean maxDistanceExceeded){
    this.maxDistanceExceeded = maxDistanceExceeded;
}


public void setTitle(String title){
    this.title = title;
}


public int getId(){
    return id;
}


public void setUid(String uid){
    this.uid = uid;
}


public void setHasUnreadComments(boolean hasUnreadComments){
    this.hasUnreadComments = hasUnreadComments;
}


public String getUid(){
    return uid;
}


public String getTitle(){
    return title;
}


public boolean getHasUnreadComments(){
    return hasUnreadComments;
}


public Boolean getChangedForMedian(){
    return changedForMedian;
}


public String getAnswer(){
    return answer;
}


public void setAnswer(String answer){
    this.answer = answer;
}


public boolean isMaxDistanceExceeded(){
    return maxDistanceExceeded;
}


public void setId(int id){
    this.id = id;
}


public void setChangedForMedian(Boolean changedForMedian){
    this.changedForMedian = changedForMedian;
}


}