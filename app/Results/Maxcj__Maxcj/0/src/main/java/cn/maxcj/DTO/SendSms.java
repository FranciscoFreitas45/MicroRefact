package cn.maxcj.DTO;
 import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;
public class SendSms {

 private  Integer appid;

 private  String appkey;

 private  Integer templateId;

 private  String smsSign;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public boolean sendSms(String phoneNum,String realName,String clubName,String thing,String state){
    String[] phoneNumbers = { phoneNum };
    String[] params = { realName, clubName, thing, state };
    try {
        SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
        // 签名参数未提供或者为空时，会使用默认签名发送短信
        SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0], templateId, params, smsSign, "", "");
    } catch (HTTPException e) {
        // HTTP响应码错误
        e.printStackTrace();
        return false;
    } catch (JSONException e) {
        // json解析错误
        e.printStackTrace();
        return false;
    } catch (IOException e) {
        // 网络IO错误
        e.printStackTrace();
        return false;
    }
    return true;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/sendSms"))

.queryParam("phoneNum",phoneNum)
.queryParam("realName",realName)
.queryParam("clubName",clubName)
.queryParam("thing",thing)
.queryParam("state",state)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}