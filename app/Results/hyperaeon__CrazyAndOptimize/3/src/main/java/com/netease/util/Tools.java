package com.netease.util;
 import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Tools {


public String getTimes(String StrDate){
    String resultTimes = "";
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    java.util.Date now;
    try {
        now = new Date();
        java.util.Date date = df.parse(StrDate);
        long times = now.getTime() - date.getTime();
        long day = times / (24 * 60 * 60 * 1000);
        long hour = (times / (60 * 60 * 1000) - day * 24);
        long min = ((times / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long sec = (times / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        StringBuffer sb = new StringBuffer();
        // sb.append("发表于：");
        if (hour > 0) {
            sb.append(hour + "小时前");
        } else if (min > 0) {
            sb.append(min + "分钟前");
        } else {
            sb.append(sec + "秒前");
        }
        resultTimes = sb.toString();
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return resultTimes;
}


public int getRandomNum(){
    Random r = new Random();
    // (Math.random()*(999999-100000)+100000)
    return r.nextInt(900000) + 100000;
}


public String date2Str(Date date,String format){
    if (date != null) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    } else {
        return "";
    }
}


public boolean checkMobileNumber(String mobileNumber){
    boolean flag = false;
    try {
        Pattern regex = Pattern.compile("^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$");
        Matcher matcher = regex.matcher(mobileNumber);
        flag = matcher.matches();
    } catch (Exception e) {
        flag = false;
    }
    return flag;
}


public boolean isEmpty(String s){
    return s == null || "".equals(s) || "null".equals(s);
}


public void main(String[] args){
    System.out.println(getRandomNum());
}


public boolean notEmpty(String s){
    return s != null && !"".equals(s) && !"null".equals(s);
}


public Date str2Date(String date){
    if (notEmpty(date)) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    } else {
        return null;
    }
}


public void writeFile(String fileP,String content){
    // 项目路径
    String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../";
    filePath = (filePath.trim() + fileP.trim()).substring(6).trim();
    if (filePath.indexOf(":") != 1) {
        filePath = File.separator + filePath;
    }
    try {
        OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(filePath), "utf-8");
        BufferedWriter writer = new BufferedWriter(write);
        writer.write(content);
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


public boolean checkEmail(String email){
    boolean flag = false;
    try {
        String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(email);
        flag = matcher.matches();
    } catch (Exception e) {
        flag = false;
    }
    return flag;
}


public String[] str2StrArray(String str){
    return str2StrArray(str, ",\\s*");
}


public String readTxtFile(String fileP){
    try {
        // 项目路径
        String filePath = String.valueOf(Thread.currentThread().getContextClassLoader().getResource("")) + "../../";
        filePath = filePath.replaceAll("file:/", "");
        filePath = filePath.replaceAll("%20", " ");
        filePath = filePath.trim() + fileP.trim();
        if (filePath.indexOf(":") != 1) {
            filePath = File.separator + filePath;
        }
        String encoding = "utf-8";
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            // 判断文件是否存在
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), // 考虑到编码格式
            encoding);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                return lineTxt;
            }
            read.close();
        } else {
            System.out.println("找不到指定的文件,查看此路径是否正确:" + filePath);
        }
    } catch (Exception e) {
        System.out.println("读取文件内容出错");
    }
    return "";
}


}