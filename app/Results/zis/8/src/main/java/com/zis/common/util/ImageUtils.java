package com.zis.common.util;
 import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.commons.lang3.StringUtils;
public class ImageUtils {


public void downloadImg(String urlString,String savePath,String filename){
    if (StringUtils.isBlank(urlString)) {
        throw new IllegalArgumentException("urlString不能为空");
    }
    if (StringUtils.isBlank(savePath)) {
        throw new IllegalArgumentException("savePath不能为空");
    }
    if (StringUtils.isBlank(filename)) {
        throw new IllegalArgumentException("filename不能为空");
    }
    // 构造URL
    URL url = new URL(urlString);
    // 打开连接
    URLConnection con = url.openConnection();
    // 设置请求超时为5s
    con.setConnectTimeout(5 * 1000);
    // 输入流
    InputStream is = con.getInputStream();
    // 1K的数据缓冲
    byte[] bs = new byte[1024];
    // 读取到的数据长度
    int len;
    // 输出的文件流
    File sf = new File(savePath);
    if (!sf.exists()) {
        sf.mkdirs();
    }
    OutputStream os = new FileOutputStream(sf.getPath() + "/" + filename);
    // 开始读取
    while ((len = is.read(bs)) != -1) {
        os.write(bs, 0, len);
    }
    // 完毕，关闭所有链接
    os.close();
    is.close();
}


}