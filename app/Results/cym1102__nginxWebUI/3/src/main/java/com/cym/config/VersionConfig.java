package com.cym.config;
 import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import com.cym.model.Version;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
@Configuration
public class VersionConfig {

 private Version version;


public Version getVersion(){
    return version;
}


public void setVersion(Version version){
    this.version = version;
}


@PostConstruct
public void getNewVersion(){
    try {
        String json = HttpUtil.get("https://www.nginxwebui.cn/download/version.json", 1000);
        if (StrUtil.isNotEmpty(json)) {
            version = JSONUtil.toBean(json, Version.class);
        }
    } catch (Exception e) {
        System.err.println(e.getMessage());
    }
}


}