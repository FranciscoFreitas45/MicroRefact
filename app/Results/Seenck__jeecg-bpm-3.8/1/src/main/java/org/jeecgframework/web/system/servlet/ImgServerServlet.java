package org.jeecgframework.web.system.servlet;
 import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecgframework.core.util.ResourceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ImgServerServlet extends HttpServlet{

 private  Logger log;

 private  long serialVersionUID;

 private  String WEB_UPLOAD_BASE_PATH;

 private  String ONLINE_TEMPLATE_BASE_PATH;


public void showPic_OnlineTemplate(String url,HttpServletResponse response){
    String imgPath = url.substring(url.indexOf(ONLINE_TEMPLATE_BASE_PATH), url.length()).replace(ONLINE_TEMPLATE_BASE_PATH, "");
    // URL特殊字符转义
    imgPath = URLDecoder.decode(imgPath, "utf-8");
    response.setContentType("image/jpeg;charset=utf-8");
    InputStream inputStream = null;
    OutputStream outputStream = null;
    try {
        String imgurl = getUploadBasePath() + File.separator + imgPath;
        inputStream = new BufferedInputStream(new FileInputStream(imgurl));
        outputStream = response.getOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) > 0) {
            outputStream.write(buf, 0, len);
        }
        response.flushBuffer();
    } catch (Exception e) {
        log.error("--通过流的方式获取文件异常--" + e.getMessage());
    } finally {
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }
    log.debug("进入了servlet");
}


public void showPic_WebUpload(String url,HttpServletRequest request,HttpServletResponse response){
    String imgPath = url.substring(url.indexOf(WEB_UPLOAD_BASE_PATH), url.length()).replace(WEB_UPLOAD_BASE_PATH, "");
    // URL特殊字符转义
    imgPath = URLDecoder.decode(imgPath, "utf-8");
    // 去掉路径结尾逗号
    if (imgPath.endsWith(",")) {
        imgPath = imgPath.substring(0, imgPath.length() - 1);
    } else if (imgPath.indexOf(",?") != -1) {
        imgPath = imgPath.replace(",?", "?");
    }
    // 是否下载否则展示图片
    String down = request.getParameter("down");
    if ("true".equals(down)) {
        String splitKey = "/";
        if (imgPath.lastIndexOf("\\") > imgPath.lastIndexOf("/")) {
            splitKey = "\\";
        }
        String fileName = imgPath.substring(imgPath.lastIndexOf(splitKey) + 1);
        String userAgent = request.getHeader("user-agent").toLowerCase();
        if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
        } else {
            fileName = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
        }
        response.setContentType("application/x-msdownload;charset=utf-8");
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
    } else {
        response.setContentType("image/jpeg;charset=utf-8");
    }
    InputStream inputStream = null;
    OutputStream outputStream = null;
    try {
        String localPath = ResourceUtil.getConfigByName("webUploadpath");
        String imgurl = localPath + File.separator + imgPath;
        inputStream = new BufferedInputStream(new FileInputStream(imgurl));
        outputStream = response.getOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = inputStream.read(buf)) > 0) {
            outputStream.write(buf, 0, len);
        }
        response.flushBuffer();
    } catch (Exception e) {
        log.error("--通过流的方式获取文件异常--" + e.getMessage());
    } finally {
        if (inputStream != null) {
            inputStream.close();
        }
        if (outputStream != null) {
            outputStream.close();
        }
    }
    log.debug("进入了servlet");
}


@Override
public long getLastModified(HttpServletRequest request){
    log.debug("--------getLastModified---------");
    /*将资源的路径从工程的路径转换到真实的路径
        String url = request.getRequestURI();
        String imgPath = request.getRequestURI().substring(url.indexOf(WEB_UPLOAD_BASE_PATH),url.length()).replace(WEB_UPLOAD_BASE_PATH,"");
        String localPath = ResourceUtil.getConfigByName("webUploadpath");
		 String imgurl = localPath + File.separator + imgPath;
//		 System.out.println(imgurl);
//      String path = this.getServletContext().getRealPath(imgurl);
        
        File file = new File(imgurl);
        System.out.println(file.lastModified());
        return file.lastModified();*/
    return Long.valueOf("1495182508301");
}


public String getUploadBasePath(){
    ClassLoader classLoader = this.getClass().getClassLoader();
    URL resource = classLoader.getResource("sysConfig.properties");
    String path = resource.getPath();
    path = path.substring(0, path.indexOf("sysConfig.properties")) + "online/template";
    // 解决tomcat安装路径包含空格的问题
    path = path.replaceAll("%20", " ");
    return path;
}


@Override
public void doPost(HttpServletRequest request,HttpServletResponse response){
    String url = request.getRequestURI();
    if (url.indexOf(ONLINE_TEMPLATE_BASE_PATH) != -1) {
        this.showPic_OnlineTemplate(url, response);
    } else if (url.indexOf(WEB_UPLOAD_BASE_PATH) != -1) {
        this.showPic_WebUpload(url, request, response);
    }
}


@Override
public void doGet(HttpServletRequest request,HttpServletResponse response){
    this.doPost(request, response);
}


}