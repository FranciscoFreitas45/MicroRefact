package com.easyshopping.DTO;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class Message extends BaseEntity{

 private  long serialVersionUID;

 private  String title;

 private  String content;

 private  String ip;

 private  Boolean isDraft;

 private  Boolean senderRead;

 private  Boolean receiverRead;

 private  Boolean senderDelete;

 private  Boolean receiverDelete;

 private  Member sender;

 private  Member receiver;

 private  Message forMessage;

 private  Set<Message> replyMessages;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


@OneToMany(mappedBy = "forMessage", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy(value = "createDate asc")
public Set<Message> getReplyMessages(){
    return replyMessages;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(updatable = false)
public Member getReceiver(){
    return receiver;
}


@Column(nullable = false, updatable = false)
public String getIp(){
    return ip;
}


@NotEmpty
@Length(max = 1000)
@Column(nullable = false, updatable = false, length = 1000)
public String getContent(){
    return content;
}


public void setTitle(String title){
    this.title = title;
}


@Column(nullable = false)
public Boolean getReceiverRead(){
    return receiverRead;
}


@Column(nullable = false, updatable = false)
public Boolean getIsDraft(){
    return isDraft;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(updatable = false)
public Message getForMessage(){
    return forMessage;
}


@Column(nullable = false, updatable = false)
public String getTitle(){
    return title;
}


@Column(nullable = false)
public Boolean getSenderRead(){
    return senderRead;
}


public void setSenderDelete(Boolean senderDelete){
    this.senderDelete = senderDelete;
}


@Column(nullable = false)
public Boolean getSenderDelete(){
    return senderDelete;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(updatable = false)
public Member getSender(){
    return sender;
}


@Column(nullable = false)
public Boolean getReceiverDelete(){
    return receiverDelete;
}


public void setReceiverRead(Boolean receiverRead){
    this.receiverRead = receiverRead;
}


public void setSenderRead(Boolean senderRead){
    this.senderRead = senderRead;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSenderRead"))

.queryParam("senderRead",senderRead)
;
restTemplate.put(builder.toUriString(),null);
}


public void setContent(String content){
    this.content = content;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setContent"))

.queryParam("content",content)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIp(String ip){
    this.ip = ip;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIp"))

.queryParam("ip",ip)
;
restTemplate.put(builder.toUriString(),null);
}


public void setIsDraft(Boolean isDraft){
    this.isDraft = isDraft;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setIsDraft"))

.queryParam("isDraft",isDraft)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReceiverDelete(Boolean receiverDelete){
    this.receiverDelete = receiverDelete;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setReceiverDelete"))

.queryParam("receiverDelete",receiverDelete)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSender(Member sender){
    this.sender = sender;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSender"))

.queryParam("sender",sender)
;
restTemplate.put(builder.toUriString(),null);
}


public void setReceiver(Member receiver){
    this.receiver = receiver;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setReceiver"))

.queryParam("receiver",receiver)
;
restTemplate.put(builder.toUriString(),null);
}


public void setForMessage(Message forMessage){
    this.forMessage = forMessage;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setForMessage"))

.queryParam("forMessage",forMessage)
;
restTemplate.put(builder.toUriString(),null);
}


}