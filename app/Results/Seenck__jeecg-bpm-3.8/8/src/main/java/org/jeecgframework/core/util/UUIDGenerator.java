package org.jeecgframework.core.util;
 import java.net.InetAddress;
public class UUIDGenerator {

 private  int IP;

 private  short counter;

 private  int JVM;


public int getLoTime(){
    return (int) System.currentTimeMillis();
}


public int toInt(byte[] bytes){
    int result = 0;
    for (int i = 0; i < 4; i++) {
        result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
    }
    return result;
}


public int getJVM(){
    return JVM;
}


public int getIP(){
    return IP;
}


public String format(short shortval){
    String formatted = Integer.toHexString(shortval);
    StringBuilder buf = new StringBuilder("0000");
    buf.replace(4 - formatted.length(), 4, formatted);
    return buf.toString();
}


public short getHiTime(){
    return (short) (System.currentTimeMillis() >>> 32);
}


public String generate(){
    return new StringBuilder(32).append(format(getIP())).append(format(getJVM())).append(format(getHiTime())).append(format(getLoTime())).append(format(getCount())).toString();
}


public short getCount(){
    synchronized (UUIDGenerator.class) {
        if (counter < 0)
            counter = 0;
        return counter++;
    }
}


}