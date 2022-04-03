package org.jugbd.mnet.DTO;
 import javax.persistence;
import java.util.HashSet;
import java.util.Set;
public class PictureInformation extends PersistentObject{

 private  Long id;

 private  Long version;

 private  Set<Attachment> dayOneAttachments;

 private  Set<Attachment> preOperativeAttachments;

 private  Set<Attachment> preOperationAttachments;

 private  Set<Attachment> postOperativeAttachments;

 private  Set<Attachment> onDischargeAttachments;

 private  Register register;


public Long getVersion(){
    return version;
}


public Set<Attachment> getPostOperativeAttachments(){
    return postOperativeAttachments;
}


public Set<Attachment> getPreOperationAttachments(){
    return preOperationAttachments;
}


public Long getId(){
    return id;
}


public Set<Attachment> getDayOneAttachments(){
    return dayOneAttachments;
}


public Set<Attachment> getPreOperativeAttachments(){
    return preOperativeAttachments;
}


public Register getRegister(){
    return register;
}


public Set<Attachment> getOnDischargeAttachments(){
    return onDischargeAttachments;
}


}