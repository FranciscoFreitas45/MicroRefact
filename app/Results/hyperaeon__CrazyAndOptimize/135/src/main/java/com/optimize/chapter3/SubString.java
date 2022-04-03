package com.optimize.chapter3;
 import java.util.ArrayList;
import java.util.List;
public class SubString {

 private  String str;

 private  String str;


public void main(String[] args){
    List<String> handler = new ArrayList<String>();
    for (int i = 0; i < 10000; i++) {
        HugeStr h = new HugeStr();
        ImprovedHugeStr ih = new ImprovedHugeStr();
        handler.add(h.getSubString(0, 10000));
    }
}


public String getSubString(int begin,int end){
    return new String(str.substring(begin, end));
}


}