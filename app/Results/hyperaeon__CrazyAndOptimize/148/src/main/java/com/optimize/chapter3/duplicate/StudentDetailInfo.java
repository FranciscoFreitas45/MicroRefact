package com.optimize.chapter3.duplicate;
 public class StudentDetailInfo {

 private Student s;

public StudentDetailInfo(Student s) {
    this.s = s;
}
@Override
public String toString(){
    return s.getName() + "'s detail information";
}


}