package com.zis.bookinfo.util;
 import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.log4j.Logger;
public class AbstractNetCapture {

 protected  int errCount;

 protected  Logger logger;

 protected  String DEFAULT_CHARSET;


public String innerGetUrlContent(String url,String charset){
    try {
        URL urlObj = new URL(url);
        HttpURLConnection httpConn = (HttpURLConnection) urlObj.openConnection();
        httpConn.setConnectTimeout(3000);
        InputStreamReader input = new InputStreamReader(httpConn.getInputStream(), charset);
        BufferedReader bufReader = new BufferedReader(input);
        String line = "";
        StringBuilder contentBuf = new StringBuilder();
        while ((line = bufReader.readLine()) != null) {
            contentBuf.append(line);
        }
        return contentBuf.toString();
    } catch (Exception e) {
        logger.error("解析url栏异常", e);
        throw new RuntimeException(e);
    }
}


public String getUrlContent(String url,String charset){
    try {
        return innerGetUrlContent(url, charset);
    } catch (Exception e) {
        e.printStackTrace();
        errCount++;
        if (errCount <= 3) {
            System.out.println("retry times: " + errCount);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                logger.error("线程阻塞", e);
            }
            return innerGetUrlContent(url, charset);
        } else {
            errCount = 0;
            throw new RuntimeException(e);
        }
    }
}


public String parseInfo(String id,String buf,String prefix,String mid,String suffix){
    int nameStartIndex = buf.indexOf(mid, buf.indexOf(prefix));
    int nameEndIndex = buf.indexOf(suffix, nameStartIndex + mid.length());
    if (nameStartIndex == -1 || nameEndIndex == -1) {
        throw new RuntimeException("查询失败" + id);
    }
    return buf.substring(nameStartIndex + mid.length(), nameEndIndex);
}


public BookMetadata getBookInfo(String id)


}