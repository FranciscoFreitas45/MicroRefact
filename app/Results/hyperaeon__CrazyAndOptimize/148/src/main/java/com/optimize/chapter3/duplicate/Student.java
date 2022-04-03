package com.optimize.chapter3.duplicate;
 public class Student implements Comparable<Student>{

 private  String name;

 private  int score;

public Student(String name, int score) {
    this.name = name;
    this.score = score;
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


@Override
public String toString(){
    StringBuffer sb = new StringBuffer();
    sb.append("name:");
    sb.append(name);
    sb.append(" ");
    sb.append("score:");
    sb.append(score);
    return sb.toString();
}


@Override
public int compareTo(Student o){
    if (this.score < o.score) {
        return -1;
    } else if (this.score > o.score) {
        return 1;
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