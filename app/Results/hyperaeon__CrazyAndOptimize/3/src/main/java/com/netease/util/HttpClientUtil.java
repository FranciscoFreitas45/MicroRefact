package com.netease.util;
 import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.imageio.ImageIO;
import org.apache.commons.httpclient.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.tiles.template.GetAsStringModel;
import com.netease.credit.test.SSLClient;
import ch.qos.logback.classic.Logger;
public class HttpClientUtil {

 private  List<Header> headerList;


@SuppressWarnings("unused")
public void setHeader3(HttpPost httpPost){
    httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
    httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
    httpPost.addHeader("Cache-Control", "max-age=0");
    httpPost.addHeader("Connection", "keep-alive");
    httpPost.addHeader("Host", "ipcrs.pbccrc.org.cn");
    httpPost.addHeader("Origin", "https://ipcrs.pbccrc.org.cn");
    httpPost.addHeader("Referer", "https://ipcrs.pbccrc.org.cn/setSafetyLevel.do");
    httpPost.addHeader("Upgrade-Insecure-Requests", "1");
    httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
    // 上传流的请求头
    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
}


public String doPost_2(String url,Map<String,String> map,String cookie){
    HttpClient httpClient = null;
    HttpPost httpPost = null;
    String result = null;
    try {
        httpClient = new SSLClient();
        httpPost = new HttpPost(url);
        // 设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, ConstantUtil.CHARSET);
            httpPost.setEntity(entity);
        }
        // 设置设置Header
        httpPost.addHeader(new BasicHeader("Cookie", cookie));
        setHeader2(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, ConstantUtil.CHARSET);
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return result;
}


public void setHeaders(HttpPost httpPost,Map<String,String> map){
    String method = map.get(ConstantUtil.METHOD);
    // String flag = map.get(ConstantUtil.FLAG);
    for (Header header : headerList) {
        httpPost.addHeader(header.getName(), header.getValue());
    }
    if (method == null) {
        return;
    }
    switch(method) {
        case ConstantUtil.APPLICATION_REPORT:
            httpPost.addHeader(ConstantUtil.REFERER, ConstantUtil.APPLY_REPORT_REFERER);
            break;
        case ConstantUtil.CHECK_ISHAS_REPORT:
            httpPost.addHeader(ConstantUtil.REFERER, ConstantUtil.APPLY_REPORTURL);
            break;
        case "":
            // httpPost.addHeader(ConstantUtil.REFERER, ConstantUtil.REFETCH_QUESTION_URL);
            httpPost.addHeader(ConstantUtil.REFERER, ConstantUtil.QUESTION_BEFORE_GETREPORT);
            // if (ConstantUtil.TRUE.equals(flag)) {
            // httpPost.addHeader(ConstantUtil.REFERER, ConstantUtil.REFETCH_QUESTION_URL);
            // break;
            // }
            // httpPost.addHeader(ConstantUtil.REFERER, ConstantUtil.QUESTION_BEFORE_GETREPORT);
            break;
        case ConstantUtil.VERIFY:
            httpPost.addHeader(ConstantUtil.REFERER, ConstantUtil.QUESTION_BEFORE_GETREPORT);
            break;
        default:
            break;
    }
}


public String doPost_1(String url,Map<String,String> map,String cookie){
    HttpClient httpClient = null;
    HttpPost httpPost = null;
    String result = null;
    try {
        httpClient = new SSLClient();
        httpPost = new HttpPost(url);
        // 设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, ConstantUtil.CHARSET);
            httpPost.setEntity(entity);
        }
        // 设置设置Header
        httpPost.addHeader(new BasicHeader("Cookie", cookie));
        setOtherHeader(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, ConstantUtil.CHARSET);
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return result;
}


@SuppressWarnings("resource")
public String doPost_0(String url,Map<String,String> map,String cookie){
    HttpClient httpClient = null;
    HttpPost httpPost = null;
    String result = null;
    String error = null;
    boolean success = false;
    try {
        httpClient = new SSLClient();
        httpPost = new HttpPost(url);
        // 设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, ConstantUtil.CHARSET);
            httpPost.setEntity(entity);
        }
        // 设置设置Header
        httpPost.addHeader(new BasicHeader("Cookie", cookie));
        setOtherHeader(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, ConstantUtil.CHARSET);
                error = ParsePageUtil.parseLoginResponse(result);
                return error;
            } else {
                return "未知错误";
            }
        } else {
            return "未知错误";
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return error;
}


public String downloadImage_3(String url,Map<String,String> map,String cookie){
    HttpClient httpClient = null;
    HttpPost httpPost = null;
    String result = null;
    try {
        httpClient = new SSLClient();
        httpPost = new HttpPost(url);
        // 设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, ConstantUtil.CHARSET);
            httpPost.setEntity(entity);
        }
        // 设置设置Header
        httpPost.addHeader(new BasicHeader("Cookie", cookie));
        setOtherHeader(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        String picName = "img2//" + ".jpeg";
        InputStream inputStream = null;
        // OutputStream outStream = null;
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            inputStream = resEntity.getContent();
            // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data = readInputStream(inputStream);
            // new一个文件对象用来保存图片，默认保存当前工程根目录
            File imageFile = new File("test_picture.jpg");
            // 创建输出流
            FileOutputStream fileOutputStream = new FileOutputStream(imageFile);
            // 写入数据
            fileOutputStream.write(data);
            // 关闭输出流
            fileOutputStream.close();
        // BufferedImage img = ImageIO.read(inputStream);
        // 
        // 
        // System.out.println("image is " +img);
        // if(resEntity != null){
        // result = EntityUtils.toString(resEntity,ConstantUtil.CHARSET);
        // }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return result;
}


public void setHeader2(HttpPost httpPost){
    httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    httpPost.addHeader("Accept-Encoding", "gzip, deflate");
    httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
    httpPost.addHeader("Cache-Control", "max-age=0");
    httpPost.addHeader("Connection", "keep-alive");
    httpPost.addHeader("Host", "ipcrs.pbccrc.org.cn");
    httpPost.addHeader("Origin", "https://ipcrs.pbccrc.org.cn");
    httpPost.addHeader("Referer", "https://ipcrs.pbccrc.org.cn/setSafetyLevel.do");
    httpPost.addHeader("Upgrade-Insecure-Requests", "1");
    httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
    // 上传流的请求头
    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
}


public void setAjaxHeader(HttpPost httpPost,String cookie){
    httpPost.addHeader("Accept", "text/plain, */*; q=0.01");
    httpPost.addHeader("Accept-Encoding", "gzip, deflate");
    httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
    httpPost.addHeader("Connection", "keep-alive");
    httpPost.addHeader("Cookie", cookie);
    httpPost.addHeader("Host", "ipcrs.pbccrc.org.cn");
    httpPost.addHeader("Origin", "https://ipcrs.pbccrc.org.cn");
    httpPost.addHeader("Referer", "https://ipcrs.pbccrc.org.cn/userReg.do");
    httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
    // 上传流的请求头
    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; ConstantUtil.CHARSET=UTF-8");
    // ajax头
    httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
}


public void setOtherHeader(HttpPost httpPost){
    httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    httpPost.addHeader("Accept-Encoding", "gzip, deflate");
    httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
    httpPost.addHeader("Cache-Control", "max-age=0");
    httpPost.addHeader("Connection", "keep-alive");
    httpPost.addHeader("Host", "ipcrs.pbccrc.org.cn");
    httpPost.addHeader("Origin", "https://ipcrs.pbccrc.org.cn");
    httpPost.addHeader("Referer", "https://ipcrs.pbccrc.org.cn/page/login/loginreg.jsp");
    httpPost.addHeader("Upgrade-Insecure-Requests", "1");
    httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
    // 上传流的请求头
    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
}


public String doPost(String url,Map<String,String> map,String cookie){
    HttpClient httpClient = null;
    HttpPost httpPost = null;
    String result = null;
    try {
        httpClient = new SSLClient();
        httpPost = new HttpPost(url);
        // 设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, ConstantUtil.CHARSET);
            httpPost.setEntity(entity);
        }
        // 设置设置Header
        httpPost.addHeader(new BasicHeader("Cookie", cookie));
        setOtherHeader(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, ConstantUtil.CHARSET);
            // if (response.getStatusLine().getStatusCode()==302) {
            // System.out.println("302：网页暂时移除，需要发送请求到另一个url");
            // String locationUrl = response.getLastHeader("Location").getValue();
            // System.out.println("新地址："+locationUrl);
            // httpPost=new HttpPost(locationUrl);
            // if(list.size() > 0){
            // UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,ConstantUtil.CHARSET);
            // httpPost.setEntity(entity);
            // }
            // // 设置设置Header
            // httpPost.addHeader(new BasicHeader("Cookie", cookie));
            // setOtherHeader(httpPost);
            // response = httpClient.execute(httpPost);
            // System.out.println("重试结果："+EntityUtils.toString(response.getEntity(), ConstantUtil.CHARSET));
            // 
            // }
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return result;
}


public String doAjaxPost(String url,Map<String,String> map,String cookie){
    HttpClient httpClient = null;
    HttpPost httpPost = null;
    String result = null;
    try {
        httpClient = new SSLClient();
        httpPost = new HttpPost(url);
        // HttpHost proxy = new HttpHost("127.0.0.1", 8888);
        // httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, proxy);
        // 设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, ConstantUtil.CHARSET);
            httpPost.setEntity(entity);
        }
        // 设置设置Header
        setAjaxHeader(httpPost, cookie);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, ConstantUtil.CHARSET);
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return result;
}


public String doPostRequest(String url,Map<String,String> map,String cookie){
    HttpClient httpClient = null;
    HttpPost httpPost = null;
    String result = null;
    try {
        httpClient = new SSLClient();
        httpPost = new HttpPost(url);
        // map.remove(ConstantUtil.FLAG);//移除不必要的请求参数
        // 设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, ConstantUtil.CHARSET);
            httpPost.setEntity(entity);
        }
        // 设置设置Header
        httpPost.addHeader(new BasicHeader("Cookie", cookie));
        setHeaders(httpPost, map);
        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, ConstantUtil.CHARSET);
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return result;
}


public byte[] readInputStream(InputStream inStream){
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    // 创建一个Buffer字符串
    byte[] buffer = new byte[1024];
    // 每次读取的字符串长度，如果为-1，代表全部读取完毕
    int len = 0;
    // 使用一个输入流从buffer里把数据读取出来
    while ((len = inStream.read(buffer)) != -1) {
        // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
        outStream.write(buffer, 0, len);
    }
    // 关闭输入流
    inStream.close();
    // 把outStream里的数据写入内存
    return outStream.toByteArray();
}


public void setOtherGetHeader(HttpGet httpPost){
    httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
    httpPost.addHeader("Accept-Encoding", "gzip, deflate");
    httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8");
    httpPost.addHeader("Cache-Control", "max-age=0");
    httpPost.addHeader("Connection", "keep-alive");
    httpPost.addHeader("Host", "ipcrs.pbccrc.org.cn");
    httpPost.addHeader("Origin", "https://ipcrs.pbccrc.org.cn");
    httpPost.addHeader("Referer", "https://ipcrs.pbccrc.org.cn/page/login/loginreg.jsp");
    httpPost.addHeader("Upgrade-Insecure-Requests", "1");
    httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36");
    // 上传流的请求头
    httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
}


@SuppressWarnings("unchecked")
public BufferedImage downloadImage(String url,Map<String,String> map,String cookie){
    HttpClient httpClient = null;
    HttpPost httpPost = null;
    BufferedImage bi = null;
    try {
        httpClient = new SSLClient();
        httpPost = new HttpPost(url);
        // 设置参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, String> elem = (Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
        }
        if (list.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, ConstantUtil.CHARSET);
            httpPost.setEntity(entity);
        }
        // 设置设置Header
        httpPost.addHeader(new BasicHeader("Cookie", cookie));
        setOtherHeader(httpPost);
        HttpResponse response = httpClient.execute(httpPost);
        InputStream inputStream = null;
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            inputStream = resEntity.getContent();
            bi = ImageIO.read(inputStream);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return bi;
}


public String doGet(String url,String cookie){
    HttpClient httpClient = null;
    HttpGet httpGet = new HttpGet(url);
    String result = null;
    try {
        httpClient = new SSLClient();
        // 设置设置Header
        httpGet.addHeader(new BasicHeader("Cookie", cookie));
        setOtherGetHeader(httpGet);
        HttpResponse response = httpClient.execute(httpGet);
        if (response != null) {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                result = EntityUtils.toString(resEntity, ConstantUtil.CHARSET);
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return result;
}


}