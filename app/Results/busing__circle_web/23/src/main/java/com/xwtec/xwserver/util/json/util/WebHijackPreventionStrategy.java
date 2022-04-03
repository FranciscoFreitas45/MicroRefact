package com.xwtec.xwserver.util.json.util;
 public class WebHijackPreventionStrategy {

 public  WebHijackPreventionStrategy COMMENTS;

 public  WebHijackPreventionStrategy INFINITE_LOOP;


public String protect(String str){
    return "while(1);" + str;
}


}