package com.qidian.hcm.common.config;
 import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
@Configuration
@Getter
public class HCMConfig {

@Value("${hcm.config.tenant-url:http://127.0.0.1/}")
 public  String tenantUrl;

@Value("${hcm.aliyun-oss.endpoint}")
 public  String aliyunOssEndpoint;

@Value("${hcm.aliyun-oss.endpoint-url}")
 public  String aliyunOssEndpointUrl;

@Value("${hcm.aliyun-oss.access-key-id}")
 public  String aliyunOssAccessKeyId;

@Value("${hcm.aliyun-oss.access-key-secret}")
 public  String aliyunOssAccessKeySecret;

@Value("${hcm.aliyun-oss.bucket-name}")
 public  String aliyunOssBucketName;

@Value("${hcm.aliyun-oss.max-attachment-size}")
 public  String aliyunOssMaxAttSize;

@Value("${hcm.aliyun-oss.temp-url-expire}")
 public  Long aliyunOssTempUrlExpire;

@Value("${hcm.snow-flake.datacenter-id}")
 public  Long snowFlakeDatacenterId;

@Value("${hcm.snow-flake.machine-id}")
 public  Long snowFlakeMachineId;

@Value("${hcm.mysql-conn-params}")
 public  String mysqlConnParams;

@Value("${hcm.login-verification-code-effective-time}")
 public  Long loginVerificationCodeEffectiveTime;

@Value("${hcm.aliyun-sms.sms-product}")
 public  String product;

@Value("${hcm.aliyun-sms.sms-domain}")
 public  String domain;

@Value("${hcm.aliyun-sms.accesskey-id}")
 public  String accessKeyId;

@Value("${hcm.aliyun-sms.accesskey-secret}")
 public  String accessKeySecret;

@Value("${hcm.aliyun-sms.template-code}")
 public  String templateCode;


}