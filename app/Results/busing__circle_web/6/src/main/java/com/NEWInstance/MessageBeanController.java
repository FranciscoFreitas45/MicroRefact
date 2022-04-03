package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MessageBeanController {

 private MessageBean messagebean;

 private MessageBean messagebean;


@PutMapping
("/setMsgContent")
public void setMsgContent(@RequestParam(name = "msgContent") String msgContent){
messagebean.setMsgContent(msgContent);
}


@PutMapping
("/setSendUserId")
public void setSendUserId(@RequestParam(name = "sendUserId") String sendUserId){
messagebean.setSendUserId(sendUserId);
}


@PutMapping
("/setReciveUserId")
public void setReciveUserId(@RequestParam(name = "reciveUserId") String reciveUserId){
messagebean.setReciveUserId(reciveUserId);
}


@PutMapping
("/setParams")
public void setParams(@RequestParam(name = "params") String params){
messagebean.setParams(params);
}


}