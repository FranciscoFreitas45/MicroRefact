package com.uec.imonitor.news.utils;
 import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class IoUtils {


public void closeQuietly(Closeable closeables){
    for (Closeable closeable : closeables) {
        closeQuietly(closeable);
    }
}


public long copy(InputStream in,OutputStream out,byte[] buff){
    long count = 0;
    int len = -1;
    while ((len = in.read(buff, 0, buff.length)) != -1) {
        out.write(buff, 0, len);
        count += len;
    }
    return count;
}


}