package com.xwtec.xwserver.util;
 import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
public class FileUtil {

 private  Logger logger;


public void deleteFile(String relativePath,String fileName){
    // 文件存放的根目录的绝对路径
    StringBuilder path = new StringBuilder(ProUtil.get("uploadFileBasePath"));
    path.append(relativePath + File.separator + fileName);
    File file = new File(path.toString());
    if (file.exists() && file.isFile()) {
        try {
            file.delete();
        } catch (Exception e) {
            logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
        }
    }
}


public void exportFile(HttpServletResponse response,List<List<String>> list,String enc){
    PrintWriter out = null;
    try {
        // 实现下载功能
        response.addHeader("Content-Disposition", "attachment;filename=" + new String("exportFile.txt".getBytes("GBK"), "ISO8859_1"));
        response.setContentType("text/html");
        // 编码格式
        response.setCharacterEncoding(enc);
        out = response.getWriter();
        /**
         * 循环所有行的集合
         */
        for (int i = 0; i < list.size(); i++) {
            List<String> l1 = list.get(i);
            /**
             * 每行信息
             */
            for (int j = 0; j < l1.size(); j++) {
                out.write(l1.get(j));
                out.write("\t");
            }
            // 换行
            out.write("\r\n");
        }
        out.close();
    } catch (IOException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    } finally {
        if (out != null) {
            try {
                out.close();
            } catch (Exception e) {
                logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
            }
        }
    }
}


public String upload(MultipartFile multipartFile){
    return upload(multipartFile, null);
}


public void createNewFile(String fileFullPath,String content,String enc){
    File file = new File(fileFullPath);
    if (file.getParentFile() != null && !file.getParentFile().exists()) {
        file.getParentFile().mkdirs();
    }
    OutputStreamWriter os = null;
    try {
        if (enc == null || enc.length() == 0) {
            os = new OutputStreamWriter(new FileOutputStream(file));
        } else {
            os = new OutputStreamWriter(new FileOutputStream(file), enc);
        }
        os.write(content);
        os.close();
    } catch (IOException e) {
        logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
    } finally {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                logger.error(CommonUtil.getErrorMessage(Thread.currentThread().getStackTrace()[1], e));
            }
        }
    }
}


public String getFileName(String path,String fileNameSuffix){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    Random random = new Random();
    String fileName = sdf.format(new Date()) + String.format("%03d", random.nextInt(999)) + fileNameSuffix;
    File file = new File(path + fileName);
    // 如果文件已存在，需要重新生成文件名
    if (file.exists()) {
        return getFileName(path, fileNameSuffix);
    }
    return fileName;
}


}