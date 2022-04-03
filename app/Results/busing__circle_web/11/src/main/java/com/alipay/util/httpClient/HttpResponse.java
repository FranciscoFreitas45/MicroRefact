package com.alipay.util.httpClient;
 import com.alipay.config.AlipayConfig;
import org.apache.commons.httpclient.Header;
import java.io.UnsupportedEncodingException;
public class HttpResponse {

 private  Header[] responseHeaders;

 private  String stringResult;

 private  byte[] byteResult;


public void setStringResult(String stringResult){
    this.stringResult = stringResult;
}


public Header[] getResponseHeaders(){
    return responseHeaders;
}


public String getStringResult(){
    if (stringResult != null) {
        return stringResult;
    }
    if (byteResult != null) {
        return new String(byteResult, AlipayConfig.input_charset);
    }
    return null;
}


public void setResponseHeaders(Header[] responseHeaders){
    this.responseHeaders = responseHeaders;
}


public void setByteResult(byte[] byteResult){
    this.byteResult = byteResult;
}


public byte[] getByteResult(){
    if (byteResult != null) {
        return byteResult;
    }
    if (stringResult != null) {
        return stringResult.getBytes();
    }
    return null;
}


}