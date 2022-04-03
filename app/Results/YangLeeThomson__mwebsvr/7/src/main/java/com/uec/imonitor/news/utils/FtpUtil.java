package com.uec.imonitor.news.utils;
 import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.uec.imonitor.common.util.CommonUtil;
public class FtpUtil {

 private  Logger log;

 private  Map<String,Map<String,String>> map;

 private  FTPClient ftpClient;


public void batchDownLoadZipFile(String serverPath,String localPath){
    FTPFile[] fileList = ftpClient.listFiles();
    int count = 0;
    for (FTPFile ftpFile : fileList) {
        if (ftpFile.getName().contains("txt") && ftpFile.getName().endsWith(".zip")) {
            if (!new File(localPath + "/" + ftpFile.getName()).exists()) {
                log.info("本地不存在的文件" + localPath + "/" + ftpFile.getName());
                log.info(ftpFile.getName());
                count++;
                if (!new File(localPath).exists()) {
                    new File(localPath).mkdirs();
                }
                File localFile = new File(localPath + "/" + ftpFile.getName());
                OutputStream is = new FileOutputStream(localFile);
                ftpClient.retrieveFile(ftpFile.getName(), is);
                is.close();
            }
        }
    }
    log.info("本次从服务器下载文件数量为" + count + "个");
}


public FTPClient getFTPClient(String host,int port,String username,String password){
    /*this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;*/
    ftpClient = new FTPClient();
    ftpClient.setControlEncoding("UTF-8");
    try {
        ftpClient.connect(host);
        ftpClient.setControlEncoding("UTF-8");
        boolean loginFlag = ftpClient.login(username, password);
        if (loginFlag) {
            int replyCode = ftpClient.getReplyCode();
            if (FTPReply.isPositiveCompletion(replyCode)) {
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            }
        }
        if (loginFlag) {
            log.info("FTP登录成功。。。");
            return ftpClient;
        } else {
            log.info("FTP登录失败。。。");
            return null;
        }
    } catch (SocketException e) {
        e.printStackTrace();
        return null;
    } catch (IOException e) {
        e.printStackTrace();
        return null;
    }
}


public void batchUnZip(String zipFilePath,String destDir){
    if (null != new File(zipFilePath)) {
        File[] listFiles = new File(zipFilePath).listFiles();
        for (File file : listFiles) {
            if (file.exists() && file.getName().endsWith(".zip")) {
                unZip(zipFilePath + "\\" + file.getName(), destDir);
            }
            if (file.exists() && file.isDirectory()) {
                batchUnZip(zipFilePath + "\\" + file.getName(), zipFilePath + "\\" + file.getName());
            }
        }
    }
}


public Map<String,Map<String,String>> batchDownLoadFile(String serverPath,String localPath){
    if (!new File(localPath).exists()) {
        new File(localPath).mkdirs();
        log.info("创建文件夹成功:" + localPath);
    }
    ftpClient.changeWorkingDirectory(serverPath);
    ftpClient.enterLocalPassiveMode();
    FTPFile[] fileList = ftpClient.listFiles();
    log.info("本次路径下文件列表" + CommonUtil.toJson(fileList));
    log.info("本次路径下文件列表数量" + fileList.length);
    int count = 0;
    for (FTPFile ftpFile : fileList) {
        if (ftpFile.isDirectory()) {
            log.info("文件夹" + ftpFile.getName() + ",递归调用。");
            batchDownLoadFile(serverPath + "/" + ftpFile.getName(), localPath + "/" + ftpFile.getName());
        } else if (ftpFile.isFile() && ftpFile.getName().endsWith(".txt")) {
            if (!new File(localPath + "/" + ftpFile.getName()).exists()) {
                log.info("本地不存在的文件" + localPath + "/" + ftpFile.getName());
                count++;
                if (!new File(localPath).exists()) {
                    log.info("本地路径" + localPath + "不存在，创建路径");
                    new File(localPath).mkdirs();
                }
                File localFile = new File(localPath + "/" + ftpFile.getName());
                log.info("本地文件完整路径" + localPath + "/" + ftpFile.getName());
                OutputStream is = new FileOutputStream(localFile);
                boolean flagRe = ftpClient.retrieveFile(ftpFile.getName(), is);
                TxtUtil txtUtil = new TxtUtil();
                // 获取ftp文件最后修改日期
                Date time = ftpFile.getTimestamp().getTime();
                // String modifTime = ftpClient.getModificationTime(serverPath + ftpFile.getName());
                // System.out.println("modifTime" + modifTime);
                // System.err.println(time.toString());
                // 文件下载完成，进行处理，入库，备份，删除本地临时文件
                Map<String, String> readSingleTxt = txtUtil.readSingleTxt(localPath + "/" + ftpFile.getName(), time.toString());
                map.put(localPath + "/" + ftpFile.getName(), readSingleTxt);
                if (flagRe) {
                    log.info(ftpFile.getName() + "下载文件成功");
                } else {
                    log.info(ftpFile.getName() + "下载文件失败");
                }
                is.close();
            }
        // 读取完文件进行删除
        // ftpClient.dele(ftpFile.getName());
        }
    }
    log.info("本次从服务器下载文件数量为" + count + "个");
    return map;
}


public void unZip(String zipFilePath,String destDir){
    /**
     * 使用GBK编码可以避免压缩中文文件名乱码
     */
    String CHINESE_CHARSET = "GBK";
    /**
     * 文件读取缓冲区大小
     */
    int CACHE_SIZE = 1024;
    ZipFile zipFile = null;
    try {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        zipFile = new ZipFile(zipFilePath, CHINESE_CHARSET);
        Enumeration<ZipEntry> zipEntries = zipFile.getEntries();
        File file, parentFile;
        ZipEntry entry;
        byte[] cache = new byte[CACHE_SIZE];
        while (zipEntries.hasMoreElements()) {
            entry = (ZipEntry) zipEntries.nextElement();
            if (entry.isDirectory()) {
                new File(destDir + "\\" + entry.getName()).mkdirs();
                continue;
            }
            bis = new BufferedInputStream(zipFile.getInputStream(entry));
            file = new File(destDir + "\\" + entry.getName());
            parentFile = file.getParentFile();
            if (parentFile != null && (!parentFile.exists())) {
                parentFile.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos, CACHE_SIZE);
            int readIndex = 0;
            while ((readIndex = bis.read(cache, 0, CACHE_SIZE)) != -1) {
                fos.write(cache, 0, readIndex);
            }
            bos.flush();
            bos.close();
            fos.close();
            bis.close();
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


public void disconnectFtp(){
    if (null != ftpClient) {
        try {
            ftpClient.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


}