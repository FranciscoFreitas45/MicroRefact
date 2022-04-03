package com.qidian.hcm.module.center.service;
 import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.qidian.hcm.common.config.HCMConfig;
import com.qidian.hcm.common.constants.RedisPrefixConstant;
import com.qidian.hcm.common.constants.SystemConstant;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.redis.RedisService;
import com.qidian.hcm.common.utils.Result;
import com.qidian.hcm.common.utils.ResultGenerator;
import com.qidian.hcm.module.center.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import com.qidian.hcm.common.utils.ResultCode.MESSAGE_SEND_FAILED;
import com.qidian.hcm.common.utils.ResultCode.USER_NOT_EXIST;
import com.qidian.hcm.Interface.HCMConfig;
@Service
@Slf4j
public class VerificationService {

 private  String CN_HANGZHOU;

@Autowired
 private  UserRepository tenantInfoRepository;

@Autowired
 private  HCMConfig hcmConfig;

@Autowired
 private  RedisService redisService;


public Result getVerificationCode(String phone){
    String verificationCode = generateVerificationCode();
    tenantInfoRepository.findByPhone(phone).orElseThrow(() -> new BizException(USER_NOT_EXIST));
    try {
        sendMessage(phone, verificationCode);
        String key = RedisPrefixConstant.VERIFICATION_CODE + phone;
        // 验证码存入缓存中
        redisService.set(key, verificationCode, hcmConfig.getLoginVerificationCodeEffectiveTime());
    } catch (ClientException e) {
        log.error("Failed to send message:", e);
        BizException exception = new BizException(MESSAGE_SEND_FAILED);
        exception.initCause(e);
        throw exception;
    }
    return ResultGenerator.genSuccessResult();
}


public String generateVerificationCode(){
    return String.valueOf(new SecureRandom().nextInt(899999) + 100000);
}


public void sendMessage(String phone,String verificationCode){
    IClientProfile profile = DefaultProfile.getProfile(CN_HANGZHOU, hcmConfig.accessKeyId, hcmConfig.accessKeySecret);
    DefaultProfile.addEndpoint(CN_HANGZHOU, CN_HANGZHOU, hcmConfig.product, hcmConfig.domain);
    IAcsClient acsClient = new DefaultAcsClient(profile);
    SendSmsRequest request = new SendSmsRequest();
    request.setMethod(MethodType.POST);
    request.setPhoneNumbers(phone);
    request.setSignName(SystemConstant.SMS_SIGN_NAME);
    request.setTemplateCode(hcmConfig.templateCode);
    request.setTemplateParam("{\"code\":" + verificationCode + "}");
    SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
    log.info("短信接口返回的数据----------------\nCode={}\nMessage={}\nRequestId={}\nBizId={}\n", sendSmsResponse.getCode(), sendSmsResponse.getMessage(), sendSmsResponse.getRequestId(), sendSmsResponse.getBizId());
}


}