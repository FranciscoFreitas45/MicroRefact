package com.xwtec.xwserver.util.json.util.WebHijackPreventionStrategy;
 public class CommentWebHijackPreventionStrategy extends WebHijackPreventionStrategy{


public String protect(String str){
    return "/*" + str + "*/";
}


}