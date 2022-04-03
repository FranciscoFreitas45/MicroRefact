package org.jeecgframework.core.util;
 import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import jodd.io.StringInputStream;
public class StreamUtils {

 final  int BUFFER_SIZE;


public byte[] InputStreamTOByte(InputStream in){
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    byte[] data = new byte[BUFFER_SIZE];
    int count = -1;
    while ((count = in.read(data, 0, BUFFER_SIZE)) != -1) outStream.write(data, 0, count);
    data = null;
    return outStream.toByteArray();
}


public InputStream byteTOInputStream(byte[] in){
    ByteArrayInputStream is = new ByteArrayInputStream(in);
    return is;
}


public InputStream byteTOFInputStream(byte[] in){
    InputStream is = new StringInputStream(InputStreamTOString(StreamUtils.byteTOInputStream(in)));
    return is;
}


public String getString(String in){
    String is = null;
    try {
        is = byteTOString(StringTObyte(in));
    } catch (Exception e) {
        e.printStackTrace();
    }
    return is;
}


public FileOutputStream getFileOutputStream(String filepath,boolean append){
    FileOutputStream fileOutputStream = null;
    try {
        fileOutputStream = new FileOutputStream(filepath, append);
    } catch (FileNotFoundException e) {
        System.out.print("错误信息:文件不存在");
        e.printStackTrace();
    }
    return fileOutputStream;
}


public File getFile(String filepath){
    return new File(filepath);
}


public byte[] getBytes(InputStream is){
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] b = new byte[BUFFER_SIZE];
    int len = 0;
    while ((len = is.read(b, 0, BUFFER_SIZE)) != -1) {
        baos.write(b, 0, len);
    }
    baos.flush();
    byte[] bytes = baos.toByteArray();
    org.jeecgframework.core.util.LogUtil.info(new String(bytes));
    return bytes;
}


public InputStream StringTOInputStream(String in){
    ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes("UTF-8"));
    return is;
}


public byte[] StringTObyte(String in){
    byte[] bytes = null;
    try {
        bytes = InputStreamTOByte(StringTOInputStream(in));
    } catch (IOException e) {
    } catch (Exception e) {
        e.printStackTrace();
    }
    return bytes;
}


public FileInputStream getFileInputStream(File file){
    FileInputStream fileInputStream = null;
    try {
        fileInputStream = new FileInputStream(file);
    } catch (FileNotFoundException e) {
        System.out.print("错误信息:文件不存在");
        e.printStackTrace();
    }
    return fileInputStream;
}


public String InputStreamTOString(InputStream in,String encoding){
    String string = null;
    ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    byte[] data = new byte[BUFFER_SIZE];
    int count = -1;
    try {
        while ((count = in.read(data, 0, BUFFER_SIZE)) != -1) outStream.write(data, 0, count);
    } catch (IOException e) {
        e.printStackTrace();
    }
    data = null;
    try {
        string = new String(outStream.toByteArray(), encoding);
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    return string;
}


public String byteTOString(byte[] in){
    InputStream is = null;
    try {
        is = byteTOInputStream(in);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return InputStreamTOString(is, "UTF-8");
}


public ByteArrayOutputStream getByteArrayOutputStream(){
    return new ByteArrayOutputStream();
}


}