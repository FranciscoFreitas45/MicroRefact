package org.jugbd.mnet.domain;
 import javax.persistence;
import java.util.HashSet;
import java.util.Set;
import org.jugbd.mnet.Request.RegisterRequest;
import org.jugbd.mnet.Request.Impl.RegisterRequestImpl;
import org.jugbd.mnet.DTO.Register;
@Entity
public class PictureInformation extends PersistentObject{

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Version
 private  Long version;

@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 private  Set<Attachment> dayOneAttachments;

@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 private  Set<Attachment> preOperativeAttachments;

@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 private  Set<Attachment> preOperationAttachments;

@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 private  Set<Attachment> postOperativeAttachments;

@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
 private  Set<Attachment> onDischargeAttachments;

@Transient
 private  Register register;

@Column(name = "id")
 private Long id;

@Transient
 private RegisterRequest registerrequest = new RegisterRequestImpl();;


public Long getVersion(){
    return version;
}


public Set<Attachment> getPostOperativeAttachments(){
    return postOperativeAttachments;
}


public void setPostOperativeAttachments(Set<Attachment> postOperativeAttachments){
    this.postOperativeAttachments = postOperativeAttachments;
}


public void setPreOperativeAttachments(Set<Attachment> preOperativeAttachments){
    this.preOperativeAttachments = preOperativeAttachments;
}


public void setVersion(Long version){
    this.version = version;
}


public Set<Attachment> getPreOperationAttachments(){
    return preOperationAttachments;
}


public void setPreOperationAttachments(Set<Attachment> preOperationAttachments){
    this.preOperationAttachments = preOperationAttachments;
}


public Long getId(){
    return id;
}


public Set<Attachment> getDayOneAttachments(){
    return dayOneAttachments;
}


public void setRegister(Register register){
 registerrequest.setRegister(register,this.id);
}



public Set<Attachment> getPreOperativeAttachments(){
    return preOperativeAttachments;
}


public Register getRegister(){
  this.register = registerrequest.getRegister(this.id);
return this.register;
}


public Set<Attachment> getOnDischargeAttachments(){
    return onDischargeAttachments;
}


public void setDayOneAttachments(Set<Attachment> dayOneAttachments){
    this.dayOneAttachments = dayOneAttachments;
}


public void setId(Long id){
    this.id = id;
}


public void setOnDischargeAttachments(Set<Attachment> onDischargeAttachments){
    this.onDischargeAttachments = onDischargeAttachments;
}


}