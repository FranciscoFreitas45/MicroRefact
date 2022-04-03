package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LzWeiEnterController {

 private LzWeiEnter lzweienter;

 private LzWeiEnter lzweienter;


@PutMapping
("/setWecEnterId")
public void setWecEnterId(@RequestParam(name = "wecEnterId") Integer wecEnterId){
lzweienter.setWecEnterId(wecEnterId);
}


@PutMapping
("/setWecStatus")
public void setWecStatus(@RequestParam(name = "wecStatus") String wecStatus){
lzweienter.setWecStatus(wecStatus);
}


@PutMapping
("/setWecRegistor")
public void setWecRegistor(@RequestParam(name = "wecRegistor") Integer wecRegistor){
lzweienter.setWecRegistor(wecRegistor);
}


@PutMapping
("/setWecEnterName_Q")
public void setWecEnterName_Q(@RequestParam(name = "wecEnterName_Q") String wecEnterName_Q){
lzweienter.setWecEnterName_Q(wecEnterName_Q);
}


@PutMapping
("/setWecCusType")
public void setWecCusType(@RequestParam(name = "wecCusType") String wecCusType){
lzweienter.setWecCusType(wecCusType);
}


@PutMapping
("/setWecDefaultMsgDesc")
public void setWecDefaultMsgDesc(@RequestParam(name = "wecDefaultMsgDesc") String wecDefaultMsgDesc){
lzweienter.setWecDefaultMsgDesc(wecDefaultMsgDesc);
}


@PutMapping
("/setWecSubscribeMsgDesc")
public void setWecSubscribeMsgDesc(@RequestParam(name = "wecSubscribeMsgDesc") String wecSubscribeMsgDesc){
lzweienter.setWecSubscribeMsgDesc(wecSubscribeMsgDesc);
}


@PutMapping
("/setListKeyWords")
public void setListKeyWords(@RequestParam(name = "listKeyWords") List<Map<String,Object>> listKeyWords){
lzweienter.setListKeyWords(listKeyWords);
}


@PutMapping
("/setWecRegistdate")
public void setWecRegistdate(@RequestParam(name = "wecRegistdate") String wecRegistdate){
lzweienter.setWecRegistdate(wecRegistdate);
}


}