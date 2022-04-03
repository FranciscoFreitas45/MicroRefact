package com.uec.imonitor.news.utils;
 import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TxtUtil {

 private  Logger log;

 private  Map<String,Map<String,String>> map;


public boolean deleteFile(String fileName){
    File file = new File(fileName);
    // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
    if (file.exists() && file.isFile()) {
        if (file.delete()) {
            System.out.println("删除单个文件" + fileName + "成功！");
            return true;
        } else {
            System.out.println("删除单个文件" + fileName + "失败！");
            return false;
        }
    } else {
        System.out.println("删除单个文件失败：" + fileName + "不存在！");
        return false;
    }
}


public Map<String,String> readSingleTxt(String singlePath,String time){
    List<String> txtAllList = null;
    List<String> txtTitle = null;
    List<String> txtContent = null;
    Map<String, String> txtMap = null;
    String encoding = "GBK";
    File file = new File(singlePath);
    if (file.isFile() && file.exists()) {
        // 判断文件是否存在
        try {
            txtAllList = new ArrayList<String>();
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                txtAllList.add(lineTxt);
            }
            read.close();
            // TODO
            // 为了删除增加的代码，待测试。。。
            bufferedReader.close();
            // 对全文空行进行删除
            for (int i = 0; i < txtAllList.size(); i++) {
                Matcher matcher = Pattern.compile("[\u4e00-\u9fa5]").matcher(txtAllList.get(i));
                Matcher matcher1 = Pattern.compile("^[A-Za-z0-9]+$").matcher(txtAllList.get(i));
                if (!matcher.find() && !matcher1.find()) {
                    txtAllList.remove(i);
                }
            }
            for (int i = 0; i < txtAllList.size(); i++) {
                if (txtAllList.get(i).length() < 1) {
                    txtAllList.remove(i);
                }
            }
            // 因为文件格式的原因，存在会议形式的格式，所以分开处理
            int classCount = 0;
            for (String string : txtAllList) {
                if (string.contains("。")) {
                    // 普通稿件格式
                    classCount++;
                    break;
                }
            }
            if (classCount > 0) {
                // 普通稿件格式
                // 对文件内容进行筛选
                int titleFlag = 0;
                txtContent = new ArrayList<String>();
                txtTitle = new ArrayList<String>();
                // 添加解决第二、三行存在。的问题 的功能
                int contentFlag = 0;
                for (int i = 3; i < txtAllList.size(); i++) {
                    if (contentFlag == 0) {
                        if (txtAllList.get(i).contains("。") || txtAllList.get(i).contains("？") || txtAllList.get(i).contains("！") || txtAllList.get(i).contains("!") || txtAllList.get(i).contains("：") || txtAllList.get(i).contains("?") || txtAllList.get(i).contains("/")) {
                            txtContent.add(txtAllList.get(i));
                            contentFlag = 1;
                            titleFlag = i;
                        }
                    } else {
                        txtContent.add(txtAllList.get(i));
                    }
                }
                // 添加标题等元信息
                for (int i = 0; i <= titleFlag; i++) {
                    txtTitle.add(txtAllList.get(i));
                }
                for (int i = 0; i < txtTitle.size(); i++) {
                    // ^[A-Za-z0-9]+$
                    Matcher matcher = Pattern.compile("[\u4e00-\u9fa5]").matcher(txtTitle.get(i));
                    Matcher matcher1 = Pattern.compile("^[A-Za-z0-9]+$").matcher(txtTitle.get(i));
                    // Matcher matcher = Pattern.compile("\\s*|\t|\r|\n").matcher(txtTitle.get(i));
                    if (!matcher.find() && !matcher1.find()) {
                        txtTitle.remove(i);
                    }
                }
                // 过滤title中正文的第一个标题
                // 验证字符串 只包含汉字英文数字
                // 有的标题最后含有空格，无法过滤该类型的
                Matcher matcher = Pattern.compile("^[a-zA-Z0-9\u4E00-\u9FA5]+$").matcher(txtTitle.get(txtTitle.size() - 2));
                String titleContent = txtTitle.get(txtTitle.size() - 2);
                String substring = titleContent.substring(0, 1);
                // Matcher matcher2 = Pattern.compile("[\u4e00-\u9fa5]").matcher(substring);
                // 指定字符串含有中文、英文、数字
                Matcher matcher2 = Pattern.compile("^[a-zA-Z0-9\u4E00-\u9FA5]+$").matcher(substring);
                if ((titleContent.length() > 7 && !titleContent.contains(",") && !titleContent.contains("，") && !titleContent.contains("、")) || titleContent.contains("%") || titleContent.contains("“") || !matcher2.find()) {
                    txtTitle.remove(txtTitle.size() - 2);
                }
                // 处理元信息
                // 作者
                String author;
                // 正文
                String content = "";
                // 大标题
                String Headline = null;
                // 小标题
                String title = null;
                // 版块
                String section;
                // 版块号
                String sectionNo;
                // 板块信息
                String stringTitle = txtTitle.get(0);
                sectionNo = stringTitle.substring(0, 3).replaceAll("[^a-z^A-Z^0-9]", "");
                section = stringTitle.replaceAll("[^\u4e00-\u9fa5]", "");
                txtTitle.remove(0);
                // 处理最后一行的作者问题
                String tempString = txtTitle.get(txtTitle.size() - 1).substring(0, 1).replaceAll("[^\u4e00-\u9fa5]", "");
                // 正文第一行存在作者
                if (tempString.length() > 1) {
                    if (txtTitle.get(txtTitle.size() - 2).contains(txtTitle.get(txtTitle.size() - 1).substring(0, 3))) {
                        author = txtTitle.get(txtTitle.size() - 2);
                    } else {
                        author = txtTitle.get(txtTitle.size() - 2).concat("、").concat(txtTitle.get(txtTitle.size() - 1).substring(0, 3));
                    }
                } else {
                    // 正文第一行没有作者
                    author = txtTitle.get(txtTitle.size() - 2);
                }
                txtTitle.remove(txtTitle.size() - 1);
                txtTitle.remove(txtTitle.size() - 1);
                if (txtTitle.size() == 1) {
                    Headline = txtTitle.get(0);
                } else if (txtTitle.size() > 1) {
                    Headline = txtTitle.get(txtTitle.size() - 1);
                    title = txtTitle.get(txtTitle.size() - 2);
                }
                // 保存正文
                // 处理正文第一行含有作者的情况
                String tempContent = txtContent.get(0).substring(0, 1).replaceAll("[^\u4e00-\u9fa5]", "");
                if (tempContent.length() > 1) {
                    // 证明含有作者,去作者名称
                    String tempFirst = txtContent.get(0).substring(3, txtContent.get(0).length());
                    txtContent.set(0, tempFirst);
                }
                // content list转成string
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < txtContent.size(); i++) {
                    stringBuffer.append(txtContent.get(i)).append("\r\n");
                }
                content = stringBuffer.toString();
                txtMap = new HashMap<String, String>(0);
                System.out.println(singlePath);
                // String stringTmp = singlePath.substring(singlePath.lastIndexOf("//") + 2, singlePath.indexOf("."));
                String stringTmp = singlePath.substring(singlePath.lastIndexOf("/") + 1, singlePath.indexOf("."));
                log.info(singlePath);
                // 解决title为null
                if (Headline == null) {
                    Headline = author;
                }
                txtMap.put("sectionNo", sectionNo);
                txtMap.put("originalCode", stringTmp);
                txtMap.put("section", section);
                txtMap.put("Headline", Headline);
                txtMap.put("title", title);
                txtMap.put("author", author);
                txtMap.put("content", content);
                txtMap.put("createTime", time);
            } else {
                txtMap = new HashMap<String, String>(0);
                txtMap.put("errorMsg", "暂不支持该类型稿件!");
            }
        } catch (Exception e) {
            log.info("txt处理失败的文件：" + singlePath);
            e.printStackTrace();
            return null;
        }
    }
    return txtMap;
}


public String hashKeyForDisk(String key){
    String cacheKey;
    try {
        final MessageDigest mDigest = MessageDigest.getInstance("MD5");
        mDigest.update(key.getBytes());
        cacheKey = bytesToHexString(mDigest.digest());
    } catch (NoSuchAlgorithmException e) {
        cacheKey = String.valueOf(key.hashCode());
    }
    return cacheKey;
}


public void copy(File file,File newFile){
    // File newFiles = new File(newFile, file.getName());
    File newFiles = newFile;
    File[] filearray = file.listFiles();
    // System.out.println(filearray.length);
    newFiles.mkdir();
    for (File files : filearray) {
        if (files.isDirectory()) {
            copy(files, newFiles);
        } else {
            write(files, newFiles);
        }
    }
}


public void write(File file,File newFile){
    String pathTmp = newFile.getPath() + "/" + file.getName();
    if (!new File(pathTmp).exists()) {
        File newFiles = new File(newFile, file.getName());
        BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(newFiles));
        BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
        byte[] bys = new byte[1024];
        int len = 0;
        while ((len = fis.read(bys)) != -1) {
            fos.write(bys, 0, len);
        }
        fos.close();
        fis.close();
    } else {
        log.info(file.getName() + "该文档已存在备份文件中");
    }
}


public boolean delete(String fileName){
    File file = new File(fileName);
    if (!file.exists()) {
        System.out.println("删除文件失败:" + fileName + "不存在！");
        return false;
    } else {
        if (file.isFile())
            return deleteFile(fileName);
        else
            return deleteDirectory(fileName);
    }
}


public boolean deleteDirectory(String dir){
    // 如果dir不以文件分隔符结尾，自动添加文件分隔符
    if (!dir.endsWith(File.separator))
        dir = dir + File.separator;
    File dirFile = new File(dir);
    // 如果dir对应的文件不存在，或者不是一个目录，则退出
    if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
        System.out.println("删除目录失败：" + dir + "不存在！");
        return false;
    }
    boolean flag = true;
    // 删除文件夹中的所有文件包括子目录
    File[] files = dirFile.listFiles();
    for (int i = 0; i < files.length; i++) {
        // 删除子文件
        if (files[i].isFile()) {
            flag = deleteFile(files[i].getAbsolutePath());
            if (!flag)
                break;
        } else // 删除子目录
        if (files[i].isDirectory()) {
            flag = deleteDirectory(files[i].getAbsolutePath());
            if (!flag)
                break;
        }
    }
    if (!flag) {
        System.out.println("删除目录失败！");
        return false;
    }
    // 删除当前目录
    if (dirFile.delete()) {
        System.out.println("删除目录" + dir + "成功！");
        return true;
    } else {
        return false;
    }
}


public Map<String,String> readTxt(String singlePath){
    List<String> txtAllList = null;
    List<String> txtTitle = null;
    List<String> txtContent = null;
    Map<String, String> txtMap = null;
    String encoding = "GBK";
    File file = new File(singlePath);
    if (file.isFile() && file.exists()) {
        // 判断文件是否存在
        Calendar cal = Calendar.getInstance();
        long time = file.lastModified();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.setTimeInMillis(time);
        String createTime = formatter.format(cal.getTime());
        try {
            txtAllList = new ArrayList<String>();
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                txtAllList.add(lineTxt);
            }
            read.close();
            // 因为文件格式的原因，存在会议形式的格式，所以分开处理
            int classCount = 0;
            for (String string : txtAllList) {
                // if (string.endsWith("。")) {
                if (string.contains("。")) {
                    // 普通稿件格式
                    classCount++;
                    break;
                }
            }
            if (classCount > 0) {
                // 普通稿件格式
                // 对文件内容进行筛选
                int flag = 0;
                txtContent = new ArrayList<String>();
                txtTitle = new ArrayList<String>();
                // 添加解决第二、三行存在。的问题 的功能
                int contentFlag = 0;
                for (int i = 4; i < txtAllList.size(); i++) {
                    if (contentFlag == 0) {
                        if (txtAllList.get(i).contains("。") || txtAllList.get(i).contains("？") || txtAllList.get(i).contains("！") || txtAllList.get(i).contains("!") || txtAllList.get(i).contains("：")) {
                            txtContent.add(txtAllList.get(i));
                            contentFlag = 1;
                        }
                    } else {
                        txtContent.add(txtAllList.get(i));
                    }
                }
                int titleFlag = 0;
                for (String string : txtAllList) {
                    if (titleFlag == 0) {
                        txtTitle.add(string);
                        if ((string.contains("。") || string.contains("？") || string.contains("！") || string.contains("!") || string.contains("：")) && txtTitle.size() > 4) {
                            titleFlag = 1;
                        }
                    }
                }
                // 如果第二、三行含有。或者?,删除
                if (txtTitle.get(1).contains("。") && txtTitle.get(1).contains("？")) {
                    txtTitle.remove(1);
                }
                if (txtTitle.get(2).contains("。") && txtTitle.get(1).contains("？")) {
                    txtTitle.remove(2);
                }
                for (int i = 0; i < txtTitle.size(); i++) {
                    if (txtTitle.get(i).length() < 2) {
                        txtTitle.remove(i);
                    }
                }
                // 处理元信息
                // 作者
                String author;
                // 正文
                String content = "";
                // 大标题
                String Headline = null;
                // 小标题
                String title = null;
                // 版块
                String section;
                // 版块号
                String sectionNo;
                // 大标题
                Headline = txtTitle.get(1);
                // 小标题
                if ((txtTitle.get(2).length() > 9) && (!txtTitle.get(2).contains(",") || !txtTitle.get(2).contains("、"))) {
                    title = txtTitle.get(2);
                } else {
                    title = null;
                }
                // 处理最后一行的作者问题
                String tempString = txtTitle.get(txtTitle.size() - 1).substring(0, 1).replaceAll("[^\u4e00-\u9fa5]", "");
                if (tempString.length() > 1) {
                    // 存在第二作者
                    if (txtTitle.get(txtTitle.size() - 2).contains(txtTitle.get(txtTitle.size() - 1).substring(0, 3))) {
                        author = txtTitle.get(txtTitle.size() - 2);
                    } else {
                        author = txtTitle.get(txtTitle.size() - 2).concat("、").concat(txtTitle.get(txtTitle.size() - 1).substring(0, 3));
                    }
                } else {
                    // 正文第一行没有作者
                    if (txtTitle.get(txtTitle.size() - 2).length() > 8 && (!txtTitle.get(txtTitle.size() - 2).contains("、")) && (!txtTitle.get(txtTitle.size() - 2).contains(",")) && (!txtTitle.get(txtTitle.size() - 2).contains("（")) && (!txtTitle.get(txtTitle.size() - 2).contains("?"))) {
                        author = txtTitle.get(txtTitle.size() - 3);
                    } else if (txtTitle.get(txtTitle.size() - 2).length() == 4) {
                        author = txtTitle.get(txtTitle.size() - 3);
                    } else if (txtTitle.get(txtTitle.size() - 2).length() < 8 && txtTitle.get(txtTitle.size() - 2).contains("”")) {
                        author = txtTitle.get(txtTitle.size() - 3);
                    } else {
                        author = txtTitle.get(txtTitle.size() - 2);
                    }
                }
                if (author.length() > 12) {
                    author = null;
                }
                // 保存正文
                // 处理正文第一行含有作者的情况
                String tempContent = txtContent.get(0).substring(0, 1).replaceAll("[^\u4e00-\u9fa5]", "");
                if (tempContent.length() > 1) {
                    // 证明含有作者,去作者名称
                    String tempFirst = txtContent.get(0).substring(3, txtContent.get(0).length());
                    txtContent.set(0, tempFirst);
                }
                // content list转成string
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < txtContent.size(); i++) {
                    stringBuffer.append(txtContent.get(i)).append("\r\n");
                }
                content = stringBuffer.toString();
                // 板块信息
                String stringTitle = txtTitle.get(0);
                sectionNo = stringTitle.substring(0, 3).replaceAll("[^a-z^A-Z^0-9]", "");
                section = stringTitle.replaceAll("[^\u4e00-\u9fa5]", "");
                txtMap = new HashMap<String, String>(0);
                String stringTmp = singlePath.substring(singlePath.lastIndexOf("//") + 2, singlePath.indexOf("."));
                txtMap.put("sectionNo", sectionNo);
                txtMap.put("originalCode", stringTmp);
                txtMap.put("section", section);
                txtMap.put("Headline", Headline);
                txtMap.put("title", title);
                txtMap.put("author", author);
                txtMap.put("content", content);
            } else {
                // 会议稿件格式
                txtContent = new ArrayList<String>();
                txtTitle = new ArrayList<String>();
                int flag = 0;
                for (String string : txtAllList) {
                    if (flag == 0) {
                        txtTitle.add(string);
                        if ((string.length() == 2 || string.length() == 3) && txtTitle.size() > 2) {
                            flag = 1;
                        }
                    }
                    if (flag == 1) {
                        txtContent.add(string);
                    }
                }
                txtContent.remove(0);
                // 删除空行
                for (int i = 0; i < txtTitle.size(); i++) {
                    if (txtTitle.get(i).isEmpty()) {
                        txtTitle.remove(i);
                    }
                }
                // 处理元信息
                // 作者
                String author;
                // 正文
                String content = "";
                // 大标题
                String Headline = null;
                // 小标题
                String title = null;
                // 版块
                String section;
                // 版块号
                String sectionNo;
                // 板块信息
                String stringTitle = txtTitle.get(0);
                sectionNo = stringTitle.substring(0, 3).replaceAll("[^a-z^A-Z^0-9]", "");
                section = stringTitle.replaceAll("[^\u4e00-\u9fa5]", "");
                // 大标题
                Headline = txtTitle.get(1);
                title = txtTitle.get(2);
                author = txtTitle.get(3);
                // 保存正文
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < txtContent.size(); i++) {
                    stringBuffer.append(txtContent.get(i)).append("\r\n");
                }
                content = stringBuffer.toString();
                String stringTmp = singlePath.substring(singlePath.lastIndexOf("//") + 2, singlePath.indexOf("."));
                txtMap = new HashMap<String, String>(0);
                txtMap.put("sectionNo", sectionNo);
                txtMap.put("originalCode", stringTmp);
                txtMap.put("section", section);
                txtMap.put("Headline", Headline);
                txtMap.put("title", title);
                txtMap.put("author", author);
                txtMap.put("content", content);
                txtMap.put("createTime", createTime);
            }
        } catch (Exception e) {
            log.info("txt处理失败的文件：" + singlePath);
            e.printStackTrace();
            return null;
        }
    }
    return txtMap;
}


public String bytesToHexString(byte[] bytes){
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < bytes.length; i++) {
        String hex = Integer.toHexString(0xFF & bytes[i]);
        if (hex.length() == 1) {
            sb.append('0');
        }
        sb.append(hex);
    }
    return sb.toString();
}


}