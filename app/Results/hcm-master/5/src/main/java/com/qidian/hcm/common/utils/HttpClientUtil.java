package com.qidian.hcm.common.utils;
 import com.alibaba.fastjson.JSON;
import com.qidian.hcm.common.constants.SystemConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.StringUtils;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
@Slf4j
public class HttpClientUtil {

 public  String MSG;

private HttpClientUtil() {
}
public String doPostObject(String url,Object obj,String token){
    try (CloseableHttpClient httpClient = HttpClients.createSystem()) {
        HttpPost request = new HttpPost(url);
        request.addHeader("content-type", "application/json");
        request.addHeader("Accept", "application/json");
        if (!StringUtils.isEmpty(token)) {
            request.addHeader(SystemConstant.TOKEN, token);
        }
        if (null != obj) {
            String str = JSON.toJSONString(obj);
            StringEntity params = new StringEntity(str, StandardCharsets.UTF_8.name());
            request.setEntity(params);
        }
        HttpResponse response = httpClient.execute(request);
        if (response != null) {
            String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8.name());
            log.info("返回json：{} ", responseBody);
            return responseBody;
        }
    } catch (IOException ex) {
        log.error(MSG, ex);
    }
    return null;
}


}