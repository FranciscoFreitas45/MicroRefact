package org.gliderwiki.framework.filter.HttpHeadFilter;
 import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
public class NoBodyOutputStream extends ServletOutputStream{

 private  int contentLength;


public int getContentLength(){
    return contentLength;
}


@Override
public void write(byte[] buf,int offset,int len){
    contentLength += len;
}


}