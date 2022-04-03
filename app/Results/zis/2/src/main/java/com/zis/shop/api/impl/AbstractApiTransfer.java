package com.zis.shop.api.impl;
 import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.lang3.StringUtils;
import com.zis.common.util.ZisUtils;
import com.zis.shop.api.ApiTransfer;
import com.zis.shop.dto.ApiAddItemDto;
public class AbstractApiTransfer implements ApiTransfer{


public String clearString(String str){
    String newStr = str.replace("&", " ").replace("=", " ");
    return newStr;
}


public String buildDesc(ApiAddItemDto dto){
    StringBuilder sb = new StringBuilder();
    sb.append("<b> 详情 <b/><p/><p/><p/>");
    sb.append("图书名称:" + dto.getBookName() + "<p/>");
    sb.append("ISBN:" + dto.getIsbn() + "<p/>");
    sb.append("作者:" + dto.getBookAuthor() + "<p/>");
    sb.append("出版时间:" + ZisUtils.getDateString("yyyy年MM月", dto.getPublishDate()) + "<p/>");
    sb.append("版次:" + dto.getBookEdition() + "<p/>");
    sb.append("出版社:" + dto.getBookPublisher() + "<p/>");
    sb.append("原价:" + dto.getBookPrice() + "<p/>");
    sb.append("<b> 概要 <b/><p/><p/><p/>");
    sb.append(dto.getSummary());
    sb.append("<p/><b> 目录 <b/><p/><p/><p/>");
    sb.append(dto.getCatalog());
    String str = clearString(sb.toString());
    return str;
}


public InputStream getInputStream(String urlStr){
    // 如果为空获取
    if (StringUtils.isBlank(urlStr)) {
        urlStr = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=" + "1486234209743&di=aa7f46550856899c55ced8b0c7af05ce&imgtype=0&src=http%3A%2F%2Fpages.haozu.ajkcdn." + "com%2F20111117%2Fimg%2Fprop%2Fnopic_385x240.gif";
    }
    InputStream is = null;
    try {
        // 构造URL
        URL url = new URL(urlStr);
        // 打开连接
        URLConnection con = url.openConnection();
        // 设置请求超时为5s
        con.setConnectTimeout(5 * 1000);
        // 输入流
        is = con.getInputStream();
        if (is == null) {
            getInputStream("");
        }
    // 转换为byte 数组
    } catch (Exception e) {
        e.printStackTrace();
    }
    return is;
}


}