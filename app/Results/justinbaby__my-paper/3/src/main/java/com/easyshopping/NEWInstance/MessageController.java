package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MessageController {

 private Message message;

 private Message message;


@PutMapping
("/setSenderRead")
public void setSenderRead(@RequestParam(name = "senderRead") Boolean senderRead){
message.setSenderRead(senderRead);
}


@PutMapping
("/setContent")
public void setContent(@RequestParam(name = "content") String content){
message.setContent(content);
}


@PutMapping
("/setIp")
public void setIp(@RequestParam(name = "ip") String ip){
message.setIp(ip);
}


@PutMapping
("/setIsDraft")
public void setIsDraft(@RequestParam(name = "isDraft") Boolean isDraft){
message.setIsDraft(isDraft);
}


@PutMapping
("/setReceiverDelete")
public void setReceiverDelete(@RequestParam(name = "receiverDelete") Boolean receiverDelete){
message.setReceiverDelete(receiverDelete);
}


@PutMapping
("/setSender")
public void setSender(@RequestParam(name = "sender") Member sender){
message.setSender(sender);
}


@PutMapping
("/setReceiver")
public void setReceiver(@RequestParam(name = "receiver") Member receiver){
message.setReceiver(receiver);
}


@PutMapping
("/setForMessage")
public void setForMessage(@RequestParam(name = "forMessage") Message forMessage){
message.setForMessage(forMessage);
}


}