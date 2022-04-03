package com.metservice.kanban.utils;
 public class MessageUtils {


public String decorateWithChar(char c,String str){
    return c + str + c;
}


public String decorateSingleQuotes(String s){
    return decorateWithChar('\'', s);
}


}