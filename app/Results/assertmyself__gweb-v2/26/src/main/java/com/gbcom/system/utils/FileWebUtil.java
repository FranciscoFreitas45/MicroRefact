package com.gbcom.system.utils;
 import com.hc.core.utils.StringHelper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.util.Base64;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
public class FileWebUtil {


public boolean copyFile(String sourceFileName,String destFileName){
    File sourceFile = new File(sourceFileName);
    File destFile = new File(destFileName);
    FileInputStream fileInputStream = new FileInputStream(sourceFile);
    FileOutputStream fileOutputStream = new FileOutputStream(destFile);
    int i = IOUtils.copy(fileInputStream, fileOutputStream);
    fileOutputStream.close();
    fileInputStream.close();
    return i > 0;
}


public String toUtf8String(String s){
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        if (c >= 0 && c <= 255) {
            sb.append(c);
        } else {
            byte[] b;
            try {
                b = Character.toString(c).getBytes("utf-8");
            } catch (Exception ex) {
                System.out.println(ex);
                b = new byte[0];
            }
            for (int j = 0; j < b.length; j++) {
                int k = b[j];
                if (k < 0) {
                    k += 256;
                }
                sb.append("%" + Integer.toHexString(k).toUpperCase());
            }
        }
    }
    return sb.toString();
}


public void downloadSection(HttpServletRequest request,HttpServletResponse response,String filePath,String originalFileName,Boolean isOnLine){
    File file = new File(filePath);
    if (!file.exists()) {
        response.sendError(404, "File not found!");
        return;
    }
    String fileName = file.getName();
    if (StringUtils.isNotBlank(originalFileName)) {
        fileName = originalFileName;
    }
    // 判断不同浏览器分别设置header
    setDownloadContentType(request, response, fileName, null, false);
    // response.reset();
    // response.setCharacterEncoding("UTF-8");
    response.setHeader("Server", "gbcom");
    // 告诉客户端允许断点续传多线程连接下载
    // 响应的格式是: Accept-Ranges: bytes
    response.setHeader("Accept-Ranges", "bytes");
    InputStream fis = new FileInputStream(file);
    OutputStream fos = response.getOutputStream();
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    response.setContentLength((int) file.length());
    long p = 0;
    long l = file.length();
    // 如果是第一次下,还没有断点续传,状态是默认的 200,无需显式设置
    // 响应的格式是:
    // HTTP/1.1 200 OK
    // 客户端请求的下载的文件块的开始字节
    if (request.getHeader("Range") != null) {
        // 如果是下载文件的范围而不是全部,向客户端声明支持并开始文件块下载
        // 要设置状态
        // 响应的格式是:
        // HTTP/1.1 206 Partial Content
        // 206
        response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
        // 从请求中得到开始的字节
        // 请求的格式是:
        // Range: bytes=[文件块的开始字节]-
        p = Long.parseLong(request.getHeader("Range").replaceAll("bytes=", "").replaceAll("-", ""));
    }
    // 下载的文件(或块)长度
    // 响应的格式是:
    // Content-Length: [文件的总大小] - [客户端请求的下载的文件块的开始字节]
    response.setHeader("Content-Length", new Long(l - p).toString());
    if (p != 0) {
        // 不是从最开始下载,
        // 响应的格式是:
        // Content-Range: bytes [文件块的开始字节]-[文件的总大小 - 1]/[文件的总大小]
        response.setHeader("Content-Range", "bytes " + new Long(p).toString() + "-" + new Long(l - 1).toString() + "/" + new Long(l).toString());
    }
    fis.skip(p);
    try {
        int i;
        while ((i = fis.read()) != -1) {
            bos.write(i);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        bos.flush();
        fis.close();
        fos.close();
        bos.close();
    }
}


@SuppressWarnings("unchecked")
public void extractZipFile(String zipPath,String dstPath){
    File dstDir = new File(dstPath);
    if (!dstDir.exists() && !dstDir.isDirectory()) {
        if (!dstDir.mkdirs()) {
            System.err.println("目标文件夹" + dstPath + "创建失败！");
        }
    }
    ZipFile zipFile = new ZipFile(zipPath);
    Enumeration<ZipEntry> zipEntryEnums = (Enumeration<ZipEntry>) zipFile.entries();
    while (zipEntryEnums.hasMoreElements()) {
        ZipEntry zipEntry = zipEntryEnums.nextElement();
        File dstFile = new File(dstPath + File.separator + zipEntry.getName());
        if (zipEntry.isDirectory()) {
            if (!dstFile.mkdir()) {
                System.err.println("解压缩文件中的目录" + dstFile.getPath() + "创建失败！");
            }
        } else {
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, dstFile);
        }
    }
}


public void removeDir(File d){
    String[] list = d.list();
    if (list == null) {
        list = new String[0];
    }
    for (int i = 0; i < list.length; i++) {
        String s = list[i];
        File f = new File(d, s);
        if (f.isDirectory()) {
            removeDir(f);
        } else {
            if (!f.delete()) {
                System.err.println("Unable to delete file " + f.getAbsolutePath());
            }
        }
    }
    if (!d.delete()) {
        System.err.println("Unable to delete directory " + d.getAbsolutePath());
    }
}


public String getFileWithoutExt(String fileName){
    String value = "";
    int start = 0;
    int end = 0;
    if (fileName == null) {
        return null;
    }
    if (fileName.lastIndexOf('.') > 0) {
        end = fileName.lastIndexOf('.');
    } else {
        end = fileName.length();
    }
    start = 0;
    value = fileName.substring(start, end);
    return value;
}


public boolean moveFile(String sourceFileName,String destFileName){
    boolean ret = copyFile(sourceFileName, destFileName);
    if (ret) {
        File sourceFile = new File(sourceFileName);
        sourceFile.deleteOnExit();
    }
    return ret;
}


public void setDownloadContentType(HttpServletRequest request,HttpServletResponse response,String fileName,String encoding,Boolean isDownloadSection){
    if (StringUtils.isBlank(encoding)) {
        encoding = "UTF-8";
    }
    response.reset();
    response.setCharacterEncoding(encoding);
    // 文件名过长需要截取，否则部分浏览器不支持
    if (fileName.indexOf(".") > 0) {
        String noExt = fileName.substring(0, fileName.indexOf("."));
        if (noExt.length() > 15) {
            noExt = noExt.substring(0, 15);
        }
        String fileExt = fileName.substring(fileName.indexOf(".") + 1);
        fileName = noExt + "." + fileExt;
    }
    // 判断不同浏览器
    String agent = request.getHeader("USER-AGENT");
    if (agent == null) {
        response.setContentType("APPLICATION/OCTET-STREAM");
        // IE
        String newFileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=\"" + newFileName + "\"");
    } else {
        Boolean isMozilla = agent.indexOf("Mozilla") > -1;
        Boolean isIE = agent.indexOf("MSIE") > -1;
        Boolean isFirefox = agent.indexOf("Firefox") > -1;
        Boolean isSafari = agent.indexOf("Safari") > -1;
        Boolean isChrome = agent.indexOf("Chrome") > -1;
        if (isMozilla) {
            // 浏览器下载
            response.setContentType("application/x-act-msdownload;charset=UTF-8");
        } else {
            response.setContentType("APPLICATION/OCTET-STREAM");
        }
        // IE
        String newFileName = URLEncoder.encode(fileName, "UTF-8");
        if (isIE) {
        } else if (isFirefox || isSafari) {
            newFileName = new String(fileName.getBytes(encoding), // Firefox,Safari
            "ISO8859-1");
        } else if (isChrome) {
            newFileName = new String(Base64.encodeBase64(fileName.getBytes(// Chrome
            encoding)));
        }
        response.setHeader("Content-disposition", "attachment;filename=\"" + newFileName + "\"");
    }
}


public void download(HttpServletRequest request,HttpServletResponse response,String filePath,String originalFileName,Boolean isOnLine){
    File file = new File(filePath);
    if (!file.exists()) {
        response.sendError(404, "File not found!");
        return;
    }
    String fileName = file.getName();
    if (StringUtils.isNotBlank(originalFileName)) {
        fileName = originalFileName;
    }
    setDownloadContentType(request, response, fileName, null, false);
    BufferedInputStream bis = null;
    BufferedOutputStream bos = null;
    OutputStream fos = null;
    InputStream fis = null;
    try {
        fis = new FileInputStream(file);
        bis = new BufferedInputStream(fis);
        fos = response.getOutputStream();
        bos = new BufferedOutputStream(fos);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = bis.read(buffer, 0, 8192)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    try {
        bos.flush();
        fis.close();
        bis.close();
        fos.close();
        bos.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


public boolean exsistUrl(String fileName,String ctx){
    if (StringHelper.isEmpty(fileName)) {
        return false;
    }
    if (fileName.toLowerCase().startsWith("http://")) {
        try {
            URL url = new URL(fileName);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (httpURLConnection.getResponseCode() == 200) {
                // 可能返回的是页面不存在的提示，所以url是存在
                if (url.openStream().available() > 300) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
    } else {
        File file = new File(ctx + fileName);
        if (file.exists()) {
            return true;
        }
    }
    return false;
}


public Boolean isPicture(String fileName){
    String extension = "";
    if (!StringHelper.isEmpty(fileName) && fileName.indexOf(".") > -1) {
        Integer index = fileName.lastIndexOf(".");
        extension = fileName.substring(index + 1);
    }
    // 图片后缀名
    String[] picExts = { "jpg", "jpeg", "bmp", "png", "gif" };
    for (String picExt : picExts) {
        if (picExt.equals(extension)) {
            return true;
        }
    }
    return false;
}


public void getUrlFile(String httpUrl,String toFile){
    InputStream in = null;
    OutputStream out = null;
    try {
        // 用streams，存储获得的资源
        URL url = new URL(httpUrl);
        in = url.openStream();
        if (toFile == null) {
            // 显示在控制台
            out = System.out;
        } else {
            // 保存到文件
            out = new FileOutputStream(toFile);
        }
        // 用输出流out，输出到指定位置
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }
    } catch (Exception e) {
        System.err.println(e);
        System.err.println("Usage: java GetURL <url> [<filename>]");
    } finally {
        // 关闭流
        try {
            in.close();
            out.close();
        } catch (Exception e) {
        }
    }
}


public String getFileExt(String fileName){
    String value = "";
    int start = 0;
    int end = 0;
    if (fileName == null) {
        return null;
    }
    if (fileName.lastIndexOf('.') > 0) {
        start = fileName.lastIndexOf('.') + 1;
        end = fileName.length();
        value = fileName.substring(start, end);
        return value;
    } else {
        return "";
    }
}


@SuppressWarnings("unchecked")
public void delExtractZipFile(String zipPath,String dstPath){
    Set<String> pathSet = new HashSet<String>();
    ZipFile zipFile = new ZipFile(zipPath);
    Enumeration<ZipEntry> zipEntryEnums = (Enumeration<ZipEntry>) zipFile.entries();
    while (zipEntryEnums.hasMoreElements()) {
        ZipEntry zipEntry = zipEntryEnums.nextElement();
        String zipEntryName = zipEntry.getName();
        String[] pathSp = zipEntryName.split("/");
        if (pathSp != null) {
            pathSet.add(pathSp[0]);
        }
    }
    for (String path : pathSet) {
        path = dstPath + File.separator + path;
        org.apache.commons.io.FileUtils.deleteQuietly(new File(path));
    }
}


public boolean exsistOnlyUrl(String fileName){
    if (StringHelper.isEmpty(fileName)) {
        return false;
    }
    if (fileName.toLowerCase().startsWith("http://")) {
        try {
            URL url = new URL(fileName);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            if (httpURLConnection.getResponseCode() == 200) {
                // 可能返回的是页面不存在的提示，所以url是存在
                if (url.openStream().available() > 300) {
                    return true;
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
    return false;
}


}