package com.xwtec.xwserver.util.json.util.WebHijackPreventionStrategy;
 public class InfiniteLoopWebHijackPreventionStrategy extends WebHijackPreventionStrategy{


public String protect(String str){
    return "while(1);" + str;
}


}