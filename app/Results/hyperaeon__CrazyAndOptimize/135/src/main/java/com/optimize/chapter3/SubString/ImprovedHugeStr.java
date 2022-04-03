package com.optimize.chapter3.SubString;
 import java.util.ArrayList;
import java.util.List;
public class ImprovedHugeStr {

 private  String str;


public String getSubString(int begin,int end){
    return new String(str.substring(begin, end));
}


}