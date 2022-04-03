package com.empl.mgr.utils;
 import java.io.Serializable;
import java.util.UUID;
public class SupportUtil implements Serializable{

 private  long serialVersionUID;


public String findUUID(){
    return UUID.randomUUID().toString();
}


}