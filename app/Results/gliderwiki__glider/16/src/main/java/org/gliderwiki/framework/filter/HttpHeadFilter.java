package org.gliderwiki.framework.filter;
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
public class HttpHeadFilter implements Filter{

 private  NoBodyOutputStream noBodyOutputStream;

 private  PrintWriter writer;

 private  int contentLength;


@Override
public void init(FilterConfig filterConfig){
// Do nothing
}


public boolean isHttpHead(HttpServletRequest request){
    return "HEAD".equals(request.getMethod());
}


@Override
public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain){
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    if (isHttpHead(httpServletRequest)) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        NoBodyResponseWrapper noBodyResponseWrapper = new NoBodyResponseWrapper(httpServletResponse);
        chain.doFilter(new ForceGetRequestWrapper(httpServletRequest), noBodyResponseWrapper);
        noBodyResponseWrapper.setContentLength();
    } else {
        chain.doFilter(request, response);
    }
}


@Override
public PrintWriter getWriter(){
    if (writer == null) {
        writer = new PrintWriter(new OutputStreamWriter(noBodyOutputStream, getCharacterEncoding()));
    }
    return writer;
}


public void setContentLength(){
    super.setContentLength(noBodyOutputStream.getContentLength());
}


@Override
public void destroy(){
// Do nothing
}


@Override
public String getMethod(){
    return "GET";
}


public int getContentLength(){
    return contentLength;
}


@Override
public void write(byte[] buf,int offset,int len){
    contentLength += len;
}


}