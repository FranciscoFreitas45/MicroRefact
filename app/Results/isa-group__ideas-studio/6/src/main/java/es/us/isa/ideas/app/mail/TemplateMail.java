package es.us.isa.ideas.app.mail;
 import java.util.HashSet;
import java.util.Map;
import java.util.Set;
public class TemplateMail {

 private  String subject;

 private  String content;

 private  Set<String> attachments;


public Set<String> getAttachments(){
    return attachments;
}


public String getSubject(){
    return subject;
}


public void setAttachments(Set<String> attachments){
    this.attachments = attachments;
}


public void setSubject(String subject){
    this.subject = subject;
}


public void setContent(String content){
    this.content = content;
}


public String getCustomizedContent(Map<String,String> customizations){
    return customize(customizations, getContent());
}


public String getContent(){
    return content;
}


public Set<String> getCustomizedAttachments(Map<String,String> customizations){
    Set<String> result = new HashSet<String>();
    for (String attachment : getAttachments()) {
        result.add(customize(customizations, attachment));
    }
    return result;
}


public String getCustomizedSubject(Map<String,String> customizations){
    return customize(customizations, getSubject());
}


public String customize(Map<String,String> customizations,String templateString){
    String result = templateString;
    for (String key : customizations.keySet()) {
        result = result.replace(key, customizations.get(key));
    }
    return result;
}


}