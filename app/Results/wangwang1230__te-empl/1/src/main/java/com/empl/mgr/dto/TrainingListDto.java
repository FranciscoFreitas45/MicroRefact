package com.empl.mgr.dto;
 import java.io.Serializable;
public class TrainingListDto implements Serializable{

 private  long serialVersionUID;

 private  long id;

 private  String name;

 private  String description;

 private  int number;

 private  String start;

 private  String end;

 private  String creator;

 private  boolean insertion;

 private  String time;

 private  int state;

 private  String stateKey;

 private  String trainingResult;

public TrainingListDto() {
// TODO Auto-generated constructor stub
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String getStart(){
    return start;
}


public String getTime(){
    return time;
}


public long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getTrainingResult(){
    return trainingResult;
}


public int getNumber(){
    return number;
}


public void setStart(String start){
    this.start = start;
}


public void setId(long id){
    this.id = id;
}


public void setEnd(String end){
    this.end = end;
}


public void setTime(String time){
    this.time = time;
}


public void setTrainingResult(String trainingResult){
    this.trainingResult = trainingResult;
}


public void setCreator(String creator){
    this.creator = creator;
}


public String getStateKey(){
    return stateKey;
}


public String getEnd(){
    return end;
}


public String getCreator(){
    return creator;
}


public void setNumber(int number){
    this.number = number;
}


public int getState(){
    return state;
}


public void setStateKey(String stateKey){
    this.stateKey = stateKey;
}


public boolean isInsertion(){
    return insertion;
}


public void setState(int state){
    this.state = state;
}


@Override
public String toString(){
    return "TrainingListDto [id:" + id + ", name:" + name + ", description:" + description + ", number:" + number + ", start:" + start + ", end:" + end + ", creator:" + creator + ", insertion:" + insertion + ", time:" + time + ", state:" + state + ", stateKey:" + stateKey + ", trainingResult:" + trainingResult + "]";
}


public void setInsertion(boolean insertion){
    this.insertion = insertion;
}


}