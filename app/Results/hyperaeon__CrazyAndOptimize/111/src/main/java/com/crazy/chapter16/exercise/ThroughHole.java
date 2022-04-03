package com.crazy.chapter16.exercise;
 public class ThroughHole implements Runnable{

 private  Hole hole;

 private  String personName;

public ThroughHole(Hole hole, String personName) {
    this.hole = hole;
    this.personName = personName;
}
public String getPersonName(){
    return personName;
}


public void setPersonName(String personName){
    this.personName = personName;
}


@Override
public void run(){
    synchronized (hole) {
        System.out.println("Name: " + personName);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}


}