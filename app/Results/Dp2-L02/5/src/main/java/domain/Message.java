package domain;
 import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity{

 private  Date moment;

 private  String subject;

 private  String body;

 private  String priority;

 private  String tags;

 private  Actor sender;

 private  Actor receiver;

public Message() {
    // For Json purposes
    super();
}
@NotBlank
public String getSubject(){
    return this.subject;
}


public void setSubject(String subject){
    this.subject = subject;
}


@NotNull
@ManyToOne
public Actor getReceiver(){
    return this.receiver;
}


@NotBlank
public String getBody(){
    return this.body;
}


public String getTags(){
    return this.tags;
}


public void setTags(String tags){
    this.tags = tags;
}


public void setBody(String body){
    this.body = body;
}


public void setReceiver(Actor receiver){
    this.receiver = receiver;
}


@Past
@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
public Date getMoment(){
    return this.moment;
}


@Valid
@NotBlank
public String getPriority(){
    return this.priority;
}


public void setMoment(Date moment){
    this.moment = moment;
}


public void setSender(Actor sender){
    this.sender = sender;
}


@NotNull
@ManyToOne(optional = false)
public Actor getSender(){
    return this.sender;
}


public void setPriority(String priority){
    this.priority = priority;
}


}