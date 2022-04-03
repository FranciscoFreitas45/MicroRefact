package com.qidian.hcm.module.center.service;
 import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.qidian.hcm.common.config.HCMConfig;
import com.qidian.hcm.common.constants.RedisPrefixConstant;
import com.qidian.hcm.common.constants.SystemConstant;
import com.qidian.hcm.common.exception.BizException;
import com.qidian.hcm.common.utils.IDGeneratorUtil;
import com.qidian.hcm.common.utils.ResultCode;
import com.qidian.hcm.module.employee.entity.Attachment;
import com.qidian.hcm.module.employee.repository.AttachmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
@Service
@Slf4j
public class FileService {

@Autowired
 private  HCMConfig hcmConfig;

@Autowired
 private  AttachmentRepository attachmentRepository;

@Autowired
 private  IDGeneratorUtil idGeneratorUtil;

@Autowired
 private  StringRedisTemplate stringRedisTemplate;


public String getThumbTempUrl(Long fileId){
    if (SystemConstant.AVATAR_IMG_WIDTH <= 0) {
        return getTempUrl(fileId);
    }
    String key = RedisPrefixConstant.FILE_TEMP_URL + SystemConstant.AVATAR_IMG_WIDTH + ":" + fileId;
    String tempUrl = stringRedisTemplate.opsForValue().get(key);
    if (!StringUtils.isEmpty(tempUrl)) {
        return tempUrl;
    }
    Optional<Attachment> attachmentOptional = attachmentRepository.findByFileId(fileId);
    if (!attachmentOptional.isPresent()) {
        return "";
    }
    Attachment attachment = attachmentOptional.get();
    OSSClient ossClient = new OSSClient(hcmConfig.getAliyunOssEndpoint(), hcmConfig.getAliyunOssAccessKeyId(), hcmConfig.getAliyunOssAccessKeySecret());
    GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(hcmConfig.getAliyunOssBucketName(), attachment.getFileNameOnOss());
    String sizeParams = ",w_" + (int) SystemConstant.AVATAR_IMG_WIDTH;
    request.addQueryParameter("x-oss-process", "image/resize" + sizeParams);
    request.setExpiration(new Date(System.currentTimeMillis() + hcmConfig.getAliyunOssTempUrlExpire()));
    URL url = ossClient.generatePresignedUrl(request);
    // 关闭OSSClient
    ossClient.shutdown();
    tempUrl = url.getProtocol() + "://" + url.getHost() + url.getFile();
    stringRedisTemplate.opsForValue().set(key, tempUrl, hcmConfig.getAliyunOssTempUrlExpire(), TimeUnit.MILLISECONDS);
    return tempUrl;
}


public Attachment uploadFileToOSS(String base64Str,String fileName){
    Attachment attachmentEntity = new Attachment();
    Long fileId;
    if (base64Str.length() > Integer.parseInt(hcmConfig.getAliyunOssMaxAttSize())) {
        throw new BizException(ResultCode.FILE_SIZE_LIMIT);
    }
    String suffix = "";
    if (fileName.contains(".")) {
        suffix = fileName.substring(fileName.lastIndexOf('.'));
    }
    fileId = idGeneratorUtil.getNextId();
    String fileNameOnOss = fileId + suffix;
    upload(base64Str, fileNameOnOss);
    attachmentEntity.setOriginName(fileName);
    attachmentEntity.setFileNameOnOss(fileNameOnOss);
    attachmentEntity.setFileId(fileId);
    attachmentRepository.save(attachmentEntity);
    return attachmentEntity;
}


public void upload(String base64Str,String originFileName){
    // 创建OSSClient实例
    OSSClient ossClient = new OSSClient(hcmConfig.getAliyunOssEndpoint(), hcmConfig.getAliyunOssAccessKeyId(), hcmConfig.getAliyunOssAccessKeySecret());
    // 关闭OSSClient
    ossClient.putObject(hcmConfig.getAliyunOssBucketName(), originFileName, new ByteArrayInputStream(Base64.decodeBase64(base64Str)));
    ossClient.shutdown();
}


public String getAvatarImgUrl(Long fileId){
    return getThumbTempUrl(fileId);
}


public String getTempUrl(Long fileId){
    String key = RedisPrefixConstant.FILE_TEMP_URL + fileId;
    String tempUrl = stringRedisTemplate.opsForValue().get(key);
    if (!StringUtils.isEmpty(tempUrl)) {
        return tempUrl;
    }
    Optional<Attachment> attachmentOptional = attachmentRepository.findByFileId(fileId);
    if (!attachmentOptional.isPresent()) {
        return "";
    }
    Attachment attachment = attachmentOptional.get();
    OSSClient ossClient = new OSSClient(hcmConfig.getAliyunOssEndpoint(), hcmConfig.getAliyunOssAccessKeyId(), hcmConfig.getAliyunOssAccessKeySecret());
    // 设置URL过期时间
    Date expiration = new Date(System.currentTimeMillis() + hcmConfig.getAliyunOssTempUrlExpire());
    URL url = ossClient.generatePresignedUrl(hcmConfig.getAliyunOssBucketName(), attachment.getFileNameOnOss(), expiration);
    // 关闭OSSClient
    ossClient.shutdown();
    tempUrl = url.getProtocol() + "://" + url.getHost() + url.getFile();
    stringRedisTemplate.opsForValue().set(key, tempUrl, hcmConfig.getAliyunOssTempUrlExpire(), TimeUnit.MILLISECONDS);
    return tempUrl;
}


}