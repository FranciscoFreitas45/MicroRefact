import org.springframework.boot.context.properties.ConfigurationProperties;
@ConfigurationProperties(prefix = "qiniu")
public class QiniuProperties {

 private  String url;

 private  String accessKey;

 private  String secretKey;

 private  String bucket;

 private  long expireSeconds;


public long getExpireSeconds(){
    return expireSeconds;
}


public String getUrl(){
    return url;
}


public String getSecretKey(){
    return secretKey;
}


public void setSecretKey(String secretKey){
    this.secretKey = secretKey;
}


public String getBucket(){
    return bucket;
}


public void setAccessKey(String accessKey){
    this.accessKey = accessKey;
}


public String getAccessKey(){
    return accessKey;
}


public void setBucket(String bucket){
    this.bucket = bucket;
}


public void setExpireSeconds(long expireSeconds){
    this.expireSeconds = expireSeconds;
}


public void setUrl(String url){
    this.url = url;
}


}