package com.example.steam.utils;
 import org.springframework.stereotype.Component;
@Component
public class StaticField {

 public  String COOKIE_KEY;

 public  String EMAIL_KEY;

 public  String SENSITIVE;

 private  char[] IGNORE_WORD;


public char[] getIGNORE_WORD(){
    return IGNORE_WORD;
}


}