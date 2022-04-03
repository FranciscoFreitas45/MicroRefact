package com.zis.bookinfo.util;
 import java.io.FileWriter;
import java.io.IOException;
public class TaobaoNetCapture extends AbstractNetCapture{


public void main(String[] args){
    // new TaobaoNetCapture().getBookInfo("524829830753");
    String ascii = "\u4f01\u4f03";
    System.out.println(ascii);
}


@Override
public BookMetadata getBookInfo(String id){
    String url = "https://item.taobao.com/item.htm?id=" + id;
    String buf = getUrlContent(url, DEFAULT_CHARSET);
    String failMsg = "很抱歉";
    if (buf.contains(failMsg)) {
        return null;
    }
    try {
        FileWriter fw = new FileWriter("c:/tmp/taobaoBook." + id);
        fw.write(buf);
        fw.flush();
        fw.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return null;
}


}