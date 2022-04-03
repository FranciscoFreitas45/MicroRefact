package com.crazy.chapter17.duplicate.DownUtil;
 import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
public class DownThread extends Thread{

 private  int startPos;

 private  int currentPartSize;

 private  RandomAccessFile currentPart;

 private  int length;

public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart) {
    this.startPos = startPos;
    this.currentPartSize = currentPartSize;
    this.currentPart = currentPart;
}
public void run(){
    try {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "image/gif, image/jpeg, image/pjpeg, image/pjpeg," + "application/x-shockwave-flash,application/xaml+xml, " + "application/vnd.ms-xpsdocument,application/x-ms-xbap," + "application/x-ms-application,application/vnd.ms-excel," + "application/vnd.ms-powerpoint,application/msword,*/*");
        conn.setRequestProperty("Accept-Language", "zh_CN");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Connection", "Keep-Alive");
        InputStream inStream = conn.getInputStream();
        inStream.skip(startPos);
        byte[] buffer = new byte[1024];
        int hasRead = 0;
        while (length < currentPartSize && (hasRead = inStream.read(buffer)) != -1) {
            currentPart.write(buffer, 0, hasRead);
            length += hasRead;
        }
        currentPart.close();
        inStream.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}