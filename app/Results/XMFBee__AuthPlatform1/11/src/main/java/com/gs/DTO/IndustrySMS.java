package com.gs.DTO;
 import com.gs.common.mes.Config;
import com.gs.common.mes.HttpUtil;
public class IndustrySMS {

 private  String operation;

 private  String accountSid;

 private  String to;

 private  String smsContent;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";

public IndustrySMS(String to, String smsContent) {
    this.to = to;
    this.smsContent = smsContent;
}
public void execute(){
    String url = Config.BASE_URL + operation;
    String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + smsContent + HttpUtil.createCommonParam();
    // 提交请求
    String result = HttpUtil.post(url, body);
    System.out.println("result:" + System.lineSeparator() + result);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/execute"))

;
restTemplate.put(builder.toUriString(),null);
}


}