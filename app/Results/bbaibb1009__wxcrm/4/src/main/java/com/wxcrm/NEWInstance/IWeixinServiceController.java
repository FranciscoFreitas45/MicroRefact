package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IWeixinServiceController {

 private IWeixinService iweixinservice;


@GetMapping
("/getWeiEnterByAdminId")
public LzWeiEnter getWeiEnterByAdminId(@RequestParam(name = "wadId") Integer wadId){
  return iweixinservice.getWeiEnterByAdminId(wadId);
}


@GetMapping
("/getWeiEnterByAppId")
public LzWeiEnter getWeiEnterByAppId(@RequestParam(name = "appId") String appId){
  return iweixinservice.getWeiEnterByAppId(appId);
}


@GetMapping
("/getCurrentTikcet")
public LzWeiJsapiTicket getCurrentTikcet(@RequestParam(name = "wecId") Integer wecId){
  return iweixinservice.getCurrentTikcet(wecId);
}


@GetMapping
("/getCurrentAccessTokenStr")
public String getCurrentAccessTokenStr(@RequestParam(name = "wecId") Integer wecId){
  return iweixinservice.getCurrentAccessTokenStr(wecId);
}


@GetMapping
("/getWeiEnterById")
public LzWeiEnter getWeiEnterById(@RequestParam(name = "weiEnterId") Integer weiEnterId){
  return iweixinservice.getWeiEnterById(weiEnterId);
}


}