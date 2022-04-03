package com.ec.survey.model.delphi;
 public class DelphiUpdateResult {

 private  String message;

 private  String link;

 private  boolean open;

 private  boolean changedForMedian;

 private  boolean changeExplanationText;

public DelphiUpdateResult(String message) {
    this.message = message;
}
public void setLink(String link){
    this.link = link;
}


public String getLink(){
    return link;
}


public void setOpen(boolean open){
    this.open = open;
}


public boolean isChangeExplanationText(){
    return changeExplanationText;
}


public boolean isOpen(){
    return open;
}


public Boolean getChangedForMedian(){
    return changedForMedian;
}


public void setChangeExplanationText(boolean changeExplanationText){
    this.changeExplanationText = changeExplanationText;
}


public String getMessage(){
    return message;
}


public void setMessage(String message){
    this.message = message;
}


public void setChangedForMedian(Boolean changedForMedian){
    this.changedForMedian = changedForMedian;
}


}