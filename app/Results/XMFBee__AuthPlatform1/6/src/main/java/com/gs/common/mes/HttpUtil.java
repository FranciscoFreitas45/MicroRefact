package com.gs.common.mes;
 import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.codec.digest.DigestUtils;
public class HttpUtil {


public String postHuiDiao(String url,String body){
    String result = "";
    try {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection();
        // 设置连接参数
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(20000);
        // 提交数据
        out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
        out.write(body);
        out.flush();
        // 读取返回数据
        in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line = "";
        // 读第一行不加换行符
        boolean firstLine = true;
        while ((line = in.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
            } else {
                result += System.lineSeparator();
            }
            result += line;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}


public String post(String url,String body){
    System.out.println("url:" + System.lineSeparator() + url);
    System.out.println("body:" + System.lineSeparator() + body);
    String result = "";
    try {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection();
        // 设置连接参数
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(20000);
        // 提交数据
        out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
        out.write(body);
        out.flush();
        // 读取返回数据
        in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line = "";
        // 读第一行不加换行符
        boolean firstLine = true;
        while ((line = in.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
            } else {
                result += System.lineSeparator();
            }
            result += line;
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}


public String createCommonParam(){
    // 时间戳
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    String timestamp = sdf.format(new Date());
    // 签名
    String sig = DigestUtils.md5Hex(Config.ACCOUNT_SID + Config.AUTH_TOKEN + timestamp);
    return "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + Config.RESP_DATA_TYPE;
}


}