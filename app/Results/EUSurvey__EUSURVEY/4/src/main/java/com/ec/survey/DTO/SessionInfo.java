package com.ec.survey.DTO;
 import java.io.Serializable;
public class SessionInfo implements Serializable{

 private  long serialVersionUID;

 private  int survey;

 private  int user;

 private  int owner;

 private  String language;

 private  String shortname;

public SessionInfo() {
}public SessionInfo(int survey, int user, int owner, String language, String shortname) {
    this.survey = survey;
    this.user = user;
    this.language = language;
    this.shortname = shortname;
    this.owner = owner;
}
public String getLanguage(){
    return language;
}


public String getShortname(){
    return shortname;
}


public String getShortnameForMenu(){
    if (shortname != null) {
        if (shortname.length() > 10) {
            return shortname.substring(0, 9) + "...";
        } else {
            return shortname;
        }
    }
    return "";
}


public int getUser(){
    return user;
}


public int getSurvey(){
    return survey;
}


public int getOwner(){
    return owner;
}


}