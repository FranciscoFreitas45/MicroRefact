package com.ukefu.util.asr;
 public class AsrResult {

 private  long serialVersionUID;

 private  String message;

 private  String id;

 private  String num;

 private  int speakms;

 private  String recordpath;

public AsrResult(String id, String message, String num) {
    this.id = id;
    this.message = message;
    this.num = num;
    this.recordpath = id + "_" + num + ".wav";
}
public void setSpeakms(int speakms){
    this.speakms = speakms;
}


public void setNum(String num){
    this.num = num;
}


public String getRecordpath(){
    return recordpath;
}


public void setRecordpath(String recordpath){
    this.recordpath = recordpath;
}


public int getSpeakms(){
    return speakms;
}


public String getNum(){
    return num;
}


public String getMessage(){
    return message;
}


public void setId(String id){
    this.id = id;
}


public String getId(){
    return id;
}


public void setMessage(String message){
    this.message = message;
}


}