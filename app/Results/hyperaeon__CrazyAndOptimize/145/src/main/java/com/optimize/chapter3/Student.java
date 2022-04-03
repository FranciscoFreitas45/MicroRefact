package com.optimize.chapter3;
 public class Student implements Comparable<Student>{

 private  String name;

 private  int score;

public Student(String name, int s) {
    this.name = name;
    this.score = s;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("name:");
    sb.append(name);
    sb.append(" ");
    sb.append("score:");
    sb.append(score);
    return sb.toString();
}


@Override
public int compareTo(Student o){
    if (o.score < this.score) {
        return 1;
    } else if (o.score > this.score) {
        return -1;
    }
    return 0;
}


public int getScore(){
    return score;
}


public void setScore(int score){
    this.score = score;
}


}