package com.DTO;
 import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import com.circle.constant.SystemDict;
import com.circle.pojo.circle.Circle;
import com.circle.pojo.msg.MessageBean;
import com.circle.pojo.user.User;
import com.circle.service.circle.ICircleService;
import com.circle.service.msg.IMsgService;
import com.circle.utils.msg.MsgQueue;
import com.xwtec.xwserver.exception.SPTException;
import com.xwtec.xwserver.util.CommonUtil;
import com.xwtec.xwserver.util.SpringUtils;
import com.Interface.User;
import com.DTO.IMsgService;
public class JoinCircleThread implements Runnable{

 private  Logger logger;

 private  String circleId;

 private  User user;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public User getUser(){
    return user;
}


public String getCircleId(){
    return circleId;
}


public void setCircleId(String circleId){
    this.circleId = circleId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCircleId"))

.queryParam("circleId",circleId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setUser(User user){
    this.user = user;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setUser"))

.queryParam("user",user)
;
restTemplate.put(builder.toUriString(),null);
}


}