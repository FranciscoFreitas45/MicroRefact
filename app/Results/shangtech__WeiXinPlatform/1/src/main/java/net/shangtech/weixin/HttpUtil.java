package net.shangtech.weixin;
 import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLContext;
import net.shangtech.weixin.sys.entity.SysUser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import net.shangtech.DTO.SysUser;
public class HttpUtil {

 private  Logger logger;

 private  Map<Integer,Map<String,Long>> ACCESS_TOKEN_MAP;


public String streamToString(InputStream is){
    StringBuffer sb = new StringBuffer();
    try {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        String line = "";
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        is.close();
    } catch (Exception e) {
        logger.error("读取数据流出现错误:" + e.getMessage());
        e.printStackTrace();
    }
    return sb.toString();
}


public String post(HttpClient client,HttpPost post){
    HttpResponse response = client.execute(post);
    HttpEntity entity = response.getEntity();
    if (entity != null) {
        return streamToString(entity.getContent());
    }
    return null;
}


public String get(HttpClient client,String url){
    HttpGet get = new HttpGet(url);
    HttpResponse response = client.execute(get);
    HttpEntity entity = response.getEntity();
    if (entity != null) {
        return streamToString(entity.getContent());
    }
    return null;
}


public CloseableHttpClient createSSLInsecureClient(){
    try {
        SSLContext context = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

            @Override
            public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                return true;
            }
        }).build();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(context);
        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    } catch (Exception e) {
        logger.error("创建client失败:" + e.getMessage());
        e.printStackTrace();
    }
    return null;
}


@Override
public boolean isTrusted(X509Certificate[] chain,String authType){
    return true;
}


public String getAccessToken(HttpClient client,SysUser user){
    Map<String, Long> map = ACCESS_TOKEN_MAP.get(user.getId());
    if (map != null && !map.isEmpty()) {
        Entry<String, Long> entry = map.entrySet().iterator().next();
        long now = System.currentTimeMillis();
        if (now - entry.getValue() < 7000000) {
            return entry.getKey();
        }
    }
    String url = Config.URL_GET_ACCESS_TOKEN + "appid=" + user.getAppid() + "&secret=" + user.getAppkey();
    String accessToken = null;
    try {
        String resp = HttpUtil.get(client, url);
        JSONObject result = JSON.parseObject(resp);
        if (result.get("errcode") != null) {
            logger.error("获取access_token失败,错误代码:" + result.get("errcode"));
        } else if (result.get("access_token") != null) {
            logger.info("获取access_token成功:" + result.getString("access_token"));
            accessToken = result.getString("access_token");
        } else {
            logger.error("获取access_token失败");
        }
    } catch (Exception e) {
        logger.error("获取access_token失败,请查看网络连接是否正常");
        e.printStackTrace();
    }
    map = new HashMap<String, Long>();
    map.put(accessToken, System.currentTimeMillis());
    return accessToken;
}


}