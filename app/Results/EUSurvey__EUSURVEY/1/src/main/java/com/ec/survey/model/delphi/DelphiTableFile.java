package com.ec.survey.model.delphi;
 public class DelphiTableFile {

 final  String name;

 final  String uid;

public DelphiTableFile(String name, String uid) {
    this.name = name;
    this.uid = uid;
}
public String getUid(){
    return uid;
}


public String getName(){
    return name;
}


}