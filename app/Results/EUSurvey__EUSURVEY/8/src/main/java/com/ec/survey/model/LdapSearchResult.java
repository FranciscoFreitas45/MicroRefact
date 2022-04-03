package com.ec.survey.model;
 import org.springframework.util.comparator.NullSafeComparator;
import java.util.Comparator;
public class LdapSearchResult {

 private  String login;

 private  String displayName;

 private  String organisation;

 private  String group;

 private  String fname;

 private  String lname;

 public  Comparator<LdapSearchResult> FIRST;

 public  Comparator<LdapSearchResult> LAST;

 public  Comparator<LdapSearchResult> GROUP;

 public  Comparator<LdapSearchResult> DISPLAYNAME;

public LdapSearchResult(String login, String displayName, String organisation, String group, String fname, String lname) {
    super();
    this.login = login;
    this.displayName = displayName;
    this.organisation = organisation;
    this.group = group;
    this.fname = fname;
    this.lname = lname;
}
public String getLogin(){
    return login;
}


public void setGroup(String group){
    this.group = group;
}


public void setOrganisation(String organisation){
    this.organisation = organisation;
}


public String getLname(){
    return lname;
}


public void setFname(String fname){
    this.fname = fname;
}


public void setLname(String lname){
    this.lname = lname;
}


public String getDisplayName(){
    return displayName;
}


public String getOrganisation(){
    return organisation;
}


public String getFname(){
    return fname;
}


public void setLogin(String login){
    this.login = login;
}


public String getGroup(){
    return group;
}


public void setDisplayName(String displayName){
    this.displayName = displayName;
}


}