package com.gbcom.system.utils;
 import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import sun.misc.BASE64Encoder;
public class CommonUtil {


public byte[] read(String srcFile){
    File file = new File(srcFile);
    InputStream input = null;
    try {
        input = new FileInputStream(file);
        return IOUtils.toByteArray(input);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        IOUtils.closeQuietly(input);
    }
    return new byte[0];
}


public void appendFile(String fileName,String content){
    FileWriter writer = null;
    try {
        // append mode
        writer = new FileWriter(fileName, true);
        writer.write(content);
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            writer.close();
        } catch (IOException e) {
            writer = null;
        }
    }
}


@SuppressWarnings("deprecation")
public void appendWrite(String srcFile,StringBuffer buffer){
    File file = new File(srcFile);
    OutputStream out = null;
    try {
        // append
        out = new FileOutputStream(file, true);
        IOUtils.write(buffer, out, "UTF-8");
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        if (out != null) {
            IOUtils.closeQuietly(out);
        }
    }
}


public void writeLines(String srcFile,List<String> lines){
    File file = new File(srcFile);
    OutputStream out = null;
    try {
        out = new FileOutputStream(file);
        IOUtils.writeLines(lines, null, out, "UTF-8");
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        if (out != null) {
            IOUtils.closeQuietly(out);
        }
    }
}


public void main(String[] args){
    String dir = "d:\\";
    String srcName = "xxx.jpg";
    String destName = "test.xds";
    // FileUtils.forceMkdir(new File(dir));
    // 
    // copy(dir+File.separator+srcName,dir+File.separator+destName);
    delete(dir + File.separator + srcName, dir + File.separator + destName);
}


public String encode64(byte[] bstr){
    return new BASE64Encoder().encode(bstr);
}


public void delete(String dests){
    for (String dest : dests) {
        File file = new File(dest);
        try {
            FileUtils.forceDeleteOnExit(file);
        } catch (IOException e) {
            e.printStackTrace();
            continue;
        } finally {
            file = null;
        }
    }
}


public String readAll(String srcFile){
    File file = new File(srcFile);
    InputStream input = null;
    try {
        input = new FileInputStream(file);
        return IOUtils.toString(input, "utf-8");
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        IOUtils.closeQuietly(input);
    }
    return "";
}


public void wirte(String srcFile,byte[] data){
    File file = new File(srcFile);
    OutputStream out = null;
    try {
        out = new FileOutputStream(file);
        IOUtils.write(data, out);
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (out != null) {
            IOUtils.closeQuietly(out);
        }
    }
}


@SuppressWarnings("deprecation")
public void writeAll(String srcFile,StringBuffer buffer){
    File file = new File(srcFile);
    OutputStream out = null;
    try {
        out = new FileOutputStream(file);
        IOUtils.write(buffer, out, "utf-8");
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        if (out != null) {
            IOUtils.closeQuietly(out);
        }
    }
}


public List<String> readLines(String srcFile){
    File file = new File(srcFile);
    InputStream input = null;
    try {
        input = new FileInputStream(file);
        return readLines(input, "utf-8");
    // IOUtils.readLines(input, encoding);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        IOUtils.closeQuietly(input);
    }
    return new ArrayList<String>(0);
}


public int copy(String src,String dest){
    File srcFile = new File(src);
    File destFile = new File(dest);
    InputStream input = null;
    OutputStream output = null;
    try {
        input = FileUtils.openInputStream(srcFile);
        output = new FileOutputStream(destFile);
        return IOUtils.copy(input, output);
    } catch (FileNotFoundException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } finally {
        if (output != null) {
            IOUtils.closeQuietly(output);
        }
        if (input != null) {
            IOUtils.closeQuietly(input);
        }
    }
    return -1;
}


public void rewriteFile(String fileName,String content){
    OutputStreamWriter writer = null;
    try {
        writer = new OutputStreamWriter(new FileOutputStream(fileName), "utf-8");
        writer.write(content);
        writer.close();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        try {
            writer.close();
        } catch (IOException e) {
            writer = null;
        }
    }
}


}