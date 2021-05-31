import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class HttpClientUtil {

 private  Logger logger;


public String post(String url,Map<String,String> paramMap){
    logger.info("HTTP POST请求URL{};请求参数：{}", url, paramMap.toString());
    String result = "";
    CloseableHttpClient httpClient = HttpClients.createDefault();
    try {
        HttpPost httppost = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        httppost.setEntity(new UrlEncodedFormEntity(nvps));
        CloseableHttpResponse response = httpClient.execute(httppost);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            if (HttpStatus.SC_OK == statusCode || HttpStatus.SC_CREATED == statusCode || HttpStatus.SC_ACCEPTED == statusCode) {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    result = EntityUtils.toString(responseEntity, "UTF-8");
                    logger.info("HTTP POST请求结果:{}", result);
                }
            }
        } finally {
            response.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("http请求错误", e);
    } finally {
        // 关闭连接,释放资源
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return result;
}


public byte[] postByteArray(String url,String param){
    logger.info("HTTP GET请求URL{}", url);
    byte[] result = null;
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpPost httppost = new HttpPost(url);
    StringEntity entity = new StringEntity(param, "UTF-8");
    httppost.setEntity(entity);
    try {
        CloseableHttpResponse response = httpClient.execute(httppost);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("HTTP POST请求状态:{}", statusCode);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toByteArray(responseEntity);
            // logger.info("HTTP GET请求结果:{}",result);
            }
        } finally {
            response.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("http请求错误", e);
    } finally {
        // 关闭连接,释放资源
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return result;
}


public String get(String url){
    logger.info("HTTP GET请求URL{}", url);
    String result = "";
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet(url);
    try {
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try {
            int statusCode = response.getStatusLine().getStatusCode();
            logger.info("HTTP POST请求状态:{}", statusCode);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity, "UTF-8");
                logger.info("HTTP GET请求结果:{}", result);
            }
        } finally {
            response.close();
        }
    } catch (Exception e) {
        e.printStackTrace();
        logger.error("http请求错误", e);
    } finally {
        // 关闭连接,释放资源
        try {
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    return result;
}


}