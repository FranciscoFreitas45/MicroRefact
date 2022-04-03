package com.ec.survey.model.delphi;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.ec.survey.tools.ConversionTools;
public class DelphiComment {

 private  String user;

 private  String text;

 private  String date;

 private  int id;

 private  String answerSetUniqueCode;

 private  List<DelphiComment> replies;

 private  boolean unread;

public DelphiComment(String user, String text, Date date, int id, String answerSetUniqueCode, boolean unread) {
    this.user = user;
    this.text = text;
    this.date = ConversionTools.getFullString(date);
    this.id = id;
    this.answerSetUniqueCode = answerSetUniqueCode;
    this.replies = new ArrayList<>();
    this.unread = unread;
}
public String getAnswerSetUniqueCode(){
    return answerSetUniqueCode;
}


public String getText(){
    return text;
}


public String getUser(){
    return user;
}


public List<DelphiComment> getReplies(){
    return replies;
}


public String getDate(){
    return date;
}


public int getId(){
    return id;
}


public boolean isUnread(){
    return unread;
}


}