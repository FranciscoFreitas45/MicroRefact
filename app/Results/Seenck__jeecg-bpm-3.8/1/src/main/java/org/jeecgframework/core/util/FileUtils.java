package org.jeecgframework.core.util;
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
public class FileUtils {


public void copyFile(String inputFile,String outputFile){
    File sFile = new File(inputFile);
    File tFile = new File(outputFile);
    FileInputStream fis = new FileInputStream(sFile);
    FileOutputStream fos = new FileOutputStream(tFile);
    int temp = 0;
    byte[] buf = new byte[10240];
    try {
        while ((temp = fis.read(buf)) != -1) {
            fos.write(buf, 0, temp);
        }
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            fis.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


public String getExtend(String filename,String defExt){
    if ((filename != null) && (filename.length() > 0)) {
        int i = filename.lastIndexOf('.');
        if ((i > 0) && (i < (filename.length() - 1))) {
            return (filename.substring(i + 1)).toLowerCase();
        }
    }
    return defExt.toLowerCase();
}


public String toHexString(int index){
    String hexString = Integer.toHexString(index);
    // 1个byte变成16进制的，只需要2位就可以表示了，取后面两位，去掉前面的符号填充
    hexString = hexString.substring(hexString.length() - 2);
    return hexString;
}


public boolean isPicture(String filename){
    // 文件名称为空的场合
    if (oConvertUtils.isEmpty(filename)) {
        // 返回不和合法
        return false;
    }
    // 获得文件后缀名
    // String tmpName = getExtend(filename);
    String tmpName = filename;
    // 声明图片后缀名数组
    String[][] imgeArray = { { "bmp", "0" }, { "dib", "1" }, { "gif", "2" }, { "jfif", "3" }, { "jpe", "4" }, { "jpeg", "5" }, { "jpg", "6" }, { "png", "7" }, { "tif", "8" }, { "tiff", "9" }, { "ico", "10" } };
    // 遍历名称数组
    for (int i = 0; i < imgeArray.length; i++) {
        // 判断单个类型文件的场合
        if (imgeArray[i][0].equals(tmpName.toLowerCase())) {
            return true;
        }
    }
    return false;
}


public boolean isDwg(String filename){
    // 文件名称为空的场合
    if (oConvertUtils.isEmpty(filename)) {
        // 返回不和合法
        return false;
    }
    // 获得文件后缀名
    String tmpName = getExtend(filename);
    // 声明图片后缀名数组
    if (tmpName.equals("dwg")) {
        return true;
    }
    return false;
}


public String getFilePrefix(String fileName){
    int splitIndex = fileName.lastIndexOf(".");
    return fileName.substring(0, splitIndex).replaceAll("\\s*", "");
}


public String encodingFileName(String fileName){
    String returnFileName = "";
    try {
        returnFileName = URLEncoder.encode(fileName, "UTF-8");
        returnFileName = StringUtils.replace(returnFileName, "+", "%20");
        if (returnFileName.length() > 150) {
            returnFileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
            returnFileName = StringUtils.replace(returnFileName, " ", "%20");
        }
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        LogUtil.info("Don't support this encoding ...");
    }
    return returnFileName;
}


public String getFilePrefix2(String fileName){
    int splitIndex = fileName.lastIndexOf(".");
    return fileName.substring(0, splitIndex);
}


public String getSwfPath(String path){
    String leftSlash = "/";
    if (!File.separator.equals(leftSlash)) {
        path = path.replace(File.separator, leftSlash);
    }
    // 文件目录带/
    String fileDir = path.substring(0, path.lastIndexOf(leftSlash) + 1);
    int pointPosition = path.lastIndexOf(".");
    // 文件名不带后缀
    String fileName = path.substring(path.lastIndexOf(leftSlash) + 1, pointPosition);
    // 取文件名首字母作为SWF文件名
    String swfName = PinyinUtil.getPinYinHeadChar(fileName);
    return fileDir + swfName + ".swf";
}


public boolean delete(String strFileName){
    File fileDelete = new File(strFileName);
    if (!fileDelete.exists() || !fileDelete.isFile()) {
        LogUtil.info("错误: " + strFileName + "不存在!");
        return false;
    }
    LogUtil.info("--------成功删除文件---------" + strFileName);
    return fileDelete.delete();
}


public void uploadTxtFile(MultipartFile mf,String savePath){
    // 利用utf-8字符集的固定首行隐藏编码原理
    // Unicode:FF FE   UTF-8:EF BB
    byte[] allbytes = mf.getBytes();
    try {
        String head1 = toHexString(allbytes[0]);
        // System.out.println(head1);
        String head2 = toHexString(allbytes[1]);
        // System.out.println(head2);
        if ("ef".equals(head1) && "bb".equals(head2)) {
            // UTF-8
            String contents = new String(mf.getBytes(), "UTF-8");
            if (StringUtils.isNotBlank(contents)) {
                OutputStream out = new FileOutputStream(savePath);
                out.write(contents.getBytes());
                out.close();
            }
        } else {
            // update-begin--Author:zhoujf  Date:20150610 for：TXT文件预览出现乱码的错误
            // GBK
            String contents = new String(mf.getBytes(), "GBK");
            OutputStream out = new FileOutputStream(savePath);
            out.write(contents.getBytes());
            out.close();
        // update-end--Author:zhoujf  Date:20150610 for：TXT文件预览出现乱码的错误
        }
    } catch (Exception e) {
        String contents = new String(mf.getBytes(), "UTF-8");
        if (StringUtils.isNotBlank(contents)) {
            OutputStream out = new FileOutputStream(savePath);
            out.write(contents.getBytes());
            out.close();
        }
    }
}


}