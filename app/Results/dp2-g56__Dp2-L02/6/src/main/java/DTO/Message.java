package DTO;
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
public class Message extends DomainEntity{

 private  Date moment;

 private  String subject;

 private  String body;

 private  String priority;

 private  String tags;

 private  Actor sender;

 private  Actor receiver;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://8";

public Message() {
    // For Json purposes
    super();
}
@NotBlank
public String getSubject(){
    return this.subject;
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


@NotNull
@ManyToOne(optional = false)
public Actor getSender(){
    return this.sender;
}


public void setReceiver(Actor receiver){
    this.receiver = receiver;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setReceiver"))

.queryParam("receiver",receiver)
;
restTemplate.put(builder.toUriString(),null);
}


}