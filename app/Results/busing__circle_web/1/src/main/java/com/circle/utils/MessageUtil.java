package com.circle.utils;
 import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
public class MessageUtil {

 private  String BASE_URI;

 private  String VERSION;

 private  String ENCODING;

 private  String URI_GET_USER_INFO;

 private  String URI_SEND_SMS;

 private  String URI_TPL_SEND_SMS;

 private  String APIKEY;

 private  String COMPANY;

 private  int UPDATE_PASS_TPL_ID;

 private  int REGISTER_TPL_ID;

 private  int ORDERNOTICE_TPL_ID;

 private  int CHANGEMOBILE_TPL_ID;

 private  int AUDITFARM_YES_TPL_ID;

 private  int AUDITFARM_NO_TPL_ID;

 private  int AUDITADDRESS_NO_TPL_ID;

 private  int AUDITADDRESS_YES_TPL_ID;

 public  Logger logger;


public Boolean sendOrderNotice(String mobile,String order_no){
    try {
        String tpl_value = "#order_no#=" + order_no;
        ;
        return tplSendSms(ORDERNOTICE_TPL_ID, tpl_value, mobile);
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}


public boolean tplSendSms(long tpl_id,String tpl_value,String mobile){
    HttpClient httpclient = new DefaultHttpClient();
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("apikey", APIKEY));
    params.add(new BasicNameValuePair("tpl_id", String.valueOf(tpl_id)));
    params.add(new BasicNameValuePair("tpl_value", tpl_value));
    params.add(new BasicNameValuePair("mobile", mobile));
    HttpPost httpPost = new HttpPost(URI_TPL_SEND_SMS);
    UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "utf-8");
    httpPost.setEntity(entity);
    HttpResponse loginResponse = httpclient.execute(httpPost);
    HttpEntity responseEntity = loginResponse.getEntity();
    String result = EntityUtils.toString(responseEntity);
    JSONObject jsonObject = JSONObject.fromObject(result);
    logger.info("send message info:" + jsonObject.get("msg"));
    if ("OK".equals(jsonObject.get("msg"))) {
        return true;
    } else {
        logger.error("send message faild:" + jsonObject.get("msg"));
    }
    return false;
}


public Boolean sendAuditAddessYes(String mobile,String time,String address){
    try {
        String tpl_value = "#time#=" + time + "#address#=" + address;
        return tplSendSms(AUDITADDRESS_YES_TPL_ID, tpl_value, mobile);
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}


public Boolean sendRegisterCode(String mobile,String code){
    try {
        String tpl_value = "#code#=" + code;
        return tplSendSms(REGISTER_TPL_ID, tpl_value, mobile);
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}


public Boolean sendAuditFarmNo(String mobile,String farm_name){
    try {
        String tpl_value = "#farm_name#=" + farm_name;
        ;
        return tplSendSms(AUDITFARM_NO_TPL_ID, tpl_value, mobile);
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}


public Boolean sendUpdatePassCode(String mobile,String code){
    try {
        String tpl_value = "#code#=" + code;
        return tplSendSms(UPDATE_PASS_TPL_ID, tpl_value, mobile);
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}


public Boolean sendChangeMobile(String mobile,String code){
    try {
        String tpl_value = "#code#=" + code;
        ;
        return tplSendSms(CHANGEMOBILE_TPL_ID, tpl_value, mobile);
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}


public void main(String[] args){
    // 修改为您要发送的手机号
    String mobile = "13815412201";
    /**
     * ************* 使用模板接口发短信 ****************
     */
    // 设置模板ID，如使用1号模板:您的验证码是#code#【#company#】
    long tpl_id = 2;
    // 设置对应的模板变量值
    String tpl_value = "1234";
    // 模板发送的调用示例
    System.out.println(MessageUtil.sendRegisterCode(mobile, tpl_value));
}


public Boolean sendAuditFarmYes(String mobile,String farm_name){
    try {
        String tpl_value = "#farm_name#=" + farm_name;
        ;
        return tplSendSms(AUDITFARM_YES_TPL_ID, tpl_value, mobile);
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}


public Boolean sendAuditAddessNo(String mobile,String time,String address){
    try {
        String tpl_value = "#time#=" + time + "#address#=" + address;
        ;
        return tplSendSms(AUDITADDRESS_NO_TPL_ID, tpl_value, mobile);
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}


}