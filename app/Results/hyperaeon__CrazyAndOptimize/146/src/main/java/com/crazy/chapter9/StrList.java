package com.crazy.chapter9;
 import java.util.ArrayList;
import java.util.List;
public class StrList {

 private  List strList;


public boolean add(String ele){
    return strList.add(ele);
}


public int size(){
    return strList.size();
}


public String get(int index){
    return (String) strList.get(index);
}


}