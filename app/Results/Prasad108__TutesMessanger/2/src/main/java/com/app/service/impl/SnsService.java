package com.app.service.impl;
 import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.amazonaws.services.sns.model.SetSMSAttributesRequest;
@Service("snsService")
public class SnsService {

@SuppressWarnings("deprecation")
 private  AmazonSNSClient snsClient;

 private  SetSMSAttributesRequest setRequest;

 private  Map<String,MessageAttributeValue> smsAttributes;


public void build_cred(){
    snsClient.setSMSAttributes(setRequest);
}


public String sendSMSMessage(String msg,String phoneNumber){
    PublishResult result = snsClient.publish(new PublishRequest().withMessage(msg).withPhoneNumber(phoneNumber).withMessageAttributes(smsAttributes));
    return result.getMessageId();
}


}